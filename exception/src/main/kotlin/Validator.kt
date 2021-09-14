import java.util.regex.Pattern

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val exactRequiredLength = 11
        val result = mutableListOf<ErrorCode>()
        if (value == null) {
            result.add(ErrorCode.MISSING_DATA)
        } else {
            if (value.any { !it.isDigit() }) {
                result.add(ErrorCode.INVALID_CHARACTER)
            }
            if (value.length > exactRequiredLength) {
                result.add(ErrorCode.TOO_MANY_CHARACTERS)
            } else if (value.length < exactRequiredLength) {
                result.add(ErrorCode.TOO_FEW_CHARACTERS)
            }
            if (value.isEmpty() ||
                ((value[0] != '8') ==
                (value[0] != '7'))) {

                result.add(ErrorCode.WRONG_PHONE_FORMAT)
            }
        }
        return result
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val result = mutableListOf<ErrorCode>()
        val maxRequiredLength = 16

        if (value == null) {
            result.add(ErrorCode.MISSING_DATA)
        } else {
            if (value.length > maxRequiredLength) {
                result.add(ErrorCode.TOO_MANY_CHARACTERS)
            }

            if (value
                    .chars()
                    .mapToObj(Character.UnicodeBlock::of)
                    .anyMatch { ! it.equals(Character.UnicodeBlock.CYRILLIC) }) {

                result.add(ErrorCode.INVALID_CHARACTER)
            }

        }
        return result
    }
}

class MailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val result = mutableListOf<ErrorCode>()
        val maxRequiredLength = 32
        val pattern = Pattern.compile(
            "[a-zA-Z]+" +
                "@" +
                "[a-zA-Z]+" +
                "(\\." +
                "[a-zA-Z]+" +
                ")*"
        )

        if (value == null) {
            result.add(ErrorCode.MISSING_DATA)
        } else {
            if (value.length > maxRequiredLength) {
                result.add(ErrorCode.TOO_MANY_CHARACTERS)
            }
            if (value.any { !(it.isLetter() ||
                        it == '@' ||
                        it == '.') }) {

                result.add(ErrorCode.INVALID_CHARACTER)
            }

            if (!pattern.matcher(value).matches()) {
                result.add(ErrorCode.WRONG_EMAIL_FORMAT)
            }

        }
        return result
    }
}

class SnilsValidator : Validator<String>() {

    private fun validateControlNum(snils: String): Boolean {
        val trueControlNumber = "${snils[9]}${snils[10]}".toIntOrNull()
        var thisControlNumber = 0
        var thisDigit: Int?
        if (snils.slice(0..8) < "001001998") {
            return false
        }
        for (i in 0..8) {
            thisDigit = snils[i].digitToIntOrNull()
            if (thisDigit != null) {
                thisControlNumber += thisDigit * (9 - i)
            } else {
                return false
            }
        }
        if (trueControlNumber != null) {
            if (thisControlNumber > 101) {
                return trueControlNumber == thisControlNumber % 101
            } else if (thisControlNumber > 99) {
                return trueControlNumber == 0
            } else {
                return trueControlNumber == thisControlNumber
            }
        }
        return false
    }

    override fun validate(value: String?): List<ErrorCode> {
        val exactRequiredLength = 11
        val result = mutableListOf<ErrorCode>()

        if (value == null) {
            result.add(ErrorCode.MISSING_DATA)
        } else {
            if (value.any { ! it.isDigit() }) {
                result.add(ErrorCode.INVALID_CHARACTER)
            }
            if (value.length > exactRequiredLength) {
                result.add(ErrorCode.TOO_MANY_CHARACTERS)
            } else if (value.length < exactRequiredLength) {
                result.add(ErrorCode.TOO_FEW_CHARACTERS)
            }

            if (value.length == exactRequiredLength &&
                !validateControlNum(value)) {
                result.add(ErrorCode.BAD_SNILS_CONTROL_NUM)
            }
        }
        return result
    }
}