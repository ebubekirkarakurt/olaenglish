package com.ekasoftware.english.view.chatbot.ui


import android.annotation.SuppressLint
import android.graphics.Paint.Align
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.CardDefaults
import com.ekasoftware.english.R
import com.ekasoftware.english.assets.Screen
import com.ekasoftware.english.ui.theme.UiColor
import com.ekasoftware.english.view.chatbot.data.QABotData


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatScreen(navController: NavHostController) {



    Scaffold(
        topBar = {
            ChatTopBar(navController)
        }
    ) {paddingValues->
        Modifier.padding(paddingValues)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(navController: NavHostController) {

    val botData = QABotData.getDefault()
    var answer by remember { mutableStateOf("") }

    var userButtons = listOf(
        "merhaba",
        "nasılsınız",
        "hava nasıl"
    )

    var botAnswers = listOf(
        botData.merhaba,
        botData.nasilsiniz,
        botData.havaNasil
    )


    Column {
        TopAppBar(
            title = { Text(
                modifier = Modifier.padding(5.dp),
                text = "Çevrimiçi") },
            modifier = Modifier.background(Color.Blue),
            navigationIcon = {
                Row {
                    Icon(imageVector = Icons.Filled.KeyboardArrowLeft,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(5.dp)
                            .clickable {
                            navController.navigate(Screen.Home.route)
                        },
                        contentDescription = "")
                    Image(
                        painter = painterResource(id = R.drawable.koalachat),
                        contentDescription = "img",
                        Modifier.size(40.dp)
                    )
                }
            }
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            Card(
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(10.dp),
                colors = androidx.compose.material3.CardDefaults.cardColors(
                    Color.White
                ),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.koalachat),
                        contentDescription = "img",
                        Modifier.size(40.dp)
                    )

                    Text(
                        text = botData.merhaba,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(5.dp),
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
            }

            Column(Modifier.padding(10.dp)) {

                userButtons.forEachIndexed { index, string ->

                    Button(
                        onClick = {
                            answer = botAnswers[index]
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Black,
                        ),
                        border = BorderStroke(0.5.dp, Color.Black)
                    ) {
                        Text(text = string)
                    }

                }
                if(answer !== ""){
                    Spacer(modifier = Modifier.height(16.dp))
                    Answer(answer = answer)
                }
            }
        }
    }
}

@Composable
fun Answer(answer: String) {

    val botData = QABotData.getDefault()
    var secondanswer by remember { mutableStateOf("") }

    var userButtons2 = listOf(
        "çalışma",
        "çalışma",
        "çalışma"
    )

    var botAnswers2 = listOf(
        botData.merhaba,
        botData.nasilsiniz,
        botData.havaNasil
    )

    Card(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(10.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            Color.White
        ),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {

            Image(
                painter = painterResource(id = R.drawable.koalachat),
                contentDescription = "img",
                Modifier.size(40.dp)
            )
            Text(
                text = answer,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(5.dp),
                fontSize = 16.sp,
                color = Color.Black
                )
        }

    }
    Column {
        userButtons2.forEachIndexed { index, string ->
            Button(
                onClick = {
                    secondanswer = botAnswers2[index]
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black,
                ),
                border = BorderStroke(0.5.dp, Color.Black)
            ) {
                Text(text = string)
            }

        }
        if(secondanswer !== "") {
            Card(
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(10.dp),
                colors = androidx.compose.material3.CardDefaults.cardColors(
                    Color.White
                ),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.koalachat),
                        contentDescription = "img",
                        Modifier.size(40.dp)
                    )
                    println(secondanswer)
                    Text(
                        text = secondanswer,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(5.dp),
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewChatScreen() {
    ChatScreen(rememberNavController())
}