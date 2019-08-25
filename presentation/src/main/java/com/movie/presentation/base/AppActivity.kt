package com.movie.presentation.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.movie.presentation.R


class AppActivity : AppCompatActivity(R.layout.app_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showStatusBarOverlay()
    }

    private fun showStatusBarOverlay() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

}