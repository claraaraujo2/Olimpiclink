package devsystem.olimpiclink.model

data class PublicationModelPost(
    val user_id : Int,
    val text_publication : String,

    val image_cover_event: String?,

    val image_one_publication : String?,
    val image_two_publication : String?,
    val image_three_publication : String?,
    val image_four_publication : String?,

    val comunity_id : Int?,
    val place_id : Int?,
    val event_id : Int?,
)
