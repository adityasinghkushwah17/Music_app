package com.example.musicappui.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.musicappui.R

@Composable
fun Subscription(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp, bottom =16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Manage Subscription")
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(end = 8.dp), Arrangement.SpaceBetween) {
            Row {
                Column {
                    Text(text = "Musical")
                    Text(text = "Free Tier")
                }
            }
            Row(modifier = Modifier
                .clickable { }
                .padding(8.dp)) {
                Text(text = "See All Plans ", color = Color.Blue)
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "See All Plans",
                    tint = Color.Blue)
            }

        }
        Divider(Modifier.padding(8.dp))
        Row(modifier = Modifier.padding(top=16.dp)) {
            Icon(painter = painterResource(id = R.drawable.baseline_account_box_24), contentDescription =null,
                Modifier.padding(start = 8.dp, end = 16.dp)
            )
            Text(text = "Get a Plan")

        }
        Divider(modifier = Modifier.padding(top = 8.dp))

    }
}