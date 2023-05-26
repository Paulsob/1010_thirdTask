package com.irregulariti.myapplication.ui

import android.util.ArraySet

class Field {
    var field: ArrayList<ArrayList<Int>>? = null
    var score = 0

    // заполнение
    fun filling() {
        for (i in 0..9) {
            for (j in 0..9) {
                field!![j].add(0)
            }
        }
    }

    // получить значение ячейки поля
    fun getStatement(cell: Pair<Int?, Int?>): Int {
        return field!![cell.first!!][cell.second!!]
    }

    // установить значение ячейки поля
    fun setStatement(pair: Pair<Int, Int>, state: Int) {
        val x = pair.first
        val y = pair.second
        field!![y][x] = state
        score += state
    }

    // проверка на заполненность колонки
    fun getStatementColumn(n: Int): Int {
        val set = ArraySet<Int>()
        for (i in 0..9) set.add(field!![i][n - 1])
        return if (set.size == 1 && set.contains(1)) 0 else 1
    }

    // очищение колонки
    fun clearColumn(n: Int) {
        for (i in 0..9) {
            val pair = Pair(i, n - 1)
            setStatement(pair, 0)
        }
    }

    // проверка на заполненность строки
    fun getStatementRow(n: Int): Int {
        val set = ArraySet<Int>()
        for (i in 0..9) set.add(field!![n - 1][i])
        return if (set.size == 1 && set.contains(1)) 0 else 1
    }

    // очищение строки
    fun clearRow(n: Int) {
        for (i in 0..9) {
            val pair = Pair(n - 1, i)
            setStatement(pair, 0)
        }
    }
}