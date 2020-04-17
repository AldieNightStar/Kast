# Kast
Kotlin Cast (Ducktyping)

```kt
import kast

class Speaker {
    fun SayHello() {
        println("Hello!")
    }

    fun SayHi() {
        println("SayHi")
    }
}

interface Inter {
    fun SayHello()
}

fun main() {
    val m = Speaker()
    val x : Inter = m.kast()
    x.SayHello()
}
```
