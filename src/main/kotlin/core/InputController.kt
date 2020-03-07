package core

import utils.Log
import kotlin.browser.document

class InputController {

    val pressedKeys = mutableSetOf<Char>()

    init {
        document.onkeydown = { e ->
            onKeyDown(e.which)
        }

        document.onkeyup = { e ->
            onKeyUp(e.which)
        }
    }

    private fun onKeyUp(charCode: Int) {
        val finalValue = charCode.toChar().toLowerCase()
        pressedKeys.remove(finalValue)
    }

    private fun onKeyDown(charCode: Int) {
        val finalValue = charCode.toChar().toLowerCase()
        pressedKeys.add(finalValue)
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

}
