package com.github.nitrico.lastadapter_sample.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*
import com.github.nitrico.lastadapter_sample.R

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        if (intent.getBooleanExtra("notification", false)) { //Just for confirmation
            txtTitleView.text = intent.getStringExtra("title")
            txtMsgView.text = intent.getStringExtra("message")

        }
    }
}