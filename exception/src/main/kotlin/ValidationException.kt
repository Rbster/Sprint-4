class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(1, "Недопустимый символ"),
    // ...
    TOO_MANY_CHARACTERS(2, "Слишком много символов"),
    TOO_FEW_CHARACTERS(4, "Слишком мало символов"),
    WRONG_FORMAT(8, "Неверный формат данных"),
    MISSING_DATA(16, "Данные не найдены")
}