/**
 * Created by glenc on Dec 2021
 **/
package com.glencconnnect.onatour.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glencconnnect.onatour.R
import com.glencconnnect.onatour.city.City

class FavoriteAdapter(val context: Context, var favCityList:ArrayList<City>): RecyclerView.Adapter<FavoriteAdapter.CityViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_favorite,parent,false)
        return CityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = favCityList[position]
        holder.setData(city,position)
    }

    override fun getItemCount(): Int  = favCityList.size

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var currentPos:Int = -1
        private var currentCity: City? = null

        private val txtCityName = itemView.findViewById<TextView>(R.id.txv_city_name)
        private val imvCityImage = itemView.findViewById<ImageView>(R.id.imv_city)

        fun setData(city: City, position: Int) {
            txtCityName.text = city.name
            imvCityImage.setImageResource(city.imageId)
            if(city.isFavorite)

            this.currentPos = position
            this.currentCity = city
        }
    }

}