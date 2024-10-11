using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.Cities
{
    public class City
    {
        [Key]
        public int id_city { get; set; }
        public string name_city { get; set; }
        public string uf_city { get; set;}

        public City(string name_city, string uf_city)
        {
            this.name_city = name_city;
            this.uf_city = uf_city;
        }
    }
}
