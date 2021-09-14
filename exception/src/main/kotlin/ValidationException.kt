class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(1, "Недопустимый символ"),
    TOO_MANY_CHARACTERS(2, "Слишком много символов"),
    TOO_FEW_CHARACTERS(4, "Слишком мало символов"),
    WRONG_EMAIL_FORMAT(8, "Неверный формат почты"),
    WRONG_PHONE_FORMAT(16, "Неверный формат номера телефона"),
    BAD_SNILS_CONTROL_NUM(32, "Контрольный номер СНИЛС не совпадает"),
    MISSING_DATA(64, "Данные не найдены")
}