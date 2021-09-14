package ru.sber.functional

import java.util.function.Predicate

class StudentsGroup {

    var students: List<Student> = init()

    fun filterByPredicate(predicate: (x: Student) -> Boolean): List<Student> {
        val result = mutableListOf<Student>()
        for (student in students) {
            if (predicate(student)) {
                result.add(student)
            }
        }
        return result
    }

    fun init(): List<Student> {
        val names = listOf("Alex", "Sergey", "Arnold", "Lucas", "Rachel", "Donald")
        val surNames = listOf("Trump", "Donahue", "Andropov", "Parker", "Hegel", "Rasputin")
        return listOf(Student(names[0], surNames[0], "", 0,4.3,"","",""),
            Student(names[1], surNames[1], "", 0,4.3,"","",""),
            Student(names[2], surNames[2], "", 0,5.0,"","",""),
            Student(names[3], surNames[3], "", 0,1.4,"","",""),
            Student(names[4], surNames[4], "", 0,3.6,"","",""),
            Student(names[5], surNames[5], "", 0,2.1,"","",""),
        )

    }
}
