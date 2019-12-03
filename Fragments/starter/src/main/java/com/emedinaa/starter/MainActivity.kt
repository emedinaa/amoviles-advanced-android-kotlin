package com.emedinaa.starter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emedinaa.starter.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //events
        btn1.setOnClickListener {
            //goToView(FS1Activity::class.java)
        }

        btn2.setOnClickListener {
            //goToView(FS2Activity::class.java)
        }

        btn3.setOnClickListener {
            //goToView(FS3Activity::class.java)
        }

        btn4.setOnClickListener {
            //goToView(FS4Activity::class.java)
        }

        btn5.setOnClickListener {
            //goToView(FExerciseActivity::class.java)
        }

        btn6.setOnClickListener {
            //goToView(FCommunicationActivity::class.java)
        }

    }

    private fun goToView(activity:Class<*>) {

        //1.Contexto de la vista donde te encuentras
        //2. Conocer la pantalla de destino
        startActivity(Intent(this, activity))
    }
}
