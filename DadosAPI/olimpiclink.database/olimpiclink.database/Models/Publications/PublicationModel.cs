using Org.BouncyCastle.Utilities;
using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.Publications
{
    public class PublicationModel
    {
        [Key]
        public int id_publication { get; set; }
        public int user_id { get; set; }
        public bool activated_publication { get; set; }
        public string text_publication { get; set; }
        public string? url_image_one_publication { get; set; }
        public string? url_image_two_publication { get; set; }
        public string? url_image_three_publication { get; set; }
        public string? url_image_four_publication { get; set; }
        public byte[]? image_one_publication { get; set; }
        public byte[]? image_two_publication { get; set; }
        public byte[]? image_three_publication { get; set; }
        public byte[]? image_four_publication { get; set; }
        public DateTime? date_publication { get; set; }
        public int? comunity_id { get; set; }
        public int? place_id { get; set; }
        public int? event_id { get; set; }

        public PublicationModel
            (
            int user_id,
            string text_publication,
            bool activated_publication,
            byte[]? image_one_publication = null,
            byte[]? image_two_publication = null,
            byte[]? image_three_publication = null,
            byte[]? image_four_publication = null,
            int? comunity_id = null,
            int? place_id = null,
            int? event_id = null
            )
        {
            this.user_id = user_id;
            this.text_publication = text_publication;
            this.activated_publication = activated_publication;
            this.image_one_publication = image_one_publication;
            this.image_two_publication = image_two_publication;
            this.image_three_publication = image_three_publication;
            this.image_four_publication = image_four_publication;
            this.comunity_id = comunity_id;
            this.place_id = place_id;
            this.event_id = event_id;
        }
    }
}
