package com.example.composeexperiments

import android.content.Context
import android.util.Log
import android.util.Log.DEBUG
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.composeexperiments.ui.theme.ComposeExperimentsTheme

data class Message(
    val author: String,
    val body: String)

@Composable
fun MessageList(messages: List<Message>, onClickListener: (Message) -> Unit) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message = message, onClickListener)
        }
    }
}
@Composable
fun MessageCard(message: Message, onClickListener: (Message) -> Unit) {

    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.profile_picture), 
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.primary, CircleShape)
        )
        
        Spacer(modifier = Modifier.width(2.dp))

        var isExpanded: Boolean by remember {
            mutableStateOf(false)
        }

        val context = LocalContext.current

        Column(modifier = Modifier.clickable(onClick = {
            showToast(context = context, message.body)
            isExpanded = !isExpanded
            onClickListener.invoke(message)
        })) {
            Text(
                text = "Hello ${message.author}",
                modifier = Modifier.padding(all = 4.dp),
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(shape = MaterialTheme.shapes.medium, elevation = 5.dp) {
                Text(
                    text = message.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body1)
            }
        }
    }

}

fun showToast(context: Context, msg:String){
    Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewGreetings() {
    ComposeExperimentsTheme {
        MessageList(messages = SampleData.conversationSample) {

        }
    }
}