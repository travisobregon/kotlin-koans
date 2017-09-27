package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun MyDate.plus(timeInterval: TimeInterval) : MyDate = this.addTimeIntervals(timeInterval, 1)

operator fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval) : MyDate = this.addTimeIntervals(repeatedTimeInterval.ti, repeatedTimeInterval.n)

class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(n: Int) : RepeatedTimeInterval = RepeatedTimeInterval(this, n)

class DateRange(override val start: MyDate, override val endInclusive: MyDate): ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator() = object : Iterator<MyDate> {
        var current = start

        override fun hasNext() = current <= endInclusive

        override fun next() : MyDate = current.tap { current = current.nextDay() }
    }
}

fun <T : Any, R> T.tap(tap: (T) -> R): T {
    tap(this)
    return this
}
