package com.nurazlib.frameart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceView
import android.graphics.Path

// Kelas untuk menangani canvas menggambar
class DrawingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : SurfaceView(context, attrs), SurfaceHolder.Callback {

    private val paint = Paint().apply {
        color = 0xFF000000.toInt()
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    private val paths = mutableListOf<Path>()
    private val currentPath = Path()

    init {
        paths.add(currentPath)
        holder.addCallback(this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath.moveTo(event.x, event.y)
            }
            MotionEvent.ACTION_MOVE -> {
                currentPath.lineTo(event.x, event.y)
                draw()
            }
            MotionEvent.ACTION_UP -> {
                val newPath = Path(currentPath)
                paths.add(newPath)
                currentPath.reset()
            }
        }
        return true
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        draw()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        draw()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {}

    private fun draw() {
        val canvas = holder.lockCanvas()
        canvas?.let {
            it.drawColor(0xFFFFFFFF.toInt()) // Ganti dengan warna latar belakang yang diinginkan
            paths.forEach { path ->
                it.drawPath(path, paint)
            }
            holder.unlockCanvasAndPost(it)
        }
    }

    // Fungsi untuk membersihkan canvas
    fun clearCanvas() {
        paths.clear()
        currentPath.reset()
        draw()
    }

    // Fungsi untuk menambah layer
    fun addLayer() {
        val newPath = Path()
        paths.add(newPath)
    }
}
