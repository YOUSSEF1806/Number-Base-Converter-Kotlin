package com.youyou

import java.math.BigInteger

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
                if (sourceBase.toInt() in (2..36) &&  targetBase.toInt() in (2..36)) {
                    do {
                        print("Enter number in base $sourceBase to convert to base $targetBase (To go back type /back) ")
                        val numberToConvert = readln()
                        if (numberToConvert != "/back") {
                            val conversionResult =
                                toBaseX(toDecimal(numberToConvert, sourceBase.toInt()), targetBase.toInt())
                            println("Conversion result: $conversionResult\n")
                        }
                    } while (numberToConvert != "/back")
                } else
                    println("Source base and target base must be between 2 and 36 included !! \n")
            }
        }
    } while (!exitFlag)

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
    val listRemainders = mutableListOf<Char>()
    do {
        val remainder = quotient.remainder(base.toBigInteger())
        quotient /= base.toBigInteger()
        listRemainders.add(remainder.toInt().toBaseChar(base))
    } while (quotient >= base.toBigInteger())
    listRemainders.add(quotient.toInt().toBaseChar(base))
    return listRemainders
        .apply { if (this.last() == '0') listRemainders.removeLast() }
        .reversed()
        .joinToString("")
}

fun Int.toBaseChar(base: Int): Char {
    if (this >= 10) {
        return 'A' + (this - 10)
    }
    return this.digitToChar()
}