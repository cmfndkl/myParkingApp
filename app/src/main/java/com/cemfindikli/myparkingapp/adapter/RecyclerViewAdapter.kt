package com.cemfindikli.myparkingapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cemfindikli.myparkingapp.R
import com.cemfindikli.myparkingapp.model.LocationModel
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(private val locationList : ArrayList<LocationModel>, private val listener : Listener) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener {
        fun onItemClick(locationModel: LocationModel)
    }

    private val colors: Array<String> = arrayOf(
        "#13bd27",
        "#29c1e1",
        "#b129e1",
        "#d3df13",
        "#f6bd0c",
        "#a1fb93",
        "#0d9de3",
        "#ffe48f"
    )

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(
            locationModel: LocationModel,
            colors: Array<String>,
            position: Int,
            listener: Listener
        ) {

            itemView.setOnClickListener {
                listener.onItemClick(locationModel)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
            itemView.text_name.text = locationModel.name
            itemView.text_longitude.text = locationModel.locationsx
            itemView.text_latitude.text = locationModel.locationsy
            itemView.imageView.load(locationModel.image)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return RowHolder(view)
    }

    override fun getItemCount(): Int {
        return locationList.count()
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(locationList[position], colors, position, listener)
    }
}


