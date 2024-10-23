package com.ending0421.multirepo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ending0421.multirepo.ui.theme.MulitiRepoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MulitiRepoTheme {
                AppNavHost()
            }
        }
    }
}