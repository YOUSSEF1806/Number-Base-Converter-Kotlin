package com.youyou

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import java.util.*

fun main() {
    var exitFlag = false
    do {
        print("\nEnter two numbers in format: {source base} {target base} (To quit type /exit) ")
        val input = readln()
        when (input) {
            "/exit" -> {
                exitFlag = true
            }

            else -> {
                val (sourceBase, targetBase) = input.split(" ")
                if (sourceBase.toInt() in (2..36) && targetBase.toInt() in (2..36)) {
                    do {
                        print("Enter number in base $sourceBase to convert to base $targetBase (To go back type /back) ")
                        val numberToConvert = readln()
                        if (numberToConvert != "/back") {
                            val conversionResult =
                                controlFlowConversion(numberToConvert, sourceBase.toInt(), targetBase.toInt())
                            println("Conversion result: $conversionResult\n")
                        }
                    } while (numberToConvert != "/back")
                } else
                    println("Source base and target base must be between 2 and 36 included !! \n")
            }
        }
    } while (!exitFlag)

}

fun controlFlowConversion(number: String, sourceBase: Int, targetBase: Int): String {
    var fractionalPart = ""
    if (number.contains(".")) {
        val fractionalToDecimal = fractionalToDecimal(
            number.substringAfter("."),
            sourceBase
        )
        fractionalPart = fractionalToBaseX(
            fractionalToDecimal,
            targetBase
        )
    }
    return toBaseX(
        toDecimal(number.substringBefore("."), sourceBase),
        targetBase
    ) + if (fractionalPart.isNotBlank()) "." + fractionalPart.substringAfter(".") else fractionalPart
}

fun fractionalToDecimal(sourceNumber: String, sourceBase: Int): BigDecimal {
    if (sourceBase == 10)
        return "0.$sourceNumber".toBigDecimal()

    return sourceNumber
        .mapIndexed { index, c ->
            val powX = sourceBase.toBigDecimal().pow(index + 1)
            val charToDecimal = c.toDecimalInt(sourceBase)
            charToDecimal.toBigDecimal().divide(powX, 6, RoundingMode.HALF_EVEN)
        }.reduce { a, b -> a + b }
}

fun fractionalToBaseX(number: BigDecimal, base: Int): String {
    if (base == 10)
        return String.format(Locale.US, "%.5f", number)

    var quotient = number
    var listRemainders = ""
    do {
        val remainder = quotient.times(base.toBigDecimal()).toInt()
        quotient = quotient.times(base.toBigDecimal()) - remainder.toBigDecimal()
        listRemainders += remainder.toBaseChar(base)
    } while (quotient != 0.toBigDecimal() && listRemainders.length < 5)
    if (listRemainders.length < 5)
        listRemainders += List(5 - listRemainders.length) { '0' }.joinToString("")
    return listRemainders
}

fun toDecimal(sourceNumber: String, sourceBase: Int): BigInteger {
    if (sourceBase == 10)
        return sourceNumber.toBigInteger()

    return sourceNumber.reversed()
        .mapIndexed { index, c ->
            c.toDecimalInt(sourceBase).toBigInteger().times(sourceBase.toBigInteger().pow(index))
        }
        .reduce { a, b -> a + b }
}

fun Char.toDecimalInt(sourceBase: Int): Int {
    if (sourceBase > 10 && !this.isDigit()) {
        return (this.uppercaseChar() - 'A') + 10
    }
    return (this - '0')
}

fun toBaseX(number: BigInteger, base: Int): String {
    if (base == 10)
        return number.toString()

    var quotient = number
    var listRemainders = ""
    do {
        val remainder = quotient.remainder(base.toBigInteger())
        quotient /= base.toBigInteger()
        listRemainders = remainder.toInt().toBaseChar(base) + listRemainders
    } while (quotient >= base.toBigInteger())
    if (quotient.toInt() != 0)
        listRemainders = quotient.toInt().toBaseChar(base) + listRemainders
    return listRemainders
}

fun Int.toBaseChar(base: Int): Char {
    if (this >= 10) {
        return 'A' + (this - 10)
    }
    return this.digitToChar()
}