using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models;
using olimpiclink.database.Models.Publications;
using olimpiclink.database.Models.Users;

namespace olimpiclink.database.Controllers
{
    [ApiController]
    [Route("api/publication")]
    public class PublicationController : Controller
    {
        ConnectionContext context = new ConnectionContext();
        [HttpGet]
        public async Task<IActionResult> getPublication()
        {
            var teste = new PlaceController();
            var publications = await context.publications.OrderByDescending(user => user.date_publication).ToListAsync();
            var lista = new List<GetPublicationModel>();
            foreach (var publication in publications)
            {
                var getPublication = new GetPublicationModel
                    (
                        publication.id_publication,
                        publication.user_id,
                        publication.text_publication,
                        publication.url_image_one_publication,
                        publication.url_image_two_publication,
                        publication.url_image_three_publication,
                        publication.url_image_four_publication,
                        publication.date_publication,
                        publication.comunity_id,
                        publication.place_id,
                        publication.event_id
                    );
                lista.Add( getPublication );
            }
            return Ok(lista);
        }
        [HttpGet("user/{user_id}")]
        public async Task<IActionResult> getUserPublications(int user_id)
        {
            var teste = new PlaceController();
            var publications = await context.publications.Where(publication => publication.user_id == user_id && publication.activated_publication == true).OrderByDescending(user => user.date_publication).ToListAsync();
            var lista = new List<GetPublicationModel>();
            foreach (var publication in publications)
            {
                var getPublication = new GetPublicationModel
                    (
                        publication.id_publication,
                        publication.user_id,
                        publication.text_publication,
                        publication.url_image_one_publication,
                        publication.url_image_two_publication,
                        publication.url_image_three_publication,
                        publication.url_image_four_publication,
                        publication.date_publication,
                        publication.comunity_id,
                        publication.place_id,
                        publication.event_id
                    );
                lista.Add(getPublication);
            }
            return Ok(lista);
        }
    
        [HttpGet("imagens/{id}/{img}")]
        public async Task<IActionResult> getImagesPublication(int id, int img)
        {
            byte[] publicationImage = null;
            switch (img){
                case 1:
                    publicationImage = (await context.publications.FindAsync(id)).image_one_publication;
                    break;
                case 2:
                    publicationImage = (await context.publications.FindAsync(id)).image_two_publication;
                    break;
                case 3:
                    publicationImage = (await context.publications.FindAsync(id)).image_three_publication;
                    break;
                case 4:
                    publicationImage = (await context.publications.FindAsync(id)).image_four_publication;
                    break;
            }
            if(publicationImage == null)
            {
                return BadRequest();
            }
            return File(publicationImage, "image/jpeg");
        }

        [HttpPost]
        public async Task<IActionResult> postImagePublication(
            [FromForm] int id, IFormFile image_one_publication = null, 
            IFormFile image_two_publication = null, 
            IFormFile image_three_publication = null, 
            IFormFile image_four_publication = null
        )
        {
            
            var publication = await context.publications.FindAsync(id);

            if (image_one_publication != null)
            {
                var memoryStream = new MemoryStream();
                image_one_publication.CopyTo(memoryStream);
                publication.image_one_publication = memoryStream.ToArray();
                publication.url_image_one_publication = "http://192.168.0.158:5000/api/publication/imagens/" + id + "/1";
                memoryStream.Close();
            }
            if(image_two_publication != null)
            {
                var memoryStream = new MemoryStream();
                image_two_publication.CopyTo(memoryStream);
                publication.image_two_publication = memoryStream.ToArray();
                publication.url_image_two_publication = "http://192.168.0.158:5000/api/publication/imagens/" + id + "/2";
                memoryStream.Close();
            }
            if(image_three_publication != null)
            {
                var memoryStream = new MemoryStream();
                image_three_publication.CopyTo(memoryStream);
                publication.image_three_publication = memoryStream.ToArray();
                publication.url_image_three_publication = "http://192.168.0.158:5000/api/publication/imagens/" + id + "/3";
                memoryStream.Close();
            }
            if (image_four_publication != null)
            {
                var memoryStream = new MemoryStream();
                image_four_publication.CopyTo(memoryStream);
                publication.image_four_publication = memoryStream.ToArray();
                publication.url_image_four_publication = "http://192.168.0.158:5000/api/publication/imagens/" + id + "/4";
                memoryStream.Close();
            }
            await context.SaveChangesAsync();
            return Ok();
        }

        [HttpPost("pointo")]
        public async Task<IActionResult> pint(AddPublicationModel new_add_publication)
        {
            byte[]? img_one = null;
            byte[]? img_two = null;
            byte[]? img_three = null;
            byte[]? img_four = null;
            if (new_add_publication.image_one_publication != null)
            {
                img_one = Convert.FromBase64String(new_add_publication.image_one_publication.Trim().Replace(" ", "").Replace("\n", ""));
            }
            if (new_add_publication.image_two_publication != null)
            {
                img_two = Convert.FromBase64String(new_add_publication.image_two_publication.Trim().Replace(" ", "").Replace("\n", ""));
            }
            if (new_add_publication.image_three_publication != null)
            {
                img_three = Convert.FromBase64String(new_add_publication.image_three_publication.Trim().Replace(" ", "").Replace("\n", ""));
            }
            if (new_add_publication.image_four_publication != null)
            {
                img_four = Convert.FromBase64String(new_add_publication.image_four_publication.Trim().Replace(" ", "").Replace("\n", ""));
            }
            PublicationModel new_publication = new 
                PublicationModel(
                new_add_publication.user_id,
                new_add_publication.text_publication,
                true,
                img_one,
                img_two, img_three, img_four,
                new_add_publication.comunity_id,
                new_add_publication.place_id,
                new_add_publication.event_id
                );
            var id = context.publications.ToListAsync().Result.Count() + 1;
            if (new_publication.image_one_publication != null)
            {
                new_publication.url_image_one_publication = "http://192.168.0.158:5000/api/publication/imagens/" + id + "/1";
            }
            if (new_publication.image_two_publication != null)
            {
                new_publication.url_image_two_publication = "http://192.168.0.158:5000/api/publication/imagens/" + id + "/2";
            }
            if (new_publication.image_three_publication != null)
            {
                new_publication.url_image_three_publication = "http://192.168.0.158:5000/api/publication/imagens/" + id + "/3";
            }
            if (new_publication.image_four_publication != null)
            {
                new_publication.url_image_four_publication = "http://192.168.0.158:5000/api/publication/imagens/" + id + "/4";
            }
            var teste = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            var test2 = DateTime.ParseExact(teste, "yyyy-MM-dd HH:mm:ss", null);
            new_publication.date_publication = test2;
            await context.publications.AddAsync(new_publication);
            await context.SaveChangesAsync();
            return Ok();
        }

        [HttpPut("arquivar")]
        public async Task<IActionResult> arquivar(int id_publication)
        {
            var publication = await context.publications.SingleOrDefaultAsync(publication  => publication.id_publication == id_publication);
            publication.activated_publication = false;
            await context.SaveChangesAsync();
            return Ok();
        }
    }
}
