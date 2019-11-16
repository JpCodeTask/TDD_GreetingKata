import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WelcomeTest {

    companion object{
        lateinit var welcome: Welcome
    }

    @BeforeEach
    fun setUp(){
        welcome = Welcome()
    }

    @Test
    fun `interpolate name into simple greeting`(){
        assertEquals("Hello, Bob.", welcome.greet("Bob"))
        assertEquals("Hello, John.", welcome.greet("John"))
    }

    @Test
    fun `when name is empty then greet as my friend`(){
        assertEquals("Hello, my friend.", welcome.greet())
    }

    @Test
    fun `when name is uppercase then shout`(){
        assertEquals("HELLO JERRY!", welcome.greet("JERRY"))
    }

    @Test
    fun `interpolate array of names into simple greeting`(){
        assertEquals("Hello, Jill and Jane.", welcome.greet(arrayOf("Jill", "Jane")))
    }

    @Test
    fun `when array of names is empty then greet as everyone`(){
        assertEquals("Hello, everyone", welcome.greet(emptyArray()))
    }

    @Test
    fun `when array of names has one name then greet me simple`(){
        assertEquals("Hello, John.", welcome.greet(arrayOf("John")))
    }

    @Test
    fun `when array of names has more then two names then separate with commas and 'and'`(){
        assertEquals(
            "Hello, Amy, Brian, and Charlotte.",
            welcome.greet(arrayOf("Amy", "Brian", "Charlotte"))
        )
        assertEquals(
            "Hello, Amy, Brian, Jim, and Charlotte.",
            welcome.greet(arrayOf("Amy", "Brian", "Jim", "Charlotte"))
        )
    }

    @Test
    fun `when some names in array uppercase then response is two greetings`(){
        assertEquals(
            "Hello, Amy and Charlotte. AND HELLO BRIAN!",
            welcome.greet(arrayOf("Amy", "BRIAN", "Charlotte"))
        )
        assertEquals(
            "Hello, Amy and Charlotte. AND HELLO BRIAN AND TOM!",
            welcome.greet(arrayOf("Amy", "BRIAN", "Charlotte", "TOM"))
        )
    }

    @Test
    fun `when string in names array is names separated by comma then split`(){
        assertEquals(
            "Hello, Bob, Charlie, and Dianne.",
            welcome.greet(arrayOf("Bob", "Charlie, Dianne"))
        )

        assertEquals(
            "Hello, Bob, Charlie, John, and Dianne.",
            welcome.greet(arrayOf("Bob", "Charlie, John, Dianne"))
        )
    }

    @Test
    fun `when commas are intentional with escape char then  not separate them`(){
        assertEquals(
            "Hello, Bob and Charlie, Dianne.",
            welcome.greet(arrayOf("Bob", "\"Charlie, Dianne\""))
        )
    }
}