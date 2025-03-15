package com.example.taxbandits.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taxbandits.presentation.state.ChatMessageState
import com.example.taxbandits.presentation.viewmodel.ChatViewModel


@SuppressLint("SuspiciousIndentation")
@Composable
 fun ChatScreen(
    viewmodel: ChatViewModel = hiltViewModel()
){
    val state by viewmodel.state.collectAsStateWithLifecycle()
    ChatScreenContent(state = state,
        viewmodel = viewmodel)
}

@Preview(showBackground = true, showSystemUi = true)
@Preview
@Composable
private fun Preview(){
    ChatScreenContent(state = emptyList(), viewmodel = viewModel())
}


@Composable
fun ChatScreenContent(state: List<ChatMessageState>,
                      viewmodel: ChatViewModel) {
    var userMessage by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(bottom = 30.dp)) {
        LazyColumn(Modifier.weight(1f)) {
            items(state) { message ->
                message.text?.let { ChatBubble(it, message.isUser) }
            }
        }

        Row(Modifier.padding(8.dp)) {
            TextField(
                value = userMessage,
                onValueChange = { userMessage = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type a message...") }
            )
            Button(
                onClick = {
                    // Trigger sending a message here
                    viewmodel.sendMessage(userMessage)
                    userMessage = ""
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Send")
            }
        }
    }
}


@Composable
fun ChatBubble(text: String, isUser: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = if (isUser) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Text(
            text,
            Modifier
                .background(if (isUser) Color.Blue else Color.Gray, shape = RoundedCornerShape(8.dp))
                .padding(8.dp),
            color = Color.White
        )
    }
}
