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
        val result = StudentsGroup(names = listOf("Alex", "Sergey", "Arnold", "Lucas", "Rachel", "Donald"),
                surNames = listOf("Trump", "Donahue", "Andropov", "Parker", "Hegel", "Rasputin"),
                averageRates = listOf(0.5, 2.6, 3.6, 4.7, 2.7, 3.1))
            .filterByPredicate { it.averageRate > 4.5 }
        assertEquals(1, result.size)
        assertEquals("Lucas", result[0].firstName)
        assertEquals("Parker", result[0].lastName)
        assertEquals(4.7, result[0].averageRate)

    }
}
