package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(predicate: (x: Student) -> Boolean): List<Student> = students
        .asSequence()
        .filter(predicate)
        .toList()


    fun init(names: List<String>,
             surNames: List<String>,
             averageRates: List<Double>): List<Student> {
        students = mutableListOf()

        for (i in names.indices) {
            (students as MutableList<Student>).add(Student(firstName = names[i],
                lastName = surNames[i],
                averageRate = averageRates[i]))
        }
        return students
    }
}
