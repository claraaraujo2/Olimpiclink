using olimpiclink.database.Data;

namespace olimpiclink.database.Models.Publications
{
    public class GetPublicationModel
    {
        public int id_publication { get; set; }
        public int user_id { get; set; }
        public string login_user { get; set; }
        public string? url_profile_picture_user { get; set; }
        public string text_publication { get; set; }
        public string? url_image_one_publication { get; set; }
        public string? url_image_two_publication { get; set; }
        public string? url_image_three_publication { get; set; }
        public string? url_image_four_publication { get; set; }
        public DateTime? date_publication { get; set; }
        public int? comunity_id { get; set; }
        public int? place_id { get; set; }
        public string? name_place {  get; set; }
        public int? event_id { get; set; }

        public GetPublicationModel
        (
            int id_publication,
            int user_id,
            string text_publication,
            string? url_image_one_publication = null,
            string? url_image_two_publication = null,
            string? url_image_three_publication = null,
            string? url_image_four_publication = null,
            DateTime? date_publication = null,
            int? comunity_id = null,
            int? place_id = null,
            int? event_id = null
        )
        {
            ConnectionContext teste = new ConnectionContext();
            var user = teste.users.FindAsync(user_id).Result;
            this.id_publication = id_publication;
            this.user_id = user_id;
            this.text_publication = text_publication;
            this.url_image_one_publication = url_image_one_publication;
            this.url_image_two_publication = url_image_two_publication;
            this.url_image_three_publication = url_image_three_publication;
            this.url_image_four_publication = url_image_four_publication;
            this.date_publication = date_publication;
            this.comunity_id = comunity_id;
            this.place_id = place_id;
            this.event_id = event_id;
            if(place_id != null )
            {
                var place = teste.places.FindAsync(place_id);
                this.name_place = place.Result.name_place;
            }
            this.login_user = user.login_user;
            this.url_profile_picture_user = user.url_profile_picture_user;

        }
    }

}
