# Kast
Kotlin Cast (Ducktyping)

# Usage
`Any.kast()` - Will cast ANY object into what your want. It depends from what to return<br>

For example to cast `class` which has `read` function into `Reader`

```kt
interface Reader {
    func read(): Array<Byte>
}

func main() {
    var rd: Reader = SOMETHING.kast()
}
```

# Example
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
