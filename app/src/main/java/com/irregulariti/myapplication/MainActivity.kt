package com.irregulariti.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irregulariti.myapplication.ui.Figures
import com.irregulariti.myapplication.ui.Game
import java.lang.Math.abs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val game = Game()
            val choose = remember { mutableStateListOf<Pair<Int, Int>>() }
            var rightColor = remember { mutableStateOf<Color>(Color.Black) }
            val rightScore = remember { mutableStateOf<Int>(0) }
            val listOfFigures = remember { mutableStateOf(game.start()) }
            var figure = Figures()
            val removedFigure = remember { mutableStateOf(figure) }
            Box(
                Modifier
                    .fillMaxSize()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .absolutePadding(0.dp, 505.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .clickable(onClick = {
                                choose.clear()
                                for (pair in figure.form) {
                                    choose.add(pair)
                                }
                                rightColor = mutableStateOf(figure.color)
                            })
                            .width(90.dp)
                            .height(150.dp)
                    ) {
                        Column(modifier = Modifier) {
                            for (k in 0..2) {
                                Row(modifier = Modifier) {
                                    for (j in 0..2) {
                                        var color = Color.White
                                        if (figure.n != 10 && figure.n != 14) {
                                            val coord = Pair(j, abs(k - 2))
                                            if (coord in figure.form) {
                                                color = figure.color
                                            }
                                        } else if (figure.n == 14) {
                                            val coord = Pair(j, abs(k - 2))
                                            if (coord in figure.form) {
                                                color = figure.color
                                            }
                                        } else {
                                            val coord = Pair(abs(k - 2), abs(j - 1))
                                            if (coord in figure.form) {
                                                color = figure.color
                                            }
                                        }
                                        TextField(
                                            value = "1",
                                            onValueChange = { removedFigure.value.form },
                                            readOnly = true,
                                            modifier = Modifier
                                                .width(30.dp)
                                                .height(30.dp),
                                            colors = TextFieldDefaults.textFieldColors(
                                                backgroundColor = color,
                                                focusedIndicatorColor = Color.Transparent,
                                                unfocusedIndicatorColor = Color.Transparent,
                                                disabledIndicatorColor = Color.Transparent
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            val state = remember { mutableStateListOf<Pair<Boolean, Color>>() }
            val value = mutableListOf<Pair<Boolean, Color>>().apply {
                repeat(100) {
                    add(
                        Pair(
                            false,
                            Color(234, 235, 222, 255)
                        )
                    )
                }
            }
            state.addAll(value)

            fun clearRow() {
                for (i in state.indices step 10) {
                    for (j in 0..9) {
                        if (state[i + j].first == false) break
                        if (j == 9) {
                            rightScore.value += 10
                            for (k in 0..9) {
                                state[i + k] = Pair(false, Color(234, 235, 222, 255))
                            }
                        }
                    }
                }
            }

            fun clearColumn() {
                for (i in 0..9) {
                    for (j in state.indices step 10) {
                        if (state[i + j].first == false) break
                        if (j == 90) {
                            rightScore.value += 10
                            for (k in state.indices step 10) {
                                state[i + k] = Pair(false, Color(234, 235, 222, 255))
                            }
                        }
                    }
                }
            }

            fun checkHorizontalBroads(index: Int): Boolean {
                for (pair in choose) {
                    if (pair.first + index % 10 >= 10) return false
                }
                return true
            }

            fun checkVerticalBroads(index: Int): Boolean {
                for (pair in choose) {
                    if (index / 10 - pair.second < 0) return false
                }
                return true
            }

            fun updateButtonStates(index: Int) {
                val arrayExample = mutableListOf<Int>();
                for (pair in choose) {
                    if (checkHorizontalBroads(index) &&
                        checkVerticalBroads(index) && !state[index + pair.first - pair.second * 10].first
                    ) {
                        arrayExample.add(index + pair.first - pair.second * 10)
                        state[index + pair.first - pair.second * 10] = Pair(true, rightColor.value)
                        rightScore.value++
                    } else {
                        for (i in arrayExample) {
                            state[i] = Pair(false, Color(234, 235, 222, 255))
                        }
                        break
                    }
                }
                choose.clear()
                clearRow()
                clearColumn()
            }

            Text(
                text = "${rightScore.value}",
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(5.dp),
                fontSize = 50.sp, textAlign = TextAlign.Center
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(10),
                modifier = Modifier.padding(5.dp, 70.dp)
            ) {
                items(100) { index ->
                    Box(
                        modifier = Modifier
                            .padding(1.dp)
                            .width(32.dp)
                            .height(32.dp)
                            .background(state[index].second)
                            .clickable(onClick = { updateButtonStates(index) })
                    )
                }
            }
        }
    }

    @Composable
    fun DefaultPreview() {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            for (i in 1..3) {
                Box(
                    modifier = Modifier
                        .width(90.dp)
                        .height(270.dp)
                ) {
                    Column(modifier = Modifier) {
                        for (k in 1..3) {
                            Row(modifier = Modifier) {
                                for (j in 1..3) {
                                    TextField(
                                        value = "1", onValueChange = {},
                                        readOnly = true,
                                        modifier = Modifier
                                            .width(30.dp)
                                            .height(30.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}