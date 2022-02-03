import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt

fun main(args: Array<String>) {
    var today = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
    // below creates a value "formattedToday" = variable (today) format (via formatter)
    // unformatted today = "YYYY-MM-DD" (not a string), formattedToday = "MM/DD/YYYY" and becomes a string
    var year: Int = Calendar.getInstance().get(Calendar.YEAR)

    var full_moon_interval = 29.53
    var next_full_moon = LocalDate.of(2022, Month.FEBRUARY, 16)

    var userdate = next_full_moon

    println("Program will calculate the next full moon either from today or from some selected day in 2022")
    println("Unfortunately, there may be some rounding off error and the date may be off by 1 day")

    print("Enter 0 to use today's date, else enter a numerical month of 2022 (ie: June = 6): ")
    var tempMonth: Int = readLine()!!.toInt()

    if (tempMonth!=0) {
        print("Enter a day of that month (ie: 1 ... #days in that month): ")
        var tempDay: Int = readLine()!!.toInt()
        userdate = LocalDate.of(year, tempMonth, tempDay)
    }
    else userdate = today
    println("The date we will use is ${userdate.format(formatter)}")

    var tempdate = next_full_moon
    var increment = 0F

    // If user entry date is in EARLY 2022, must subtract from Current Next Full Moon to calculate that Next Full Moon
    while (userdate.isBefore(tempdate.minusDays(full_moon_interval.toLong()))) {
        increment = (increment + full_moon_interval).toFloat()
        tempdate = next_full_moon.minusDays(increment.roundToInt().toLong())
    }

    // If user entry date is in LATE 2022, must add to Current Next Full Moon to calculate that Next Full Moon
    while (userdate.isAfter(tempdate)) {
        increment = (increment + full_moon_interval).toFloat()
        tempdate = next_full_moon.plusDays(increment.roundToInt().toLong())
    }
//    println("Unformatted date of the next full moon after your input date will be $tempdate")
    println("Formatted date of the next full moon after your input date will be ${tempdate.format(formatter)}")
}
