package Raul.Ex_Avaliativo.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import Raul.Ex_Avaliativo.data.repository.AnotationRepository
import Raul.Ex_Avaliativo.databinding.ActivityMainBinding
import Raul.Ex_Avaliativo.ui.adapter.AnotationAdapter
import Raul.Ex_Avaliativo.ui.details.DetailsActivity
import Raul.Ex_Avaliativo.ui.listeners.AnotationItemClickListener
import Raul.Ex_Avaliativo.ui.util.Constant

class MainActivity : AppCompatActivity(), AnotationItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = AnotationAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = MainViewModelFactory(AnotationRepository(applicationContext))
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        setupRecyclerView()
        setupObservers()
        setupListeners()
    }
    override fun onResume() {
        super.onResume()
        viewModel.checkDatabase()
    }
    override fun clickDone(position: Int) {
        val anotation = adapter.getDatasetItem(position)
        viewModel.makeTaskDone(anotation.id)
    }
    override fun clickOpen(position: Int) {
        val anotation = adapter.getDatasetItem(position)
        val mIntent = Intent(this, DetailsActivity::class.java)
        mIntent.putExtra(Constant.ANOTATION_ID, anotation.id)
        startActivity(mIntent)
    }
    private fun setupListeners() {
        binding.buttonAdd.setOnClickListener{
            val mIntent = Intent(this, DetailsActivity::class.java)
            startActivity(mIntent)
        }
    }
    private fun setupRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter
    }
    private fun setupObservers() {
        viewModel.anotations.observe(this, Observer {
            adapter.submitDataset(it)
        })
    }
}