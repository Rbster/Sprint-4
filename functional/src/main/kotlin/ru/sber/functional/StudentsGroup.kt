package ru.sber.functional

class StudentsGroup(names: List<String>,
                    surNames: List<String>,
                    averageRates: List<Double>,
                    middleNames: List<String> = List(names.size) { "" },
                    ages: List<Int> = List(names.size) { 0 },
                    cities: List<String> = List(names.size) { "" },
                    specializations: List<String> = List(names.size) { "" },
                    prevEducations: List<String> = List(names.size) { "" }) {

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
                middleName = middleNames[i],
                age = ages[i],
                city = cities[i],
                specialization = specializations[i],
                prevEducation = prevEducations[i]))
        }
    }
}
