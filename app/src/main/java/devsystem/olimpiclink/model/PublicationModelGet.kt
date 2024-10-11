package devsystem.olimpiclink.model

class PublicationModelGet(
    val id_publication : Int,
    val user_id : Int,
    val login_user : String,
    val url_profile_picture_user : String,
    val text_publication : String,

    val url_image_one_publication : String?,
    val url_image_two_publication : String?,
    val url_image_three_publication : String?,
    val url_image_four_publication : String?,

    val date_publication : String,
    val comunity_id : Int?,
    val place_id : Int?,
    val name_place : String?,
    val event_id : Int?
) {
    fun listarImagens(): MutableList<String> {
        var lista_imagens : MutableList<String>
        lista_imagens = mutableListOf()
        if (url_image_one_publication != null) {
            lista_imagens.add(url_image_one_publication)
        }
        if (url_image_two_publication != null) {
            lista_imagens.add(url_image_two_publication)
        }
        if (url_image_three_publication != null) {
            lista_imagens.add(url_image_three_publication)
        }
        if (url_image_four_publication != null) {
            lista_imagens.add(url_image_four_publication)
        }
        return lista_imagens
    }
}