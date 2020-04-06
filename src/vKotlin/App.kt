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

    var firstInterval = data[0]
    finalData.add(firstInterval) //adiciona o primeiro intervalo (menor first value)

    for (interval in data) {
        if (firstInterval.lastValue >= interval.firstValue) { //compara se o lastValue do primeiro intervalo é maior que o first value do próximo
            firstInterval.merge(interval) //faz o merge do intervalo
        } else {
            firstInterval = interval //se não o primeiro intervalo recebe o valor do próximo
            finalData.add(firstInterval) //cria um novo intervalo no finalData
        }
    }

    println("Tamanho da lista inicial " + data.size)
    println("Tamanho da lista final " + finalData.size)
    val endTime = System.currentTimeMillis()
    val executionTime = endTime - initialTime
    println("Tempo de execução: $executionTime ms")
}