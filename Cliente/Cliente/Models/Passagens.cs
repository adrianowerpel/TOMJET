using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Cliente.Models
{
    public class Passagens
    {
        public Int64 id { get; set; }

        [DataType(DataType.DateTime)]
        public DateTime dtCompra { get; set; }

        [JsonIgnore]
        public Clientes cliente { get; set; }
        [JsonIgnore]
        public Voos voo { get; set; }
    }
}