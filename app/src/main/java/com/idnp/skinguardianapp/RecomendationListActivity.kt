package com.idnp.skinguardianapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RecomendationListActivity : AppCompatActivity() {
    private lateinit var btnRemedy: AppCompatButton
    private lateinit var btnDiet: AppCompatButton
    private lateinit var btnExercise: AppCompatButton
    private lateinit var btnReturn: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_recommendations)

        initComponent()
    }

    private fun initComponent() {
        btnRemedy = findViewById(R.id.btnRemedy)
        btnDiet = findViewById(R.id.btnDiet)
        btnExercise = findViewById(R.id.btnExercise)
        btnReturn = findViewById(R.id.btnReturn)
    }
}