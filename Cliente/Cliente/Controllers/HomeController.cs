using Cliente.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Mvc;

namespace Cliente.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult CadastrarCliente()
        {
            return View(new Clientes());
        }

        public ActionResult GravarCliente(Clientes cliente)
        {
            var url = "http://localhost:8090/novoCliente";
            var client = new HttpClient();
            var response = client.PostAsJsonAsync(url, cliente).GetAwaiter().GetResult();

            return RedirectToAction("Logar");
        }

        public ActionResult Logar()
        {
            ViewBag.Aviso = "Digite usuário e senha!";
            return View();
        }

        public ActionResult VerificarLogin(String login, String senha)
        {
            var url = "http://localhost:8090/buscarPorLogin/"+login+"&"+senha+"";
            var client = new HttpClient();
            var response = client.GetAsync(url).GetAwaiter().GetResult();

            Clientes cliente = new Clientes();

            if (response.IsSuccessStatusCode)
            {
                var cliJson = response.Content.ReadAsStringAsync().GetAwaiter().GetResult();
                cliente = JsonConvert.DeserializeObject<Clientes>(cliJson);

                Utils.Logar(cliente);
                var voos = TodosVoos();
                ViewBag.Encontrados = "Bem vindo "+cliente.nome+"!";
                return View("Index",voos);
            }
            else
            {
                ViewBag.Aviso = "Usuário ou senha incorretos!";
                return View("Logar");
            }

            
        }

        public ActionResult Index()
        {
            var voos = TodosVoos();

            ViewBag.Encontrados = "Voos Disponíveis!";

            return View(voos);
        }

        public ActionResult PesquisarVoo(String txtCidadeOrigem, DateTime dtSaida, String txtCidadeDestino)
        {
            String dataSaida = dtSaida.ToString("yyyy-MM-dd");

            var url = "http://localhost:8090/vooPorData/"+txtCidadeOrigem+"&"+dataSaida+"&"+txtCidadeDestino+"";
            var client = new HttpClient();
            var response = client.GetAsync(url).GetAwaiter().GetResult();
            var voos = new List<Voos>();

            if (response.IsSuccessStatusCode)
            {
                var voosJson = response.Content.ReadAsStringAsync().GetAwaiter().GetResult();
                voos = JsonConvert.DeserializeObject<List<Voos>>(voosJson).ToList();
            }

            ViewBag.Encontrados = "" + voos.Count() + " voo(s) encontrado(s)!";

            return View("Index",voos);
        }

        public ActionResult VerificarCartao(Voos voo)
        {
            if(Utils.Clientes != null)
            {
                Utils.MeuVoo(voo);
                return View(voo);
            }
            else
            {
                ViewBag.Aviso = "A compra de passagem só pode ser feita com usuário logado!";
                return View("Logar");
            }            
        }

        public ActionResult ComprarPassagem(String txtCartao)
        {
            
            String valor = Utils.Voos.valorPassagem.ToString();
            valor = valor.Replace(",", ".");

            var url = "http://localhost:8090/validarCompra/"+valor+"&"+txtCartao+"";
            var client = new HttpClient();                
            var response = client.GetAsync(url).GetAwaiter().GetResult();
            var banco = new InstituicaoFinanceira();

            if (response.IsSuccessStatusCode)
            {
                var bancoJson = response.Content.ReadAsStringAsync().GetAwaiter().GetResult();
                banco = JsonConvert.DeserializeObject<InstituicaoFinanceira>(bancoJson);

                banco.saldo = banco.saldo - Utils.Voos.valorPassagem;
                Utils.MeuBanco(banco);

                banco = Utils.InstituicaoFinanceira;

                url = "http://localhost:8090/editarBanco";
                client = new HttpClient();
                response = client.PutAsJsonAsync(url, banco).GetAwaiter().GetResult();

                var voo = Utils.Voos;
                voo.qtdAssentos = Utils.Voos.qtdAssentos - 1;
                Utils.MeuVoo(voo);
                url = "http://localhost:8090/editarVoo";
                client = new HttpClient();
                response = client.PutAsJsonAsync(url, Utils.Voos).GetAwaiter().GetResult();

                Passagens passagem = new Passagens();
                url = "http://localhost:8090/novaPassagem/"+Utils.Clientes.id+"&"+Utils.Voos.id+"";
                client = new HttpClient();
                response = client.GetAsync(url).GetAwaiter().GetResult();

                return RedirectToAction("MinhasPassagens");
            }
            else
            {
                var voos = TodosVoos();
                ViewBag.Encontrados = "Número do cartão invalido ou saldo insuficiente!";
                return View("Index",voos);
            }

                                            

        }

        public ActionResult MinhasPassagens()
        {
            var url = "http://localhost:8090/passagensPorCliente/" + Utils.Clientes.id+"";
            var client = new HttpClient();
            var response = client.GetAsync(url).GetAwaiter().GetResult();

            var passagens = new List<Passagens>();

            if (response.IsSuccessStatusCode)
            {
                var passagemJson = response.Content.ReadAsStringAsync().GetAwaiter().GetResult();
                passagens = JsonConvert.DeserializeObject<List<Passagens>>(passagemJson).ToList();
            }

            return View(passagens);
        }

        public ActionResult DevolverPassagem(Passagens passagem)
        {
            var url = "http://localhost:8090/deletarPassagem/"+passagem.id+"";
            var client = new HttpClient();
            var response = client.DeleteAsync(url).GetAwaiter().GetResult();

            var banco = Utils.InstituicaoFinanceira;
            banco.saldo = banco.saldo + Utils.Voos.valorPassagem;
           
            url = "http://localhost:8090/editarBanco";
            client = new HttpClient();
            response = client.PutAsJsonAsync(url, banco).GetAwaiter().GetResult();

            var voo = Utils.Voos;
            voo.qtdAssentos = Utils.Voos.qtdAssentos + 1;
            Utils.MeuVoo(voo);
            url = "http://localhost:8090/editarVoo";
            client = new HttpClient();
            response = client.PutAsJsonAsync(url, Utils.Voos).GetAwaiter().GetResult();

            return RedirectToAction("MinhasPassagens");
        }

        public ActionResult Deslogar()
        {
            Utils.Deslogar();

            return RedirectToAction("Index");
        }

        public static List<Voos> TodosVoos()
        {
            var url = "http://localhost:8090/todosVoos";
            var client = new HttpClient();
            var response = client.GetAsync(url).GetAwaiter().GetResult();
            var voos = new List<Voos>();

            if (response.IsSuccessStatusCode)
            {
                var voosJson = response.Content.ReadAsStringAsync().GetAwaiter().GetResult();
                voos = JsonConvert.DeserializeObject<List<Voos>>(voosJson).ToList();
            }

            return voos;
        }
    }
}