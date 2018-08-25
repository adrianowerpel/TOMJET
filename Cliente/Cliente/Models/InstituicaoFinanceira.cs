using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Cliente.Models
{
    public class InstituicaoFinanceira
    {
        public Int64 id { get; set; }
        public String nome { get; set; }
        public String numCartao { get; set; }
        public double saldo { get; set; }

        [DataType(DataType.Date)]
        public DateTime dtValidade { get; set; }

        [JsonIgnore]
        public Clientes cliente { get; set; }
    }
}