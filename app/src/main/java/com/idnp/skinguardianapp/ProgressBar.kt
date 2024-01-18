package com.idnp.skinguardianapp
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val pincel = Paint()
        pincel.color = Color.BLUE
        pincel.strokeWidth = 10f
        pincel.style = Paint.Style.STROKE
        pincel.color = Color.BLUE
        canvas.drawRect(100f, 300f, 800f, 500f, pincel)

        var num = 200f
        for (i in 1 until 8) {
            canvas.drawRect(100f * i, 300f, num, 500f, pincel)
        }
        pincel.strokeWidth = 1f
        pincel.color = Color.RED
        pincel.style = Paint.Style.FILL
        canvas.drawRect(100f, 300f, num, 500f, pincel)
        canvas.drawRect(300f, 300f, num, 500f, pincel)
        pincel.textSize = 50f
        for (i in 1 until 8) {
            canvas.drawText("${i} d", 100f * i, 200f, pincel)
        }
    }
}