package devsystem.olimpiclink.util

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import devsystem.olimpiclink.databinding.ImagesPublicationBinding
import devsystem.olimpiclink.databinding.PublishedPublicationBinding
import devsystem.olimpiclink.model.ImagesPublicationModel

class AdapterPublicationImages(
    var lista_images : MutableList<String>,
    var context : Context
) : RecyclerView.Adapter<AdapterPublicationImages.ImagesPublicationModelViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImagesPublicationModelViewHolder {
        val itemLista = ImagesPublicationBinding.inflate(LayoutInflater.from(context), parent, false)
        return ImagesPublicationModelViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: ImagesPublicationModelViewHolder, position: Int) {
        Glide.with(context).load(lista_images[position]).into(holder.img)
    }

    override fun getItemCount() = lista_images.size



    inner class ImagesPublicationModelViewHolder(binding : ImagesPublicationBinding) : RecyclerView.ViewHolder(binding.root){
        val img = binding.imageView
    }
}