package ru.sber.functional

class StudentsGroup(names: List<String>,
                    surNames: List<String>,
                    averageRates: List<Double>) {

    val students: List<Student> = mutableListOf()

    fun filterByPredicate(predicate: (x: Student) -> Boolean): List<Student> = students
        .asSequence()
        .filter(predicate)
        .toList()


    init {
        for (i in names.indices) {
            (students as MutableList<Student>).add(Student(firstName = names[i],
                lastName = surNames[i],
                averageRate = averageRates[i]))
        }
    }
}
