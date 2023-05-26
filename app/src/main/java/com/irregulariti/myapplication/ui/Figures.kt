package com.irregulariti.myapplication.ui

import androidx.compose.ui.graphics.Color
import java.util.Random

class Figures {
    var color: Color
    var count: Int
    var form: Array<Pair<Int, Int>>
    val random = Random()
    val n: Int

    init {
        val point = arrayOf(Pair(0, 0))
        val squareTwo = arrayOf(Pair(0, 0), Pair(0, 1), Pair(1, 0), Pair(1, 1))
        val squareThree = arrayOf(
            Pair(0, 0),
            Pair(0, 1),
            Pair(0, 2),
            Pair(1, 0),
            Pair(1, 1),
            Pair(1, 2),
            Pair(2, 0),
            Pair(2, 1),
            Pair(2, 2)
        )
        val horizontalLineTwo = arrayOf(Pair(0, 0), Pair(1, 0))
        val horizontalLineThree = arrayOf(Pair(0, 0), Pair(1, 0), Pair(2, 0))
        val verticalLineTwo = arrayOf(Pair(0, 0), Pair(0, 1))
        val verticalLineThree = arrayOf(Pair(0, 0), Pair(0, 1), Pair(0, 2))
        val cornerTwoLeftUp = arrayOf(Pair(0, 0), Pair(0, 1), Pair(1, 1))
        val cornerTwoRightDown = arrayOf(Pair(0, 0), Pair(1, 0), Pair(1, 1))
        val cornerTwoLeftDown = arrayOf(Pair(0, 0), Pair(1, 0), Pair(0, 1))
        val cornerTwoRightUp = arrayOf(Pair(0, 0), Pair(-1, 1), Pair(0, 1))
        val cornerThreeLeftUp =
            arrayOf(Pair(0, 0), Pair(0, 1), Pair(0, 2), Pair(1, 2), Pair(2, 2))
        val cornerThreeRightDown =
            arrayOf(Pair(0, 0), Pair(1, 0), Pair(2, 0), Pair(2, 1), Pair(2, 2))
        val cornerThreeLeftDown =
            arrayOf(Pair(0, 0), Pair(1, 0), Pair(2, 0), Pair(0, 1), Pair(0, 2))
        val cornerThreeRightUp =
            arrayOf(Pair(0, 0), Pair(0, 1), Pair(0, 2), Pair(-1, 2), Pair(-2, 2))
        val arrayOfFigures = arrayOf(
            point,
            squareTwo,
            squareThree,
            horizontalLineTwo,
            horizontalLineThree,
            verticalLineTwo,
            verticalLineThree,
            cornerTwoLeftUp,
            cornerTwoRightDown,
            cornerTwoLeftDown,
            cornerTwoRightUp,
            cornerThreeLeftUp,
            cornerThreeRightDown,
            cornerThreeLeftDown,
            cornerThreeRightUp
        )
        val colors = arrayOf(
            Color(255, 0, 0, 255),
            Color(0, 255, 0, 255),
            Color(255, 255, 0, 255),
            Color(156, 39, 176, 255),
            Color(33, 150, 243, 255),
            Color(255, 152, 0, 255),
            Color(0, 188, 212, 255),
            Color(0, 150, 136, 255),
            Color(244, 67, 54, 255),
            Color(233, 30, 99, 255),
            Color(76, 175, 80, 255),
            Color(205, 220, 57, 255)
//                "violet",
//                "green",
//                "blue",
//                "yellow",
//                "orange",
//                "pink",
//                "red",
//                "yellow",
//                "orange",
//                "pink",
//                "red",
//                "green",
//                "green",
//                "green",
//                "green",
//                "dark blue",
//                "dark blue",
//                "dark blue",
//                "dark blue"
        )
        n = random.nextInt(15)
        form = arrayOfFigures[n]
        count = form.size
        color = colors[random.nextInt(3)]
    }
}