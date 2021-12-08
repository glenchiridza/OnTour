package com.glencconnnect.onatour.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.glencconnnect.onatour.R
import com.glencconnnect.onatour.city.City
import com.glencconnnect.onatour.city.VacationSpots
import java.util.*
import kotlin.collections.ArrayList


class FavoriteFragment : Fragment() {

    private lateinit var favoriteCityList : ArrayList<City>
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        setUpRecycler(view)
        return view
    }
    private fun setUpRecycler(view: View?) {
        val context = requireContext()

        favoriteCityList = VacationSpots.favoriteCityList as ArrayList<City>
        val favAdapter = FavoriteAdapter(context, favoriteCityList)

        recyclerView = view?.findViewById(R.id.fav_recycler_view)!!
        recyclerView?.adapter = favAdapter
        recyclerView?.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN ,ItemTouchHelper.RIGHT){
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, targetViewHolder: RecyclerView.ViewHolder): Boolean {

            //item dragged
            val fromPosition = viewHolder.adapterPosition
            val toPosition = targetViewHolder.adapterPosition

            Collections.swap(favoriteCityList, fromPosition,toPosition)

            recyclerView.adapter?.notifyItemMoved(fromPosition,toPosition)

            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            ///item swiped
            val position = viewHolder.adapterPosition
            val deletedCity:City = favoriteCityList[position]

            deleteItem(position)

        }

    })

    private fun deleteItem(position: Int) {
        favoriteCityList.removeAt(position)
        favoriteAdapter.notifyItemRemoved(position)
        favoriteAdapter.notifyItemRangeRemoved(position,favoriteCityList.size)
    }

}