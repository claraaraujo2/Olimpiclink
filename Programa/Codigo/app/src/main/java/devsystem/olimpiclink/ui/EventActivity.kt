package devsystem.olimpiclink

import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import devsystem.olimpiclink.databinding.ActivityEventBinding
import devsystem.olimpiclink.model.Carousel
import devsystem.olimpiclink.util.AdapterEventCarousel
import android.app.DatePickerDialog
import java.util.Calendar

class Event : AppCompatActivity() {

    private lateinit var binding: ActivityEventBinding
    private lateinit var adapterEventCarousel: AdapterEventCarousel
    private val listImage: MutableList<Carousel> = mutableListOf()

    private lateinit var selectImageLauncher: ActivityResultLauncher<String>
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        val editTextDate: TextView = findViewById(R.id.editTextDate)
        val editTextDate2: TextView = findViewById(R.id.editTextDate2)

        // Configurar calendário para a data inicial
        editTextDate.setOnClickListener {
            showDatePicker { selectedDate ->
                editTextDate.text = selectedDate
            }
        }

        // Configurar calendário para a data final
        editTextDate2.setOnClickListener {
            showDatePicker { selectedDate ->
                editTextDate2.text = selectedDate
            }
        }

        // Inicializar o seletor de imagem
        selectImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                selectedImageUri = uri
                Toast.makeText(this, "Imagem selecionada com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }

        // Adicionar imagem da capa
        binding.BTNAddCover.setOnClickListener {
            selectImageLauncher.launch("image/*")
        }

        // Publicar evento
        binding.BTNPublishAnEvents.setOnClickListener {
            val title = binding.TVTextPublicationEvents.text.toString()
            val description = binding.TVTextDescription.text.toString()
            val startDate = binding.editTextDate.text.toString()
            val endDate = binding.editTextDate2.text.toString()

            if (title.isBlank() || description.isBlank() || startDate.isBlank() || endDate.isBlank() || selectedImageUri == null) {
                Toast.makeText(this, "Por favor, preencha todos os campos e adicione uma capa!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Evento publicado com sucesso!", Toast.LENGTH_LONG).show()

                // Adiciona a imagem do evento à lista de Carousels
                listImage.add(Carousel(selectedImageUri.toString()))
                adapterEventCarousel.notifyItemInserted(listImage.size - 1)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewCarousel.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewCarousel.setHasFixedSize(true)
        adapterEventCarousel = AdapterEventCarousel(this, listImage)
        binding.recyclerViewCarousel.adapter = adapterEventCarousel
    }

    private fun showDatePicker(onDateSelected: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Formatar a data selecionada
                val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                onDateSelected(formattedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}
