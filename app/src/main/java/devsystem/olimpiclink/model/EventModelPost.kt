package devsystem.olimpiclink.model

data class EventModelPost(
    val user_id: Int,
    val title_event: String,
    val description_event: String,

    val image_one_event: String?,
    val image_two_event: String?,
    val image_three_event: String?,
    val image_four_event: String?,

    val date_start_event: String,
    val date_end_event: String,

    val place_id: Int?,
    val event_id: Int
)