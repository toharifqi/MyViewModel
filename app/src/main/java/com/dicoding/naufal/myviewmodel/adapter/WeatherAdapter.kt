package com.dicoding.naufal.myviewmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.naufal.myviewmodel.R
import com.dicoding.naufal.myviewmodel.databinding.ItemWeatherBinding
import com.dicoding.naufal.myviewmodel.model.WeatherModel

class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    private val mData = ArrayList<WeatherModel>()

    fun setData(items: ArrayList<WeatherModel>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class ViewHolder(private val binding: ItemWeatherBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(weatherModel: WeatherModel){
            with(binding){
                textCity.text = weatherModel.name
                textTemp.text = weatherModel.temperature
                textDesc.text = weatherModel.description
            }
        }
    }
}