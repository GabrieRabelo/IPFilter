package vKotlin

import java.io.File
import kotlin.math.max

class Interval (var firstValue: Int, var lastValue: Int): Comparable<Interval> {
    override fun compareTo(other: Interval): Int {
        return if (this.firstValue != other.firstValue) {
            this.firstValue - other.firstValue
        } else 0
    }

    fun merge(interval: Interval) {
        this.lastValue = max(this.lastValue, interval.lastValue)
    }
}

fun main() {
    val initialTime = System.currentTimeMillis()
    val data = mutableListOf<Interval>()
    val finalData = mutableListOf<Interval>()

    File("src/vKotlin/resource/cohen12.txt")  //leitura do arquivo para adicionar ao data
        .forEachLine {
            val lineValues = it.split("-".toRegex())
            val interval = Interval(lineValues[0].toInt(), lineValues[1].toInt())
            data.add(interval)
        }

    data.sort()

    var currentInterval = data[0]
    finalData.add(currentInterval) //adiciona o primeiro intervalo (menor first value)

    for (nextInterval in data) {
        if (currentInterval.lastValue >= nextInterval.firstValue) {
            //compara se o lastValue do primeiro intervalo é maior ou igual
            // que o first value do próximo
            currentInterval.merge(nextInterval) //faz o merge do intervalo
        } else {
            currentInterval = nextInterval //currentInterval recebe a referencia do próximo intervalo
            finalData.add(currentInterval) //adiciona o currentInterval no finalData
        }
    }

    println("Tamanho da lista inicial " + data.size)
    println("Tamanho da lista final " + finalData.size)
    val endTime = System.currentTimeMillis()
    val executionTime = endTime - initialTime
    println("Tempo de execução: $executionTime ms")
}