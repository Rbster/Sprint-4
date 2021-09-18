package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PowFactoryTest {
    @Test
    fun `buildPowFunction should return lambda It should calculate to second power`() {
        // expect
         assertEquals(9, PowFactory.buildPowFunction(2)(3))
    }

    @Test
    fun `StudentsGroup test`() {
        // expect
        val result = StudentsGroup().filterByPredicate { it.averageRate > 4.5 }
        assertEquals(1, result.size)
        assertEquals("Lucas", result[0].firstName)
        assertEquals("Parker", result[0].lastName)
        assertEquals(4.7, result[0].averageRate)


    }
}
