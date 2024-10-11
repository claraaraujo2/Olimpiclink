package devsystem.olimpiclink.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import devsystem.olimpiclink.R
import devsystem.olimpiclink.util.CommonEvents

class InitialActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_initial)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.navigationBarColor = resources.getColor(R.color.end_initial)
        var buttonEvents = CommonEvents()
        var btn_login = findViewById<AppCompatButton>(R.id.btn_login)
        var btn_register = findViewById<AppCompatButton>(R.id.btn_register)
        btn_login.setOnTouchListener(buttonEvents.touchListenerGet(btn_login))
        btn_register.setOnTouchListener(buttonEvents.touchListenerGet(btn_register))
    }

    fun userGoLogin(view: View) {
        val login_screen = Intent(this, LoginActivity::class.java) // cria um objeto da proxima activity
        startActivity(login_screen) // inicia o objeto
    }
    fun userGoRegister(view: View) {
        val register_screen = Intent(this, RegisterActivity::class.java)
        startActivity(register_screen)
    }
}