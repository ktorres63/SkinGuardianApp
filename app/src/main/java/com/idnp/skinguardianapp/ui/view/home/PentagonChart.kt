package com.idnp.skinguardianapp.ui.view.home

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import android.graphics.Color
import android.graphics.Path
import android.util.AttributeSet
import kotlin.math.*

class PentagonChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var data = doubleArrayOf()
    fun setData(data: DoubleArray) {
        this.data = data
        invalidate()  // Invalida la vista para que se vuelva a dibujar con los nuevos datos
    }
    override fun onDraw(canvas: Canvas) {

        drawPentagon(0.33, canvas)
        drawPentagon(0.66, canvas)
        drawPentagon(1.0, canvas)
        drawPrincipal(data, canvas)
    }
    private fun drawPentagon(r : Double, canvas : Canvas) {
        val centerX = width / 2
        val centerY = height / 2
        val radius = min(centerX, centerY)
        val angleStep = (2 * PI) / 5

        val path = Path()
        path.moveTo((centerX + radius * r *  sin(0.0)).toFloat(), (centerY + radius * r * -cos(0.0)).toFloat())
        for (i in 1 until 5) {
            val angle = i * angleStep
            path.lineTo((centerX + radius * r * sin(angle)).toFloat(), (centerY + radius * r * -cos(angle)).toFloat())
        }
        path.close()

        val paint = Paint()
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        canvas.drawPath(path, paint)
    }

    private fun drawPrincipal(v : DoubleArray, canvas : Canvas) {
        val centerX = width / 2
        val centerY = height / 2
        val radius = min(centerX, centerY)
        val angleStep = (2 * PI) / 5

        val path = Path()
        path.moveTo((centerX + radius * v[0] *  sin(0.0)).toFloat(), (centerY + radius * v[0] * -cos(0.0)).toFloat())
        for (i in 1 until 5) {
            val angle = i * angleStep
            path.lineTo((centerX + radius * v[i] * sin(angle)).toFloat(), (centerY + radius * v[i] * -cos(angle)).toFloat())
        }
        path.close()

        val paint = Paint()
        paint.color = Color.CYAN
        paint.style = Paint.Style.FILL
        canvas.drawPath(path, paint)
    }
}