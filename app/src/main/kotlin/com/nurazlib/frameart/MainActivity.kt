package com.nurazlib.frameart

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var drawingView: DrawingView
    private lateinit var layerAdapter: LayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.drawing_view)
        val addFrameButton: Button = findViewById(R.id.add_frame_button)
        val addLayerButton: Button = findViewById(R.id.add_layer_button)
        val layerList: RecyclerView = findViewById(R.id.layer_list)

        // Inisialisasi RecyclerView untuk menampilkan layer
        layerAdapter = LayerAdapter(drawingView)
        layerList.layoutManager = LinearLayoutManager(this)
        layerList.adapter = layerAdapter

        addFrameButton.setOnClickListener {
            // Logika untuk menambah frame
            drawingView.clearCanvas()  // Membersihkan canvas untuk frame baru
        }

        addLayerButton.setOnClickListener {
            // Logika untuk menambah layer
            drawingView.addLayer()
            layerAdapter.addLayer()
        }
    }
}
