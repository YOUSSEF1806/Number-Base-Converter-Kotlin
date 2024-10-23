import com.youyou.toBaseChar
import com.youyou.toBaseX
import com.youyou.toDecimal
import com.youyou.toDecimalInt
import kotlin.test.Test
import kotlin.test.assertEquals


class TestBaseConversion {

    @Test
    fun testToDecimal() {
        assertEquals(31, toDecimal("1f", 16))
        assertEquals(255, toDecimal("ff", 16))
        assertEquals(17, toDecimal("21", 8))
        assertEquals(57, toDecimal("71", 8))
        assertEquals(103, toDecimal("147", 8))
        assertEquals(15, toDecimal("1111", 2))
        assertEquals(57, toDecimal("111001", 2))
        assertEquals(101, toDecimal("1100101", 2))
    }

    @Test
    fun testToBaseX() {
        assertEquals("1f".uppercase(), toBaseX(31, 16))
        assertEquals("ff".uppercase(), toBaseX(255, 16))
        assertEquals("21", toBaseX(17, 8))
        assertEquals("71", toBaseX(57, 8))
        assertEquals("147", toBaseX(103, 8))
        assertEquals("1111", toBaseX(15, 2))
        assertEquals("111001", toBaseX(57, 2))
        assertEquals("1100101", toBaseX(101, 2))
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