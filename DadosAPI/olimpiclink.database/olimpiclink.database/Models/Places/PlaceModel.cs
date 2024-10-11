using System.ComponentModel.DataAnnotations;
using System.Globalization;

namespace olimpiclink.database.Models.Places
{
    public class PlaceModel
    {
        [Key]
        public int id_place { get; set; }
        public int city_id { get; set; }
        public string name_place { get; set; }
        public string description_place { get; set; }
        public TimeSpan? opening_time_place { get; set; }
        public TimeSpan? closing_time_place { get; set; }
        public DateTime? created_at_place { get; set; }
        public DateTime? updated_at_place { get; private set; }

        public PlaceModel() { }

        public PlaceModel(int city_id, string name_place, string description_place, TimeSpan opening_time_place, TimeSpan closing_time_place) 
        {
            this.city_id = city_id;
            this.name_place = name_place;
            this.description_place = description_place;
            this.opening_time_place = opening_time_place;
            this.closing_time_place = closing_time_place;
        }
    }
}
