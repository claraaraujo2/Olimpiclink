using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;

namespace olimpiclink.database.Controllers
{
    [ApiController]
    [Route("api/v1/cities")]  //define a rota da url
    public class CityController : Controller
    {
        ConnectionContext context = new ConnectionContext();

        [HttpGet]
        public async Task<IActionResult> cityGet(CancellationToken ct)
        {
            
            var city = await context.cities.ToListAsync(ct);
            return Ok(city);
        }
        [HttpGet("{id_city}")]

        public async Task<IActionResult> cityGetID(long? id_city, CancellationToken ct)
        {
            var city_specific = await context.cities.Where(city => city.id_city == id_city).FirstOrDefaultAsync(ct);
            if (city_specific == null)
            {
                return Ok("Essa cidade não existe");
            }
            return Ok(city_specific);
        }
    }
}
