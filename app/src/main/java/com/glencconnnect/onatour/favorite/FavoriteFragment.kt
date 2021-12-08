package com.glencconnnect.onatour.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.glencconnnect.onatour.R
import com.glencconnnect.onatour.city.VacationSpots


class FavoriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        setUpRecycler(view)
        return view
    }
    private fun setUpRecycler(view: View?) {
        val context = requireContext()

        val favAdapter = FavoriteAdapter(context, VacationSpots.cityList!!)

        val recyclerView = view?.findViewById<RecyclerView>(R.id.fav_recycler_view)
        recyclerView?.adapter = favAdapter
        recyclerView?.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView?.layoutManager = layoutManager
    }
}