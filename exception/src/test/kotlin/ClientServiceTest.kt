import com.google.gson.Gson
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - bad phone`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(ErrorCode.WRONG_PHONE_FORMAT, exception.errorCode[0])
    }

    @Test
    fun `fail save client - bad snils`() {
        val client = getClientFromJson("/fail/user_bad_snils.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(ErrorCode.BAD_SNILS_CONTROL_NUM, exception.errorCode[0])
    }

    @Test
    fun `fail save client - bad email`() {
        val client = getClientFromJson("/fail/user_bad_email.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(ErrorCode.WRONG_EMAIL_FORMAT, exception.errorCode[0])
    }

    @Test
    fun `fail save client - wrong character`() {
        val client = getClientFromJson("/fail/user_wrong_character.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(ErrorCode.INVALID_CHARACTER, exception.errorCode[0])
    }

    @Test
    fun `fail save client - missing data`() {
        val client = getClientFromJson("/fail/user_missing_data.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(ErrorCode.MISSING_DATA, exception.errorCode[0])
    }

    @Test
    fun `fail save client - too many characters`() {
        val client = getClientFromJson("/fail/user_many_characters.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(ErrorCode.TOO_MANY_CHARACTERS, exception.errorCode[0])
    }

    @Test
    fun `fail save client - too few characters`() {
        val client = getClientFromJson("/fail/user_few_characters.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(ErrorCode.TOO_FEW_CHARACTERS, exception.errorCode[0])
    }

    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode.toSet(),
            setOf(ErrorCode.MISSING_DATA,
                ErrorCode.TOO_MANY_CHARACTERS,
                ErrorCode.WRONG_PHONE_FORMAT,
                ErrorCode.BAD_SNILS_CONTROL_NUM,
                ErrorCode.TOO_FEW_CHARACTERS))
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}