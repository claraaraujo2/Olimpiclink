package devsystem.olimpiclink.util

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.WindowManager
import androidx.core.view.marginTop
import androidx.core.view.setPadding
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import devsystem.olimpiclink.databinding.PublishedPublicationBinding
import devsystem.olimpiclink.model.PublicationModelGet

class AdapterPublication(
    var lista_publications : List<PublicationModelGet>,
    var context : Context
) : RecyclerView.Adapter<AdapterPublication.PublicationsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : PublicationsViewHolder{
        val itemLista = PublishedPublicationBinding.inflate(LayoutInflater.from(context), parent, false)
        return PublicationsViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: PublicationsViewHolder, position: Int) {
        holder.tv_username.text = lista_publications[position].login_user
        Glide.with(context).load(lista_publications[position].url_profile_picture_user).circleCrop().into(holder.img_profile)
        holder.tv_date_publication.text = lista_publications[position].date_publication
        holder.tv_text_publication.text = lista_publications[position].text_publication
        var images = lista_publications[position].listarImagens()
        if (images.size == 0){
            holder.rc_column_one.setHasFixedSize(false);
            holder.rc_column_two.setHasFixedSize(false);
        }
        else {
            imagens(holder, position, images)
        }

        if(lista_publications[position].place_id != null){
            holder.tv_localization.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10.toFloat())
            holder.tv_localization.text = "Localização: "+lista_publications[position].name_place.toString()
        }
    }
    fun imagens(holder: PublicationsViewHolder, position: Int, images: MutableList<String>){
        var one_line_images = mutableListOf<String>()
        var two_line_images = mutableListOf<String>()
        for (i in 0 until images.size){
            if(i < 2){
                one_line_images.add(images[i])
            }
            else{
                two_line_images.add(images[i])
            }
        }
        adapterImagens(holder.rc_column_one, position, one_line_images)
        adapterImagens(holder.rc_column_two, position, two_line_images)
    }

    fun adapterImagens(rc : RecyclerView, position: Int, images: MutableList<String>){
        var adapter_one = AdapterPublicationImages(images, context)
        rc.layoutManager = LinearLayoutManager(context)
        rc.setHasFixedSize(true)
        rc.adapter = adapter_one
    }

    override fun getItemCount() = lista_publications.size

    inner class PublicationsViewHolder(binding: PublishedPublicationBinding) : RecyclerView.ViewHolder(binding.root) {
        val img_profile = binding.imageView4
        val tv_username = binding.tvUsername
        val tv_date_publication = binding.tvDatePublication
        val tv_text_publication = binding.tvTextPublication
        val rc_column_one = binding.rcImages
        val rc_column_two = binding.rcImages2
        val tv_localization = binding.tvLocalization
    }
}