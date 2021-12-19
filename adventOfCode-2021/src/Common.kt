import java.io.File

val input : File get() {
    val trace = Thread.currentThread().stackTrace
    val classname = trace[trace.lastIndex].className.substringAfter(".")
    val fileSuffix = classname.substring(1 /*P*/, classname.length-2 /*Kt*/)

    val f = File("adventOfCode-2021/resources/input$fileSuffix.txt")
    if (fileSuffix.endsWith("x") && !f.exists())
        return File("adventOfCode-2021/resources/input${fileSuffix.removeSuffix("x")}.txt")
    else
        return f;
}

fun Int.cnd(condition: Boolean) = if (condition) this else 0
fun cnd(condition: Boolean, ifTrue: () -> String) : String = if (condition) ifTrue() else ""