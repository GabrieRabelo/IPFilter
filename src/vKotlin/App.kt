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

    override fun toString(): String {
        return "[$firstValue, $lastValue]"
    }
}

fun main() {
    val initialTime = System.currentTimeMillis()
    val data = mutableListOf<Interval>()
    val finalData = mutableListOf<Interval>()

    File("src/vKotlin/resource/cohen01.txt")
        .forEachLine {
            val lineValues = it.split("-".toRegex())
            val interval = Interval(lineValues[0].toInt(), lineValues[1].toInt())
            data.add(interval)
        }
    data.sort()
    println("Tamanho da lista inicial " + data.size)

    var currentInterval = data.removeAt(0)
    finalData.add(currentInterval)

    for (nextInterval in data) {
        if (currentInterval.lastValue >= nextInterval.firstValue) {
            currentInterval.merge(nextInterval)
        } else {
            currentInterval = nextInterval
            finalData.add(currentInterval)
        }
    }

    println(finalData)

    println("Tamanho da lista final " + finalData.size)
    val endTime = System.currentTimeMillis()
    val executionTime = endTime - initialTime
    println("Tempo de execução: $executionTime ms")
}