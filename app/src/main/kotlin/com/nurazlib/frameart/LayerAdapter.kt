package com.nurazlib.frameart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter untuk mengelola list layer di RecyclerView
class LayerAdapter(private val drawingView: DrawingView) :
    RecyclerView.Adapter<LayerAdapter.LayerViewHolder>() {

    private val layers = mutableListOf<String>()

    init {
        layers.add("Layer 1")  // Tambahkan layer awal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return LayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: LayerViewHolder, position: Int) {
        holder.layerName.text = layers[position]
        // Logika untuk menampilkan dan mengatur setiap layer
    }

    override fun getItemCount(): Int = layers.size

    fun addLayer() {
        layers.add("Layer ${layers.size + 1}")
        notifyDataSetChanged()
    }

    class LayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layerName: TextView = itemView.findViewById(android.R.id.text1)
    }
}
