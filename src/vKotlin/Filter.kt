package vKotlin

class Filter(private var data: MutableList<Interval>) {
    fun add(interval: Interval) {
        if(data.isEmpty()) {
            data.add(interval)
            return
        }
            checkValues(interval)
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

        }

        data.add(inputInterval)

    }

    fun organize() {
        for(item: Interval in data) {

        }
    }


    override fun toString(): String {
        return data.toString();
    }
}