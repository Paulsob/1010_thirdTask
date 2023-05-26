package com.third.myapplication.ui

class Game {

    var field = Field()

    var figuresTempArray: ArrayList<Figures>? = null

    fun start(): ArrayList<Figures> {
        val figure1 = Figures()
        val figure2 = Figures()
        val figure3 = Figures()
        val figures: ArrayList<Figures> = ArrayList()
        figures.add(figure1)
        figures.add(figure2)
        figures.add(figure3)
        return figures
    }

    fun place(figure: Figures, cell: Pair<Int, Int>) {
        val x = cell.first
        val y = cell.second
        for (i in 0 until figure.form.size) {
            val curX = figure.form.get(i).first + x
            val curY = figure.form.get(i).second + y
            field.setStatement(Pair(curX, curY), 1)
        }
        figuresTempArray!!.remove(figure)
    }

    fun step(figure: Figures, cell: Pair<Int, Int>): Int {
        val x = cell.first
        val y = cell.second
        var indicator = true
        for (i in 0 until figure.form.size) {
            val curX = figure.form.get(i).first + x
            val curY = figure.form.get(i).second + y
            if (curX >= 0 && curX <= 9 && curY >= 0 && curY <= 9) {
                if (field.getStatement(Pair(curX, curY)) == 1) {
                    indicator = false
                    break
                }
            } else {
                indicator = false
                break
            }
        }
        return if (indicator) 1 else 0
    }

    fun play(pair: Pair<Int, Int>): Int {
        figuresTempArray = start()
        while (true) {
            var count = 0
            for (i in figuresTempArray!!.indices) {
                for (j in 0..9) {
                    for (k in 0..9) {
                        count += step(figuresTempArray!![i], Pair(j, k))
                    }
                }
            }
            if (count != 0) {
                if (!figuresTempArray!!.isEmpty()) {
                    val figure: Figures = figuresTempArray!![0]
                    if (step(figure, pair) == 1) {
                        place(figure, pair)
                    }
                } else {
                    figuresTempArray = start()
                }
                for (i in 0..9) {
                    if (field.getStatementRow(i) == 0) field.clearRow(i)
                    if (field.getStatementColumn(i) == 0) field.clearColumn(i)
                }
            } else {
                return 0
            }
        }
    }
}
