namespace olimpiclink.database.Models
{
    public record AddPublicationModel
        (
        int user_id,
        string text_publication,
        string? image_one_publication,
        string? image_two_publication,
        string? image_three_publication,
        string? image_four_publication,
        int? comunity_id,
        int? place_id,
        int? event_id
        ){}
}
