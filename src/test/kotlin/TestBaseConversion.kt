import com.youyou.*
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals


class TestBaseConversion {

    @Test
    fun testFractionalNumberConversion() {
        assertEquals("0", controlFlowConversion("0", 2, 10))
        assertEquals("0", controlFlowConversion("0", 2, 16))
        assertEquals("0.00000", controlFlowConversion("0.0", 2, 10))
        assertEquals("0.14315", controlFlowConversion("0.234", 10, 7))
        assertEquals("13.14315", controlFlowConversion("10.234", 10, 7))
        assertEquals("11113.22222", controlFlowConversion("1100001111.10000", 2, 5))
        assertEquals("38012", controlFlowConversion("4242", 21, 10))
        assertEquals("54E36.00000", controlFlowConversion("aaaa.0", 35, 17))
        assertEquals("148.G88A8", controlFlowConversion("af.xy", 35, 17))
    }

    @Test
    fun testFractionalPartToBaseX() {
        assertEquals( "00000" , fractionalToBaseX(0.toBigDecimal(), 2))
        assertEquals( "01000" , fractionalToBaseX(0.25.toBigDecimal(), 2))
        assertEquals( "10110" , fractionalToBaseX(0.68750.toBigDecimal(), 2))
        assertEquals( "11003" , fractionalToBaseX(0.06641.toBigDecimal(), 16))
        assertEquals( "14315" , fractionalToBaseX(0.234.toBigDecimal(), 7))

    }

    @Test
    fun testFractionalPartToDecimal() {
        assertEquals("0.00000", String.format(Locale.US, "%.5f",fractionalToDecimal("0", 10)))
        assertEquals("0.23400", String.format(Locale.US, "%.5f",fractionalToDecimal("234", 10)))
        assertEquals("0.00000", String.format(Locale.US, "%.5f",fractionalToDecimal("0", 2)))
        assertEquals("0.50000", String.format(Locale.US, "%.5f",fractionalToDecimal("1", 2)))
        assertEquals("0.75000", String.format(Locale.US, "%.5f",fractionalToDecimal("11", 2)))
        assertEquals("0.68750", String.format(Locale.US, "%.5f",fractionalToDecimal("1011", 2)))
        assertEquals("0.06641", String.format(Locale.US, "%.5f",fractionalToDecimal("11", 16)))
        assertEquals("0.35860", String.format(Locale.US, "%.5f",fractionalToDecimal("234", 7)))

    }

    @Test
    fun testToDecimal() {
        assertEquals(31.toBigInteger(), toDecimal("1f", 16))
        assertEquals(255.toBigInteger(), toDecimal("ff", 16))
        assertEquals(17.toBigInteger(), toDecimal("21", 8))
        assertEquals(57.toBigInteger(), toDecimal("71", 8))
        assertEquals(103.toBigInteger(), toDecimal("147", 8))
        assertEquals(15.toBigInteger(), toDecimal("1111", 2))
        assertEquals(57.toBigInteger(), toDecimal("111001", 2))
        assertEquals(101.toBigInteger(), toDecimal("1100101", 2))
    }

    @Test
    fun testToBaseX() {
        assertEquals("1F", toBaseX(31.toBigInteger(), 16))
        assertEquals("FF", toBaseX(255.toBigInteger(), 16))
        assertEquals("21", toBaseX(17.toBigInteger(), 8))
        assertEquals("71", toBaseX(57.toBigInteger(), 8))
        assertEquals("147", toBaseX(103.toBigInteger(), 8))
        assertEquals("1111", toBaseX(15.toBigInteger(), 2))
        assertEquals("111001", toBaseX(57.toBigInteger(), 2))
        assertEquals("1100101", toBaseX(101.toBigInteger(), 2))
    }

    @Test
    fun testToDecimalInt() {
        assertEquals(15, 'f'.toDecimalInt(16))
        assertEquals(10, 'A'.toDecimalInt(16))
        assertEquals(9, '9'.toDecimalInt(16))
        assertEquals(7, '7'.toDecimalInt(8))
        assertEquals(1, '1'.toDecimalInt(2))
        assertEquals(0, '0'.toDecimalInt(2))
    }

    @Test
    fun testToBaseChar() {
        assertEquals('A', 10.toBaseChar(16))
        assertEquals('F', 15.toBaseChar(16))
        assertEquals('7', 7.toBaseChar(8))
        assertEquals('0', 0.toBaseChar(2))
    }
}