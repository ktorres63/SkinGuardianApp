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

    override fun onDraw(canvas: Canvas) {
        val centerX = width / 2
        val centerY = height / 2
        val radius = min(centerX, centerY)
        val angleStep = (2 * PI) / 5

        val path = Path()
        path.moveTo((centerX + radius * sin(0.0)).toFloat(), (centerY + radius * -cos(0.0)).toFloat())
        for (i in 1 until 5) {
            val angle = i * angleStep
            path.lineTo((centerX + radius * sin(angle)).toFloat(), (centerY + radius * -cos(angle)).toFloat())
        }
        path.close()

        // Dibujar el pentágono
        val paint = Paint()
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        canvas.drawPath(path, paint)

        val path1 = Path()
        path1.moveTo((centerX + radius / 3 * 2 * sin(0.0)).toFloat(), (centerY + radius / 3 * 2 * -cos(0.0)).toFloat())
        for (i in 1 until 5) {
            val angle = i * angleStep
            path1.lineTo((centerX + radius / 3 * 2 * sin(angle)).toFloat(), (centerY + radius / 3 * 2 * -cos(angle)).toFloat())
        }
        path1.close()

        // Dibujar el pentágono
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        canvas.drawPath(path1, paint)

        val path2 = Path()
        path2.moveTo((centerX + radius / 3  * sin(0.0)).toFloat(), (centerY + radius / 3 * -cos(0.0)).toFloat())
        for (i in 1 until 5) {
            val angle = i * angleStep
            path2.lineTo((centerX + radius / 3 * sin(angle)).toFloat(), (centerY + radius / 3 * -cos(angle)).toFloat())
        }
        path2.close()

        // Dibujar el pentágono
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        canvas.drawPath(path2, paint)




    }
}