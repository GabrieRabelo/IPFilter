package vKotlin

import java.io.File
val data = mutableListOf<Interval>()

fun main() {
    val initialTime = System.currentTimeMillis()
    var linesRead = 0

    File("src/vKotlin/resource/cohen11.txt")
        .forEachLine {
            val lineValues = it.split("-".toRegex())
            val interval = Interval(lineValues[0].toInt(), lineValues[1].toInt())
            data.add(interval)
            linesRead++
        }

    data.sort()

    for(i in 1..data.size) {
        merger(data.removeAt(0))
    }

    println("Tamanho da lista " + data.size)
    println("Total de linhas lidas $linesRead")
    val endTime = System.currentTimeMillis()
    val executionTime = endTime - initialTime
    println("\n\nTempo de execução: $executionTime ms")
}

fun merger(interval: Interval) {
    for(i : Interval in data) {
        if(interval.firstValue <= i.firstValue && interval.lastValue >= i.firstValue
            && interval.lastValue <= i.lastValue) {
            i.mergeFirst(interval)
            return
        }

        if(interval.firstValue <= i.lastValue && interval.firstValue >= i.firstValue
            && interval.lastValue >= i.lastValue) {
            i.mergeLast(interval)
            return
        }

        if(interval.firstValue <= i.firstValue && interval.lastValue >= i.lastValue){
            i.mergeFirst(interval)
            i.mergeLast(interval)
            return
        }
    }
    data.add(interval)
}