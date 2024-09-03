package Raul.Ex_Avaliativo.ui.details

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import Raul.Ex_Avaliativo.data.repository.AnotationRepository
import Raul.Ex_Avaliativo.databinding.DetailsActivityBinding
import Raul.Ex_Avaliativo.ui.util.Constant
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DetailsActivity: AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding: DetailsActivityBinding
    private lateinit var viewModel: DetailsViewModel
    private var deadLine: LocalDate = LocalDate.now()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = DetailsViewModelFactory(AnotationRepository(applicationContext))
        viewModel = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)
        handleBundle()
        setupUi()
        setupListeners()
        setupObservers()

    }
    override fun onDateSet(datePicker: DatePicker, year: Int, mounth: Int, dayOfMounth:
    Int) {
        deadLine = LocalDate.of(year, mounth+1, dayOfMounth)
        setupUi()
    }
    private fun handleBundle(){
        if (intent.hasExtra(Constant.ANOTATION_ID)){
            val id = intent.getLongExtra(Constant.ANOTATION_ID, -1)
            viewModel.showEvent(id)
        }
    }
    private fun setupUi(){
        val str = "${deadLine.dayOfMonth}/${deadLine.monthValue}/${deadLine.year}"
        binding.buttonDeadline.text = str
    }
    private fun setupObservers() {
        viewModel.saved.observe(this, Observer {
            if (it){
                Toast.makeText(this, "Tarefa salva com sucesso.",
                    Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Erro ao salvar tarefa.", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.isUpdate.observe(this, Observer {
            if (it){
                binding.buttonSave.text = "salvar alteracao"
            }
        })
        viewModel.title.observe(this, Observer {
            binding.editTitle.setText(it)
        })

        viewModel.anotation.observe(this, Observer {
            binding.editAnotation.setText(it)
        })
        viewModel.description.observe(this, Observer {
            binding.editLocal.setText(it)
        })
        viewModel.deadlineString.observe(this, Observer {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            deadLine = LocalDate.parse(it, formatter)
            setupUi()
        })
    }
    private fun setupListeners() {
        binding.buttonDeadline.setOnClickListener{
            initDatePickerDialog()
        }
        binding.buttonSave.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val desc = binding.editLocal.text.toString()
            if (title.isEmpty() || title.isBlank()){
                Toast.makeText(this, "Título da tarefa é obrigatório",
                    Toast.LENGTH_SHORT).show()
            } else {
                viewModel.saveAnotation(title, desc, deadLine)
            }
        }
    }
    private fun initDatePickerDialog() {
        DatePickerDialog(this, this, deadLine.year, deadLine.monthValue-1,
            deadLine.dayOfMonth).show()
    }
}

