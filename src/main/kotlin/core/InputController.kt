package core

import org.w3c.dom.events.KeyboardEvent
import kotlin.browser.document

class InputController {

    private var isShiftPressed: Boolean = false
    val pressedKeys = mutableSetOf<Char>()

    init {
        document.onkeydown = { e ->
            onKeyEvent(e, true)
        }

        document.onkeyup = { e ->
            onKeyEvent(e, false)
        }
    }

    private fun onKeyUp(charCode: Int) {
        val finalValue = charCode.toChar().toLowerCase()
        pressedKeys.remove(finalValue)
    }

    private fun onKeyEvent(event: KeyboardEvent, isDown: Boolean) {
        val finalValue = event.which.toChar().toLowerCase()
        if (isDown) {
            isShiftPressed = event.shiftKey
            pressedKeys.add(finalValue)
        } else {
            pressedKeys.remove(finalValue)
        }
    }

    fun isLeftTurn(): Boolean {
        return pressedKeys.contains('a')
    }

    fun isRightTurn(): Boolean {
        return pressedKeys.contains('d')
    }

    fun isForward(): Boolean {
        return pressedKeys.contains('w')
    }

    fun isBackward(): Boolean {
        return pressedKeys.contains('s')
    }

    fun isSprint(): Boolean {
        return isShiftPressed
    }

}
