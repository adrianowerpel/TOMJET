using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Cliente.Models
{
    public class Voos
    {
        public Int64 id { get; set; }
        public String cidadeOrigem { get; set; }
        public String cidadeDestino { get; set; }

        [DataType(DataType.DateTime)]
        public DateTime dataSaida { get; set; }
        [DataType(DataType.DateTime)]
        public DateTime dataChegada { get; set; }

        public int qtdAssentos { get; set; }

        [DataType(DataType.Currency)]
        public double valorPassagem { get; set; }

        [JsonIgnore]
        public List<Passagens> passagens { get; set; }

        public Voos()
        {
            this.passagens = new List<Passagens>();
        }
    }
}