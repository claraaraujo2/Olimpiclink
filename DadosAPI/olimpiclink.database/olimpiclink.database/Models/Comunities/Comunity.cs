using System.ComponentModel.DataAnnotations;
using static System.Net.Mime.MediaTypeNames;
using System.Reflection.Metadata;

namespace olimpiclink.database.Models.Comunities
{
    public class Comunity
    {
        [Key]
        public int id_comunity { get; set; }
        public string name_comunity { get; set; }
        public string description_comunity { get; set; }
        public byte[] icon_comunity { get; set; }
        public DateTime created_at_comunities { get; set; }
        public DateTime updated_at_comunities { get; set; }
        public Comunity(string name_comunity, string description_comunity, byte[] icon_comunity) 
        {
            this.name_comunity = name_comunity;
            this.description_comunity = description_comunity;
            this.icon_comunity = icon_comunity;
            var teste = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            var test2 = DateTime.ParseExact(teste, "yyyy-MM-dd HH:mm:ss", null);
            created_at_comunities = test2;
            updated_at_comunities = test2;
        }
    }
}
