package app.bako.view.navigation.popup

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import app.bako.R

class AddWorkCodePopup:Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_manage_workcode)

        val dm:DisplayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        val width = dm.widthPixels
        val height = dm.heightPixels

        window.setLayout(width, height)

    }
}
