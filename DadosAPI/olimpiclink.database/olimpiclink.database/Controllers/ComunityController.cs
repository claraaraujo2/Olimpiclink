using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models.Comunities;

namespace olimpiclink.database.Controllers
{
    [ApiController]
    [Route("api/v1/comunities")]  //define a rota da url
    public class ComunityController : Controller
    {
        ConnectionContext context = new ConnectionContext(); //cria conexão
        [HttpPost("upload")] //define a rota
        public async Task<IActionResult> Add([FromForm] string name_comunity, [FromForm] string description_comunity, IFormFile icon_comunity)
        {
            var memoryStream = new MemoryStream();
            icon_comunity.CopyTo(memoryStream); //coloca a imagem em um armazem temporario da api
            var new_comunity = new Comunity(name_comunity, description_comunity, memoryStream.ToArray()); //coloca a imagem no objeto
            await context.comunities.AddAsync(new_comunity); //adiciona o objeto no banco de dados
            await context.SaveChangesAsync(); 
            return Ok();
        }
        [HttpGet("Get/{id}")]
        public async Task<IActionResult> Get(int id)
        {
            var teste = await context.comunities.Where(comunity => comunity.id_comunity == id).ToListAsync();

            return Ok(teste[0].icon_comunity); //exibe o arquivo do banco no formato jpeg
        }
    }
}
