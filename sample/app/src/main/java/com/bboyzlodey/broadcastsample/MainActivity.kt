package com.bboyzlodey.broadcastsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bboyzlodey.broadcast.onBroadcastReceiveString
import com.bboyzlodey.broadcastsample.ui.theme.BroadcastSampleTheme
import kotlinx.coroutines.flow.MutableSharedFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BroadcastSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val greetingText by broadcastSample_1().collectAsState(initial = "Android")
                    Greeting(greetingText)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BroadcastSampleTheme {
        Greeting("Android")
    }
}

private fun MainActivity.broadcastSample_1(): kotlinx.coroutines.flow.Flow<String> {
    val sharedFlow = MutableSharedFlow<String>(replay = 0, extraBufferCapacity = 1)
    onBroadcastReceiveString {
        sharedFlow.tryEmit(it)
    }
    return sharedFlow
}