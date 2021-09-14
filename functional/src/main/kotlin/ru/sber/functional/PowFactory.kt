package ru.sber.functional

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int): (Int) -> Int {
        return {x: Int ->
            var result = 1
            for (i in 1..pow) {
                result *= x
            }
            result
        }
    }
}
