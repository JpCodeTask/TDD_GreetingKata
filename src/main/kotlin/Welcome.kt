class Welcome {

    fun greet(name: String = ""): String  =
        when {
            name.isEmpty() -> "Hello, my friend."
            isUpperCase(name) -> "HELLO $name!"
            else -> "Hello, $name."
        }


    fun isUpperCase(string: String): Boolean =
        string.filter {it.isUpperCase()}.count() == string.length

    fun greet(names: Array<String>): String{

        val preprocessedNames = preprocessNames(names)

        val upperCasedNames = preprocessedNames.filter { isUpperCase(it) }.toTypedArray()
        val capitalizedNames = preprocessedNames.filter { !isUpperCase(it) }.toTypedArray()

        val normalGreeting = greetNormally(capitalizedNames)
        val shoutGreeting = greetNormally(upperCasedNames)
            .replace(Regex(","), "")
            .replace('.', '!')
            .toUpperCase()

        var greeting = ""
        greeting += normalGreeting
        greeting += if (upperCasedNames.isNotEmpty()) " AND ${shoutGreeting}" else ""

        return greeting
    }

    private fun preprocessNames(names: Array<String>): MutableList<String> {
        val preprocessedNames = names.map {
            if(it.contains('"')){
                listOf(it.replace(Regex("\""),""))
            }else{
                it.split(",")
            }
        }.flatten().toMutableList()

        for (i in preprocessedNames.indices) {
            preprocessedNames[i] = preprocessedNames[i].trim()
        }

        preprocessedNames.toList()
        return preprocessedNames
    }

    private fun greetNormally(names: Array<String>): String{
        var greeting = "Hello, "

        when(names.size){
            0 -> greeting += "everyone"
            1 -> greeting = greet(names.first())
            2 -> greeting += "${names.component1()} and ${names.component2()}."
            else -> {

                for (i in 0 until names.size - 1){
                    greeting += "${names[i]}, "
                }

                greeting += "and ${names.last().trim()}."
            }
        }

        return greeting
    }
}
