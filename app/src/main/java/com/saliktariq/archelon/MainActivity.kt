package com.saliktariq.archelon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    /**
     * This is the Entry point into the Archelon application.
     * This activity loads activity_main layout which contains
     * the navigation graph used in the application.
     *
     * The design principle used in this application is to use
     * single activity and multiple fragments within that single activity
     *
     * **Salik Tariq**
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
