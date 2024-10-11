using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace olimpiclink.database.Models.Categories
{
    [Table("categories")]
    public class CategoryModel
    {
        [Key]
        public int id_category { get; set; }
        public string name_category { get; set; }

        public CategoryModel(string name_category)
        {
            this.name_category = name_category ?? throw new ArgumentNullException(nameof(name_category));
        }
    }
}
