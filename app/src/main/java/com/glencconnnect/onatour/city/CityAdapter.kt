/**
 * Created by glenc on Dec 2021
 **/
package com.glencconnnect.onatour.city

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.glencconnnect.onatour.R
import com.glencconnnect.onatour.city.City

class CityAdapter(val context: Context, var cityList:ArrayList<City>): RecyclerView.Adapter<CityAdapter.CityViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_city,parent,false)
        return CityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cityList[position]
        holder.setData(city,position)
        holder.setListeners()
    }

    override fun getItemCount(): Int  = cityList.size

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {

        private var currentPos:Int = -1
        private var currentCity: City? = null

        private val txtCityName = itemView.findViewById<TextView>(R.id.txv_city_name)
        private val imvCityImage = itemView.findViewById<ImageView>(R.id.imv_city)
        private val imvDelete = itemView.findViewById<ImageView>(R.id.imv_delete)
        private val imvFavorite = itemView.findViewById<ImageView>(R.id.imv_favorite)

        private val icFavoriteFilledImage = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_favorite_filled,null)
        private val icFavoriteBorderedImage = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_favorite_bordered,null)

        fun setData(city: City, position: Int) {
            txtCityName.text = city.name
            imvCityImage.setImageResource(city.imageId)
            if(city.isFavorite)
                imvFavorite.setImageDrawable(icFavoriteFilledImage)
            else
                imvFavorite.setImageDrawable(icFavoriteBorderedImage)
            this.currentPos = position
            this.currentCity = city
        }

        fun setListeners() {
            imvDelete.setOnClickListener(this@CityViewHolder)
            imvFavorite.setOnClickListener(this@CityViewHolder)
        }

        override fun onClick(v: View?) {
            when(v!!.id){
                R.id.imv_delete -> deleteItem()
                R.id.imv_favorite -> addToFavorite()
            }
        }

        private fun addToFavorite() {

            currentCity?.isFavorite = !(currentCity?.isFavorite!!)
            if(currentCity?.isFavorite!!){
                imvFavorite.setImageDrawable(icFavoriteFilledImage)
                VacationSpots.favoriteCityList.add(currentCity!!)
            }else{
                imvFavorite.setImageDrawable(icFavoriteBorderedImage)
                VacationSpots.favoriteCityList.remove(currentCity!!)
            }
            notifyItemInserted(currentPos)
            notifyItemRangeChanged(currentPos,cityList.size)

        }

        private fun deleteItem() {

            cityList.removeAt(currentPos)
            notifyItemRemoved(currentPos)
            notifyItemRangeChanged(currentPos,cityList.size)

            VacationSpots.favoriteCityList.remove(currentCity!!)
        }
    }

}