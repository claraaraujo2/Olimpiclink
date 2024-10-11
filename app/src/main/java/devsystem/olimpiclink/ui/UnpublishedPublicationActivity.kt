package devsystem.olimpiclink.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityUnpublishedPublicationBinding
import devsystem.olimpiclink.model.User
import android.widget.EditText
import android.widget.TextView
import android.widget.ImageView
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.lifecycleScope
import devsystem.olimpiclink.model.PublicationModelPost
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.model.util.EndpointUser
import devsystem.olimpiclink.util.CommonEvents
import devsystem.olimpiclink.util.EndpointPublication
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class UnpublishedPublicationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUnpublishedPublicationBinding
    private lateinit var user : User
    private lateinit var img_profile_picture_user : ImageView
    private lateinit var tv_login_user : TextView
    private lateinit var tv_text_publication : EditText

    private lateinit var image_one_publication : ImageView
    private lateinit var image_two_publication : ImageView
    private lateinit var image_three_publication : ImageView
    private lateinit var image_four_publication : ImageView
    private var selectImage : Uri? = null;
    private lateinit var api_publication : EndpointPublication
    private lateinit var btn_publicar : AppCompatButton
    var commonEvents = CommonEvents()

    private var lista_imagens = mutableListOf<Uri?>()
    private var lista_image_view = mutableListOf<ImageView>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUnpublishedPublicationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.navigationBarColor = resources.getColor(R.color.laranja_splash)
        window.statusBarColor = ContextCompat.getColor(this, R.color.laranja_splash)

        user = intent.extras!!.getParcelable<User>("user")!!
        componentsInitialize()

        Glide.with(this).load("http://192.168.0.158:5000/api/publication/imagens/1/2").into(binding.imgOne)
        Glide.with(this).load("http://192.168.0.158:5000/api/publication/imagens/1/2").into(binding.imgTwo)
        Glide.with(this).load("http://192.168.0.158:5000/api/publication/imagens/1/2").into(binding.imgThree)
        Glide.with(this).load("http://192.168.0.158:5000/api/publication/imagens/1/2").into(binding.imgFour)
    }


    @SuppressLint("SetTextI18n")
    private fun componentsInitialize() {
        img_profile_picture_user = binding.imgPictureProfileUser
        tv_login_user = binding.tvUsername
        tv_text_publication = binding.tvTextPublication
        image_one_publication = binding.imgOne
        image_two_publication = binding.imgTwo
        image_three_publication = binding.imgThree
        image_four_publication = binding.imgFour

        lista_image_view.add(image_one_publication)
        lista_image_view.add(image_two_publication)
        lista_image_view.add(image_three_publication)
        lista_image_view.add(image_four_publication)

        image_one_publication.setImageBitmap(null);
        api_publication = ApiCliente.retrofit.create(EndpointPublication::class.java)
        btn_publicar = binding.btnPublicar
        Glide.with(this).load(user.url_profile_picture_user).circleCrop().into(img_profile_picture_user)
        tv_login_user.text = "@${user.login_user}"
        btn_publicar.setOnTouchListener(commonEvents.touchListenerGet(btn_publicar))
        commonEvents.goPageMain(user,this, binding.bottomMenu.binding.btnPgInitial)
    }

    fun publishPublication(view: View) {
        Log.d("UnpublishedPublicationActivity", "pintinho")
        var img_one = byteArrayToBase64(imageToByte(lista_image_view[0]))
        var img_two = byteArrayToBase64(imageToByte(lista_image_view[1]))
        var img_three = byteArrayToBase64(imageToByte(lista_image_view[2]))
        var img_four = byteArrayToBase64(imageToByte(lista_image_view[3]))
        Log.d("UnpublishedPublicationActivity", "$img_one")
        if(tv_text_publication.text.isNotEmpty()){
            var new_publication = PublicationModelPost(
                user.id_user,
                tv_text_publication.text.toString(),
                img_one,img_two,img_three,img_four,
                null,null,null
            )
            Log.d("UnpublishedPublicationActivity", "pintao")
            lifecycleScope.launch {
                try {
                    api_publication.publicationsPost(
                        new_publication
                        )
                    Log.d("UnpublishedPublicationActivity", "launch2321")
                    var main_activity = Intent(this@UnpublishedPublicationActivity, MainActivity::class.java)
                    main_activity.putExtra("user", user)
                    startActivity(main_activity)
                    finish()
                }
                catch (e: Exception) {
                    // Lide com o erro
                    Log.d("UnpublishedPublicationActivity", "launch2")
                    e.printStackTrace()
                }
            }
        }
    }

    fun byteArrayToBase64(byteArray: ByteArray?): String? {
        return if (byteArray != null) {
            Base64.encodeToString(byteArray, 1)
        } else {
            null
        }
    }
    fun imageToByte(image : ImageView?) : ByteArray?{
        if(image?.drawable == null){
            return null
        }
        var bitmap = (image.drawable as BitmapDrawable).bitmap
        var stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, stream)
        return stream.toByteArray()
    }

    fun selectPhoto(view: View) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0){
            selectImage = data?.data
            if(lista_imagens.size < 4){
                binding.btnAdcImg.isEnabled = true;
                lista_imagens.add(selectImage)
                for (i in 0 until lista_imagens.size){
                    lista_image_view[i].visibility = View.VISIBLE
                    Glide.with(this).load(lista_imagens[i]).into(lista_image_view[i])
                }
            }
            else{
                binding.btnAdcImg.isEnabled = false;
            }
        }
    }

    fun imageAdd(view: View) {
        var intent = Intent(Intent.ACTION_PICK);
        intent.setType("image/*")
        startActivityForResult(intent, requestCode = 0)
    }

}

