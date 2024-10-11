package devsystem.olimpiclink.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityRegisterBinding
import devsystem.olimpiclink.util.CommonEvents
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import devsystem.olimpiclink.model.RequestMessages
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.model.util.EndpointUser
import retrofit2.Call
import android.widget.Toast
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var et_name_user : EditText
    private lateinit var et_login_user : EditText
    private lateinit var et_email : EditText
    private lateinit var et_password : EditText
    private lateinit var btn_continue : AppCompatButton
    private lateinit var api_user : EndpointUser
    private var user : User? = null
    private var commonEvents = CommonEvents()

    @Suppress("DEPRECATION")
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.navigationBarColor = resources.getColor(R.color.end_initial)
        componentsInitialize()
    }

    private fun componentsInitialize() {
        et_name_user = binding.etNameUser
        et_login_user = binding.etLoginUser
        et_email = binding.etEmail
        et_password = binding.etPassword
        btn_continue = binding.btnContinue

        api_user = ApiCliente.retrofit.create(EndpointUser::class.java)

        et_name_user.onFocusChangeListener = commonEvents.focusChangedListenerGet(et_name_user)
        et_login_user.onFocusChangeListener = commonEvents.focusChangedListenerGet(et_login_user)
        et_email.onFocusChangeListener = commonEvents.focusChangedListenerGet(et_email)
        et_password.onFocusChangeListener = commonEvents.focusChangedListenerGet(et_password)
        btn_continue.setOnTouchListener(commonEvents.touchListenerGet(btn_continue))
    }

    fun userExists(login_user : String, email_user : String){
        api_user.emailUsernameCheck(login_user, email_user).enqueue(object : retrofit2.Callback<RequestMessages>{
            override fun onResponse(call: Call<RequestMessages>, response: retrofit2.Response<RequestMessages>) {
                if(response.isSuccessful){
                    response.body()?.let { user_bd ->
                        Log.d("RegisterActivity", "erro: ${user_bd.message}")
                        if(user_bd.message != "Pode continuar"){
                            messageMake(user_bd.message)
                        }
                        else{
                            user = User(0, et_name_user.text.toString(), et_name_user.text.toString(),
                                et_login_user.text.toString(), "", "")
                        }
                    }
                }
                else{
                    Log.e("RegisterActivity", "Erro: ${response.code()}")
                }
            }
            override fun onFailure(p0: Call<RequestMessages>, p1: Throwable) {
                Log.e("RegisterActivity", "Erro", p1)
            }
        })
    }

    fun messageMake(message : String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        if(message == "username e email já cadastrados"){
            et_login_user.setText("");
            et_email.setText("");
            et_login_user.setBackgroundResource(R.drawable.edit_text_selected)
            et_email.setBackgroundResource(R.drawable.edit_text_selected)
            et_login_user.requestFocus()
        }
        else if(message == "email já cadastrados"){
            et_email.setText("");
            et_email.setBackgroundResource(R.drawable.edit_text_selected)
            et_email.requestFocus()

        }
        else{
            et_login_user.setText("");
            et_login_user.setBackgroundResource(R.drawable.edit_text_selected)
            et_login_user.requestFocus()
        }

    }

    fun registrationContinue(view: View) {
        if(et_login_user.text.isNotEmpty() && et_email.text.isNotEmpty()
            && et_name_user.text.isNotEmpty() && et_password.text.isNotEmpty()){
                userExists(et_login_user.text.toString(), et_email.text.toString())
                if(user != null){
                    var register_city = Intent(this, RegisterCityActivity::class.java)
                    register_city.putExtra("user", user)
                    startActivity(register_city)
                }
        }
        else{
            var register_city = Intent(this, RegisterCityActivity::class.java)
            startActivity(register_city)
        }
    }
}