import java.io.File

val input : File get() {
    val trace = Thread.currentThread().stackTrace
    val classname = trace[trace.lastIndex].className
    val fileSuffix = classname.substring(1 /*P*/, classname.length-2 /*Kt*/)

    val f = File("adventOfCode-2021/resources/input$fileSuffix.txt")
    if (fileSuffix.endsWith("x") && !f.exists())
        return File("adventOfCode-2021/resources/input${fileSuffix.removeSuffix("x")}.txt")
    else
        return f;
}