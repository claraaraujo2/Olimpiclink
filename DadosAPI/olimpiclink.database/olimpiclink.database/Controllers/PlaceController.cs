using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models.Places;

namespace olimpiclink.database.Controllers
{
    [ApiController]
    [Route("api/places")]
    public class PlaceController : Controller
    {
        ConnectionContext context = new ConnectionContext();

        [HttpGet]
        public async Task<IActionResult> getPlaces()
        {
            var places = await context.places.ToListAsync();
            return Ok(places);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> getPlace(int id)
        {
            var place = await context.places.FindAsync(id);
            return Ok(place);
        }

        [HttpPost]
        public async Task<IActionResult> postPlace(AddPlaceModel new_place)
        {
            var place = new PlaceModel(new_place.city_id, new_place.name_place, new_place.description_place, TimeSpan.Parse(new_place.opening_time_place), TimeSpan.Parse(new_place.closing_time_place));
            await context.places.AddAsync(place);
            await context.SaveChangesAsync();
            return Ok();
        }
    }
}
