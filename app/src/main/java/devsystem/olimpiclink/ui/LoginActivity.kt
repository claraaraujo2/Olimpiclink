package devsystem.olimpiclink.ui

import devsystem.olimpiclink.util.CommonEvents
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityLoginBinding
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.model.util.EndpointUser
import retrofit2.Call


class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var commonEvents: CommonEvents
    private lateinit var btn_login : AppCompatButton
    private lateinit var et_username : EditText
    private lateinit var et_password : EditText
    private lateinit var api_user : EndpointUser
    private var user : User? = null

    @Suppress("DEPRECATION")
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.navigationBarColor = resources.getColor(R.color.end_initial)
        componentsInitialize()
    }

    private fun userLogar(login_user : String, password_user : String){
        api_user.userLogin(login_user, password_user).enqueue(object : retrofit2.Callback<User> {
            override fun onResponse(call: Call<User>, response: retrofit2.Response<User>){
                if (response.isSuccessful){
                    response.body()?.let { user_bd ->
                        Log.d("LoginActivity", "User: ${user_bd.name_user}")
                        user = User(user_bd.id_user, user_bd.name_user, user_bd.email_user,
                            user_bd.login_user, user_bd.url_profile_picture_user, user_bd.created_at_user)
                        var main_activity = Intent(this@LoginActivity, MainActivity::class.java)
                        main_activity.putExtra("user", user)
                        startActivity(main_activity)
                        finish()
                    }
                }else{
                    Log.e("LoginActivity", "Erro: ${response.code()}")
                    Toast.makeText(this@LoginActivity, "Login ou senha invalidos", Toast.LENGTH_SHORT).show()
                    et_username.setText("");
                    et_username.requestFocus()
                    et_password.setText("");
                }
            }
            override fun onFailure(call:Call<User>, t: Throwable){
                Log.e("LoginActivity", "falha", t)

            }
        })
    }

    private fun componentsInitialize() {
        commonEvents = CommonEvents()
        btn_login = binding.btnLogin
        et_username = binding.etUsername
        et_password = binding.etPassword

        api_user = ApiCliente.retrofit.create(EndpointUser::class.java)

        et_username.onFocusChangeListener = commonEvents.focusChangedListenerGet(et_username)
        et_password.onFocusChangeListener = commonEvents.focusChangedListenerGet(et_password)
        btn_login.setOnTouchListener(commonEvents.touchListenerGet(btn_login))
    }

    fun enterClick(view: View) {
        userLogar(et_username.text.toString(), et_password.text.toString())
    }
}