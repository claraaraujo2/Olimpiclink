package devsystem.olimpiclink.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityMainBinding
import devsystem.olimpiclink.model.PublicationModelGet
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.util.AdapterPublication
import devsystem.olimpiclink.util.CommonEvents
import devsystem.olimpiclink.util.EndpointPublication
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var api_publication : EndpointPublication
    private lateinit var list_publication : List<PublicationModelGet>
    var commonEvents = CommonEvents()
    private lateinit var user : User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.navigationBarColor = resources.getColor(R.color.laranja_splash)
        window.statusBarColor = ContextCompat.getColor(this, R.color.laranja_splash)
        user = intent.extras!!.getParcelable<User>("user")!!
        api_publication = ApiCliente.retrofit.create(EndpointPublication::class.java)
        publicationGet()
        commonEvents.goPageCreationPublication(user, this, binding.bottomMenu.binding.btnPgCreatePublication)
        commonEvents.goPageMain(user, this, binding.bottomMenu.binding.btnPgInitial)
    }

    private fun publicationGet() {
        Log.d("MainActivity", "dentro")
        lifecycleScope.launch {
            Log.d("MainActivity", "launch")
            try {
                Log.d("MainActivity", "launch1")
                list_publication = api_publication.publicationsGet()
                var adapter = AdapterPublication(list_publication, this@MainActivity)
                var rc = binding.rcFeed
                rc.layoutManager = LinearLayoutManager(this@MainActivity)
                rc.setHasFixedSize(true)
                rc.adapter = adapter

            } catch (e: Exception) {
                // Lide com o erro
                Log.d("MainActivity", "launch2")
                e.printStackTrace()
            }
        }
    }

    fun publicar(view: View) {
        var main_activity = Intent(this, UnpublishedPublicationActivity::class.java)
        main_activity.putExtra("user", user)
        startActivity(main_activity)
        finish()
    }
}