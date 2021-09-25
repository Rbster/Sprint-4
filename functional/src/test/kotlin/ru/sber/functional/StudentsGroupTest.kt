package ru.sber.functional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class StudentsGroupTest {

    @Test
    fun `StudentsGroup test`() {

        val studentsGroup = StudentsGroup()
        studentsGroup.init(names = listOf("Alex", "Sergey", "Arnold", "Lucas", "Rachel", "Donald"),
            surNames = listOf("Trump", "Donahue", "Andropov", "Parker", "Hegel", "Rasputin"),
            averageRates = listOf(0.5, 2.6, 3.6, 4.7, 2.7, 3.1))

        val result = studentsGroup.filterByPredicate { it.averageRate > 4.5 }
        assertEquals(1, result.size)
        assertEquals("Lucas", result[0].firstName)
        assertEquals("Parker", result[0].lastName)
        assertEquals(4.7, result[0].averageRate)

    }
}