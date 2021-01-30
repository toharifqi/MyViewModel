package com.dicoding.naufal.myviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.naufal.myviewmodel.adapter.WeatherAdapter
import com.dicoding.naufal.myviewmodel.databinding.ActivityMainBinding
import com.dicoding.naufal.myviewmodel.model.WeatherModel
import com.dicoding.naufal.myviewmodel.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: WeatherAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = WeatherAdapter()
        adapter.notifyDataSetChanged()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.btnCity.setOnClickListener{
            val city = binding.editCity.text.toString()
            if (city.isEmpty()) return@setOnClickListener
            showLoading(true)
            mainViewModel.setWeather(city)
        }

        mainViewModel.getWeather().observe(this, object : Observer<ArrayList<WeatherModel>>{
            override fun onChanged(weatherItems: ArrayList<WeatherModel>) {
                if (weatherItems != null){
                    adapter.setData(weatherItems)
                    showLoading(false)
                }

            }
        })
    }



    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}