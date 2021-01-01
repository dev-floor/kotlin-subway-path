package subway.ui.common

import java.util.Stack

object ViewNavigation {
    val isEmpty get() = navigation.isEmpty()
    private val navigation = Stack<View>()

    fun init(view: View) {
        navigation.push(view)
    }

    fun run() = navigation.pushAndRender(navigation.pop())

    fun navigate(view: View?) = view?.let { navigation.push(it) } ?: goBack()

    fun navigateWithHandlingException(view: View?) = try {
        navigate(view)
    } catch (e: IllegalArgumentException) {
        navigation.pop()
        throw e
    }

    fun goBack() {
        navigation.pop()
    }

    fun goToFirst() {
        val firstView = navigation.firstElement()

        navigation.clear()
        navigation.push(firstView)
    }
}
