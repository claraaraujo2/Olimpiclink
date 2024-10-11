using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models;
using olimpiclink.database.Models.Categories;
using System.Linq;

namespace olimpiclink.database.Controllers
{
    [ApiController] 
    [Route("api/v1/category")]  // define a rota da url
    public class CategoryController : Controller
    {
        ConnectionContext context = new ConnectionContext(); // Chama o "contexto", a conexão com o banco de dados.
        [HttpGet("add/{name_category}")] // Define que o metodo a seguir é GET, normalmente usado apenas para pegar dados, nesse caso está mandando dados a partir da url
        public async Task<IActionResult> categoryCreate(string name_category, CancellationToken ct)
        {
            var new_category = new CategoryModel(name_category); // objeto category, porém feito apenas com o nome, sem ID, pois ID é auto_increment
            if(await context.categories.AnyAsync(category => category.name_category == name_category, ct)) // verifica de a categoria já existe
            {
                return Ok("Essa categoria já existe");
            }
            await context.categories.AddAsync(new_category, ct); //adiciona no banco de dados
            await context.SaveChangesAsync(ct); //salva no banco
            return Ok();
        }

        [HttpGet("{id_category}")] //D efine que o metodo a seguir é GET, usado para pegar um dado especifico
        public async Task<IActionResult> categoryGetID(long? id_category, CancellationToken ct)
        {
             return Ok(await context.categories.Where(category => category.id_category == id_category).ToListAsync()); //puxa a categoria que tenha o id referente ao id inserido na url
        }

        [HttpGet] // Define que o metodo a seguir é GET, usando para pegar todos os dados da tabela.
        public async Task<IActionResult> categoryGet(long? id_category, CancellationToken ct)
        {
            var get_categories = await context.categories.ToListAsync(ct);
            return Ok(get_categories);
        }

        [HttpGet("alter/{id_category}/{name_category}")] // Metodo para alterar dados na tabela. Utiliza o ID na rota para localizar o objeto e o "name_category" é o dado possivel de ser mudado
        public async Task<IActionResult> categoryChange(long? id_category, string name_category, CancellationToken ct)
        {
            var category = await context.categories.SingleOrDefaultAsync(category => category.id_category == id_category, ct);
            if (category == null)
            {
                return Ok("Categoria não encontrada");
            }
            category.name_category = name_category;
            await context.SaveChangesAsync(ct);
            return Ok();
        }

        [HttpGet("delete/{id_category}")]
        public async Task<IActionResult> categoryDelete(long? id_category, CancellationToken ct)
        {
            var category = await context.categories.SingleOrDefaultAsync(category => category.id_category == id_category, ct);
            if (category == null)
            {
                return Ok("Categoria não encontrada");
            }
            context.categories.Remove(category);
            await context.SaveChangesAsync(ct);
            return Ok();
        }
    }
}
