package vKotlin

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