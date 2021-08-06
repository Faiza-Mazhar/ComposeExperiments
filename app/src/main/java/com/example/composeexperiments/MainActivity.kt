package com.example.composeexperiments

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composeexperiments.ui.theme.ComposeExperimentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExperimentsTheme {
                MessageList(messages = SampleData.conversationSample) {
                    Log.d(it.body, it.author)
                }
            }
        }
    }
}

