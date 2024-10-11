using System.ComponentModel.DataAnnotations;
using System.Globalization;

namespace olimpiclink.database.Models.Places
{
    public record AddPlaceModel (int city_id, string name_place, string description_place, string opening_time_place, string closing_time_place);
}
