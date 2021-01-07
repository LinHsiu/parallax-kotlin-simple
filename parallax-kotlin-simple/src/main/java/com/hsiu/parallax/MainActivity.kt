package com.hsiu.parallax

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hsiu.parallax.activity.HorizonActivity
import com.hsiu.parallax.activity.VerticalActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_horizon.setOnClickListener {
            val intentHorizon = Intent()
            intentHorizon.setClass(this, HorizonActivity::class.java)
            startActivity(intentHorizon)
        }
        btn_vertical.setOnClickListener {
            val intentVertical = Intent()
            intentVertical.setClass(this, VerticalActivity::class.java)
            startActivity(intentVertical)
        }
    }
}
