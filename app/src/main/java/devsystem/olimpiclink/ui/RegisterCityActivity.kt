package devsystem.olimpiclink.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityRegisterCityBinding
import devsystem.olimpiclink.model.User
import android.widget.EditText
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import devsystem.olimpiclink.model.City
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.util.CommonEvents
import devsystem.olimpiclink.util.EndpointCity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class RegisterCityActivity: AppCompatActivity() {
    private lateinit var binding : ActivityRegisterCityBinding;
    private lateinit var et_state : EditText;
    private lateinit var spn_cities : AutoCompleteTextView
    private lateinit var btn_continue : AppCompatButton
    private lateinit var api_city : EndpointCity
    private var cities_list : ArrayList<String> = arrayListOf()

    private var commonEvents = CommonEvents()
    private lateinit var city : City

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterCityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        componentsInitialize()
    }

    private fun createArrayCities() {
        api_city.citiesGet().enqueue(object : Callback<List<City>> {
            override fun onResponse(p0: Call<List<City>>, p1: Response<List<City>>) {
                if(p1.isSuccessful){
                    p1.body()?.let { city ->
                        city.forEach(){
                            cities_list.add(it.name_city)
                        }
                    }
                }
                else{
                    Log.e("RegisterCityActivity", "Error: ${p1.code()}")
                }
            }

            override fun onFailure(p0: Call<List<City>>, p1: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun componentsInitialize() {
        et_state = binding.etState
        et_state.setText("SP");
        et_state.isEnabled = false;
        spn_cities = binding.spnCities
        btn_continue = binding.btnContinue
        api_city = ApiCliente.retrofit.create(EndpointCity::class.java)

        spn_cities.onFocusChangeListener = commonEvents.focusChangedListenerGet(spn_cities)
        btn_continue.setOnTouchListener(commonEvents.touchListenerGet(btn_continue))

        createArrayCities()
        var array_cities = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities_list)
        array_cities.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spn_cities.setAdapter(array_cities)
        array_cities.notifyDataSetChanged()
    }

    fun registrationContinue(view: View) {
        if(spn_cities.text.isEmpty()){
            if(spn_cities.text.toString() in cities_list){
                city = City(0, spn_cities.text.toString(), "SP")
            }
            else{
                Toast.makeText(this, "Essa cidade não existe", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this, "Selecione uma cidade", Toast.LENGTH_SHORT).show()
        }

    }

}