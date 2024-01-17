package com.idnp.skinguardianapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import com.idnp.skinguardianapp.R

class ProgressBarView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val progressBarPaint = Paint()
    private var progress = 0f
    private val button: Button

    init {
        // Inflar el diseño de la vista
        LayoutInflater.from(context).inflate(R.layout.fragment_home, this, true)

        // Inicializar la pintura para la barra de progreso
        progressBarPaint.color = Color.BLUE
        progressBarPaint.style = Paint.Style.FILL

        // Buscar el botón en el layout inflado
        button = findViewById(R.id.button)
        button.setOnClickListener {
            // Aumentar el progreso cada vez que se presiona el botón
            progress += 0.1f
            invalidate() // Volver a dibujar la vista
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Dibujar la barra de progreso rectangular
        val barWidth = width.toFloat() * progress
        canvas.drawRect(0f, 0f, barWidth, height.toFloat(), progressBarPaint)
    }
}