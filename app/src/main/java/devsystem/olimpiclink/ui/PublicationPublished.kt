package devsystem.olimpiclink.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.PublishedPublicationBinding
import devsystem.olimpiclink.util.AdapterPublicationImages

class PublicationPublished @JvmOverloads constructor (
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {
    private var login_user : String? = null
    private var text_publication : String? = null
    val binding = PublishedPublicationBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setLayout(attrs)
    }
    private fun setLayout(attrs: AttributeSet?){
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.PublicationPublished
            )

            login_user = attributes.getString(R.styleable.PublicationPublished_tv_login_user)
            text_publication = attributes.getString(R.styleable.PublicationPublished_tv_text_publication)
            binding.tvUsername.text = login_user
            binding.tvTextPublication.text = text_publication
            attributes.recycle()
        }
    }

    fun setTeste(login : String, text_publication : String, date_publication : String, url_profile_picutre : String, images : MutableList<String>){
        binding.tvUsername.text = login
        binding.tvTextPublication.text = text_publication
        binding.tvDatePublication.text = date_publication
        Glide.with(this).load(url_profile_picutre).circleCrop().into(binding.imageView4)
        var one_line_images = mutableListOf<String>()
        var two_line_images = mutableListOf<String>()
        for (i in 0 until images.size){
            if(i < 2){
                one_line_images.add(images[i])
                Log.d("PublicationPublished", "one_line ${images[i]}")
            }
            else{
                two_line_images.add(images[i])
                Log.d("PublicationPublished", "two_line ${images[i]}")
            }
        }
        var adapter_one = AdapterPublicationImages(one_line_images, context)
        val rc = binding.rcImages
        rc.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rc.setHasFixedSize(true)
        rc.adapter = adapter_one

        var adapter_two = AdapterPublicationImages(two_line_images, context)
        val rc_images2 = binding.rcImages2
        rc_images2.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rc_images2.setHasFixedSize(true)
        rc_images2.adapter = adapter_two
    }
}