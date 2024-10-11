package devsystem.olimpiclink.model

data class EventModelGet(
    val event_id: Int,
    val user_id: Int,
    val title_event: String,
    val description_event: String,

    val cover_image_event: String?,
    val image_one_event: String?,
    val image_two_event: String?,
    val image_three_event: String?,
    val image_four_event: String?,

    val date_start_event: String,
    val date_end_event: String,

    val place_id: Int?,
    val name_place: String?,

    val comunity_id: Int?,
    val login_user: String,
    val url_profile_picture_user: String
) {
    fun listarImagens(): MutableList<String> {
        val lista_imagens: MutableList<String> = mutableListOf()
        cover_image_event?.let { lista_imagens.add(it) }
        image_one_event?.let { lista_imagens.add(it) }
        image_two_event?.let { lista_imagens.add(it) }
        image_three_event?.let { lista_imagens.add(it) }
        image_four_event?.let { lista_imagens.add(it) }
        return lista_imagens
    }
}
