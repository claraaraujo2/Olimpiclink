using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Models.Categories;
using olimpiclink.database.Models.Cities;
using olimpiclink.database.Models.Comunities;
using olimpiclink.database.Models.Places;
using olimpiclink.database.Models.Publications;
using olimpiclink.database.Models.Users;
using Pomelo.EntityFrameworkCore.MySql;
using System.Configuration;

namespace olimpiclink.database.Data
{
    public class ConnectionContext : DbContext
    {
        public DbSet<CategoryModel> categories { get; set; }
        public DbSet<UserModel> users { get; set; }
        public DbSet<City> cities { get; set; }
        public DbSet<Comunity> comunities { get; set; }
        public DbSet<PublicationModel> publications { get; set; }
        public DbSet<PlaceModel> places { get; set; }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseMySql("Server=localhost;Port=3306;Database=olimpiclink;Uid=root;Pwd=123;", new MySqlServerVersion(new Version(8, 0, 21)));
        }
    }
}
