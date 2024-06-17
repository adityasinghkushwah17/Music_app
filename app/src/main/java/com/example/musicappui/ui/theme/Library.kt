package com.example.musicappui.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicappui.DummyLib
import com.example.musicappui.libraries

@Composable
fun Library(){
    LazyColumn(){
        items(libraries){
            it->
            LibItem(lib =it )
        }
    }
}

@Composable
fun LibItem(lib:DummyLib){
    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(end = 8.dp), Arrangement.SpaceBetween) {
            Row {
                Icon(
                    painter = painterResource(id =lib.Icon),
                    contentDescription = lib.name, Modifier.padding(8.dp)
                )
                Text(text = lib.name, modifier = Modifier.padding(top = 8.dp))
              
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null)

            }
        }
    }

}