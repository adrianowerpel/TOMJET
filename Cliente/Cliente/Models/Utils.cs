using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Cliente.Models
{
    public class Utils
    {
        public static Clientes Clientes
        {
            get
            {
                if (HttpContext.Current.Session["Clientes"] != null)
                {
                    return (Clientes)HttpContext.Current.Session["Clientes"];
                }

                return null;
            }
        }

        public static Voos Voos
        {
            get
            {
                if (HttpContext.Current.Session["Voos"] != null)
                {
                    return (Voos)HttpContext.Current.Session["Voos"];
                }

                return null;
            }
        }

        public static InstituicaoFinanceira InstituicaoFinanceira
        {
            get
            {
                if (HttpContext.Current.Session["InstituicaoFinanceira"] != null)
                {
                    return (InstituicaoFinanceira)HttpContext.Current.Session["InstituicaoFinanceira"];
                }

                return null;
            }
        }

        public static void Logar(Clientes cliente)
        {
            if (cliente != null)
            {
                HttpContext.Current.Session["Clientes"] = cliente;
            }
        }

        public static void MeuVoo(Voos Voos)
        {
            if (Voos != null)
            {
                HttpContext.Current.Session["Voos"] = Voos;
            }
        }

        public static void MeuBanco(InstituicaoFinanceira InstituicaoFinanceira)
        {
            if (InstituicaoFinanceira != null)
            {
                HttpContext.Current.Session["InstituicaoFinanceira"] = InstituicaoFinanceira;
            }
        }

        public static void Deslogar()
        {
            HttpContext.Current.Session["Clientes"] = null;
            HttpContext.Current.Session.Remove("Clientes");

            HttpContext.Current.Session["Voos"] = null;
            HttpContext.Current.Session.Remove("Voos");

            HttpContext.Current.Session["InstituicaoFinanceira"] = null;
            HttpContext.Current.Session.Remove("InstituicaoFinanceira");
        }
    }
}