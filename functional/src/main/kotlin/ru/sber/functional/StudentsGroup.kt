package ru.sber.functional

import java.util.function.Predicate

class StudentsGroup(names: List<String> = listOf("Alex", "Sergey", "Arnold", "Lucas", "Rachel", "Donald"),
                    surNames: List<String> = listOf("Trump", "Donahue", "Andropov", "Parker", "Hegel", "Rasputin"),
                    averageRates: List<Double> = listOf(0.5, 2.6, 3.6, 4.7, 2.7, 3.1)) {

    val students: List<Student> = mutableListOf()

    fun filterByPredicate(predicate: (x: Student) -> Boolean): List<Student> = students
        .asSequence()
        .filter(predicate)
        .toList()


    init {
        for (i in names.indices) {
            (students as MutableList<Student>).add(Student(firstName = names[i],
                lastName = surNames[i],
                averageRate = averageRates[i],
                middleName = "",
                age = 0,
                city = "",
                specialization = "",
                prevEducation = ""))
        }
    }
}
