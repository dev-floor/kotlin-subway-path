package subway.ui.common

import java.util.Stack

fun Stack<View>.pushAndRender(view: View) =
    this.push(view)
        .render()

fun Stack<View>.peekAndRender() =
    this.peek()
        .render()
