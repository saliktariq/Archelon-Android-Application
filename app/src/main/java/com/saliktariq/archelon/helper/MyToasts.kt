package com.saliktariq.archelon.helper

import android.content.Context
import android.view.Gravity
import android.widget.Toast

class MyToasts {

    /*
    Easy access to toast creation
     */
    companion object {
        fun showToast(message: String?, context: Context?) {
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, message, duration)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        }

        fun showToastWithGravity(message: String?, context: Context?, gravity: Int) {
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, message, duration)
            toast.setGravity(gravity, 0, 0)
            toast.show()
        }
    }

}