import java.lang.UnsupportedOperationException
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.*

class KastInvokeHandler(val o: Any) : InvocationHandler {
    private val map = generateMap()

    private fun generateMap(): MutableMap<String, MutableList<Method>> {
        var map = mutableMapOf<String, MutableList<Method>>()
        var methods = o.javaClass.methods
        for (method in methods) {
            var list = map[method.name]
            if (list == null) {
                list = mutableListOf()
                map[method.name] = list
            }
            list.add(method)
        }
        return map
    }

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        val list = map[method?.name] ?: mutableListOf()
        if (method == null || list.isEmpty()) throw UnsupportedOperationException("No such method ${method?.name}")
        for (m in list) {
            if (m.name == method.name && Arrays.equals(m.typeParameters, method.typeParameters)) {
                if (args == null) {
                    return m.invoke(o);
                } else {
                    return m.invoke(o, *args)
                }
            }
        }
        throw UnsupportedOperationException("No such overloading for method \"${method.name}\" with params: ${method.typeParameters}")
    }
}

inline fun <reified T> Any.kast(): T {
    return Proxy.newProxyInstance(javaClass.classLoader, arrayOf(T::class.java), KastInvokeHandler(this)) as T
}
