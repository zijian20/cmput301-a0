package com.example.assign0

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.assign0.ui.theme.Assign0Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assign0Theme {
                RandomChooser()
            }
        }
    }
}


// chooser widget with some random results of varying probability
// YES: 1/2
// MEH: 1/4
// NO:  1/10
@Composable
fun RandomChooser() {

    val defaultStr  = "Should we go?"
    val yesStr      = "Yes!"
    val noStr       = "No!"

    // return the yesStr with a random 1/oneOutOf chance
    // otherwise return the noStr
    fun yesOrNo(oneOutOf: Int): String {
        return if (Random.nextInt(0, oneOutOf) == 0) yesStr else noStr
    }

    // track what the current response message is and also the click count
    var response by remember { mutableStateOf(defaultStr) }
    var clicks by remember { mutableIntStateOf(0) }

    // this is the only widget in the app so it can fill all the availible space
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1.0f))    // idiom for fill all remaining space

        // response message display
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1.0f))
            Text(
                text = response,
                modifier = Modifier.padding(12.dp)
            )
            Spacer(modifier = Modifier.weight(1.0f))
        }

        // buttons row
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1.0f))

            Button(
                onClick = {
                    response = yesOrNo(2)
                    clicks++
                }
            ) {
                Text("Ok")
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = {
                    response = yesOrNo(4)
                    clicks++
                }
            ) {
                Text("Meh")
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = {
                    response = yesOrNo(10)
                    clicks++
                }
            ) {
                Text("Nah")
            }

            Spacer(modifier = Modifier.weight(1.0f))
        }

        // clicks counter
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1.0f))

            Text(
                text = "Clicks: $clicks",       // reminds me of perl...
                modifier = Modifier.padding(12.dp)
            )

            Spacer(modifier = Modifier.weight(1.0f))
        }

        Spacer(modifier = Modifier.weight(1.0f))

        // student id and ccid, this is prob going into a public repo so hii
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "zijian20",
                modifier = Modifier.padding(12.dp)
            )

            Spacer(modifier = Modifier.weight(1.0f))

            Text(
                text = "1850586",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}