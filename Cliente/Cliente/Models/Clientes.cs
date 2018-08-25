using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Cliente.Models
{
    public class Clientes
    {
        public Int64 id { get; set; }
        public String nome { get; set; }
        public String login { get; set; }

        [DataType(DataType.Password)]
        public String senha { get; set; }
        public String cpf { get; set; }
        public String telefone { get; set; }

        [DataType(DataType.Date)]
        public DateTime dtNascimento { get; set; }
        public String rua { get; set; }
        public int numero { get; set; }
        public String bairro { get; set; }
        public String cidade { get; set; }
        public String estado { get; set; }

        [JsonIgnore]
        public InstituicaoFinanceira cartao { get; set; }

        [JsonIgnore]
        public IList<Passagens> passagens { get; set; }

        public Clientes()
        {
            this.passagens = new List<Passagens>();
        }
    }
}