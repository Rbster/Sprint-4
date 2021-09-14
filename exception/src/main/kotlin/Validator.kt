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
            if (value.any { ! it.isDigit() }) {
                result.add(ErrorCode.INVALID_CHARACTER)
            }
            if (value.length > exactRequiredLength) {
                result.add(ErrorCode.TOO_MANY_CHARACTERS)
            } else if (value.length < exactRequiredLength) {
                result.add(ErrorCode.TOO_FEW_CHARACTERS)
            }
            if (value.isEmpty() ||
                value[0] != '8' ||
                value[0] != '7') {

                result.add(ErrorCode.WRONG_FORMAT)
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

        if (value == null) {
            result.add(ErrorCode.MISSING_DATA)
        } else {
            if (value.length > maxRequiredLength) {
                result.add(ErrorCode.TOO_MANY_CHARACTERS)
            }

            // TODO:
            //  - Latin alphabet
            //  - Regex for format
        }


        return result
    }
}

class SnilsValidator : Validator<String>() {
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
            // TODO:
            //  - Control number validation

        }
        return result
    }
}