package com.idnp.skinguardianapp.ui.view.profile

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class PieChartView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val labels = listOf("% Grasa", "% Arrugas", "% Lesiones", "% Uniforme")
    private var data =floatArrayOf()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF = RectF()

    fun setData(data: FloatArray) {
        this.data = data
        invalidate()  // Invalida la vista para que se vuelva a dibujar con los nuevos datos
    }

    private val colors = listOf(
        Color.rgb(255, 253, 130),
        Color.rgb(255, 155, 113),
        Color.rgb(232, 72, 85),
        Color.rgb(181, 107, 69)
    )


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = canvas.width.toFloat()
        val height = canvas.height.toFloat()
        val size = if (width < height) width else height
        val radius = size / 2 * 0.8f
        val centerX = width / 2
        val centerY = height / 2
        val startAngle = -90f

        var currentAngle = startAngle
        rectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)

        for (i in data.indices) {
            paint.color = colors[i]
            val sweepAngle = 360 * (data[i] / data.sum())
            canvas.drawArc(rectF, currentAngle, sweepAngle, true, paint)

            // Dibuja el texto en el centro del segmento
            val text = labels.getOrElse(i) { "Texto $i" }
            val textX = centerX + radius * 0.7f * Math.cos(Math.toRadians(currentAngle + sweepAngle / 2.0)).toFloat()
            val textY = centerY + radius * 0.7f * Math.sin(Math.toRadians(currentAngle + sweepAngle / 2.0)).toFloat()

            textPaint.color = Color.BLACK
            textPaint.textSize = 24f
            textPaint.textAlign = Paint.Align.CENTER
            canvas.drawText(text, textX, textY, textPaint)

            currentAngle += sweepAngle
        }
    }
}
