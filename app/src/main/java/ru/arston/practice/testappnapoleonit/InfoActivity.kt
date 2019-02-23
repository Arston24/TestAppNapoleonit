package ru.arston.practice.testappnapoleonit

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        var closeInfo: ImageView = findViewById(R.id.close_info)

        closeInfo.setOnClickListener {
            val intent = Intent(this, InfoActivity:: class.java)
            // finishActivity(1)
            super.onBackPressed()
        }
    }
}
