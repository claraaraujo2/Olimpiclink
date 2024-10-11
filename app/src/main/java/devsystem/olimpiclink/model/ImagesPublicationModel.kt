package devsystem.olimpiclink.model

data class ImagesPublicationModel(
    val image_one_publication : String?,
    val image_two_publication : String?,
    val image_three_publication : String?,
    val image_four_publication : String?,
    var lista_imagens : MutableList<String>
){
    fun listarImagens() : MutableList<String>{
        if(image_one_publication != null){
            lista_imagens.add(image_one_publication)
        }
        if(image_two_publication != null){
            lista_imagens.add(image_two_publication)
        }
        if(image_three_publication != null){
            lista_imagens.add(image_three_publication)
        }
        if(image_four_publication != null){
            lista_imagens.add(image_four_publication)
        }
        return lista_imagens
    }
}
