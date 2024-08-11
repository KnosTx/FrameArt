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
) : SurfaceView(context, attrs) {

    private val paint = Paint().apply {
        color = 0xFF000000.toInt()
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    private val paths = mutableListOf<Path>()
    private val currentPath = Path()

    init {
        paths.add(currentPath)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath.moveTo(event.x, event.y)
            }
            MotionEvent.ACTION_MOVE -> {
                currentPath.lineTo(event.x, event.y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                val newPath = Path(currentPath)
                paths.add(newPath)
                currentPath.reset()
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paths.forEach { path ->
            canvas?.drawPath(path, paint)
        }
    }

    // Fungsi untuk membersihkan canvas
    fun clearCanvas() {
        paths.clear()
        currentPath.reset()
        invalidate()
    }

    // Fungsi untuk menambah layer
    fun addLayer() {
        // Menambahkan path baru sebagai layer
        val newPath = Path()
        paths.add(newPath)
    }
}
