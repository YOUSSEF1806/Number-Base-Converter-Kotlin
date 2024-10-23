package com.youyou

import kotlin.math.*

fun main() {
    var exitFlag = false
    do {
        print("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ")
        val input = readln()
        when (input) {
            "/from" -> {
                fromDecimal()
            }

            "/to" -> {
                toDecimal()
            }

            "/exit" -> {
                exitFlag = true
            }
        }
    } while (!exitFlag)

}

fun fromDecimal() {
    print("Enter a number in decimal system: ")
    val number = readln().toInt()
    print("Enter the target base: ")
    val base = readln().toInt()
    println("Conversion result: ${toBaseX(number, base)}")
}

fun toDecimal() {
    print("Enter source number: ")
    val sourceNumber = readln()
    print("Enter source base: ")
    val sourceBase = readln().toInt()
    println("Conversion to decimal result: ${toDecimal(sourceNumber, sourceBase)}")
}

fun toDecimal(sourceNumber: String, sourceBase: Int): Int {
    return sourceNumber.reversed()
        .mapIndexed { index, c ->
            c.toDecimalInt(sourceBase) * sourceBase.toDouble().pow(index).toInt()
        }
        .sum()
}

fun Char.toDecimalInt(sourceBase: Int): Int {
    if (sourceBase > 10 && !this.isDigit()) {
        return (this.uppercaseChar() - 'A') + 10
    }
    return (this - '0')
}

fun toBaseX(number: Int, base: Int): String {
    var quotient = number
    val listRemainders = mutableListOf<Char>()
    do {
        val remainder = quotient % base
        quotient /= base
        listRemainders.add(remainder.toBaseChar(base))
    } while (quotient >= base)
    listRemainders.add(quotient.toBaseChar(base))
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