package vKotlin

import java.io.File

fun main() {
    val initialTime = System.currentTimeMillis()
    val filter = Filter(mutableListOf())

    File("src/vKotlin/resource/casoenunciado.txt")
        .forEachLine {
            val lineValues = it.split("-".toRegex())
            val interval = Interval(lineValues[0].toInt(), lineValues[1].toInt())

            filter.add(interval)
        }

    println("Tamanho da lista " + filter.getSize())

    val endTime = System.currentTimeMillis()
    val executionTime = endTime - initialTime
    println("\n\nTempo de execução: $executionTime ms")


}

class Interval (var firstValue: Int, var lastValue: Int): Comparable<Interval> {
    override fun compareTo(other: Interval): Int {
        return if (this.firstValue != other.firstValue) {
            this.firstValue - other.firstValue
        } else 0
    }

    fun mergeFirst(interval: Interval) {
        this.firstValue = interval.firstValue
    }

    fun mergeLast(interval: Interval) {
        this.lastValue = interval.lastValue
    }

    override fun toString(): String {
        return "$firstValue - $lastValue"
    }
}

class Filter(private var data: MutableList<Interval>) {
    fun add(interval: Interval) {
        if(data.isEmpty()) {
            data.add(interval)
            return
        }
        checkValues(interval)

        val tamanho = data.size
        for(i in 1..tamanho) {
            checkValues(data.removeFirst())
        }
    }

    private fun checkValues(inputInterval: Interval) {
        for(i : Interval in data) {
            if(inputInterval.firstValue <= i.firstValue && inputInterval.lastValue >= i.firstValue
                && inputInterval.lastValue <= i.lastValue) {
                i.mergeFirst(inputInterval)
                return
            }

            if(inputInterval.firstValue <= i.lastValue && inputInterval.firstValue >= i.firstValue
                && inputInterval.lastValue >= i.lastValue) {
                i.mergeLast(inputInterval)
                return
            }

            if(inputInterval.firstValue <= i.firstValue && inputInterval.lastValue >= i.lastValue){
                i.mergeFirst(inputInterval)
                i.mergeLast(inputInterval)
                return
            }
        }
        data.add(inputInterval)
    }

    fun getSize():Int {
        return data.size
    }

    override fun toString(): String {
        return data.toString();
    }
}