package ui.common

import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import javax.swing.JTextField

class UIInput(var hint: String, var hintColor: Color = Color.lightGray) : JTextField() {
    override fun paint(g: Graphics?) {
        super.paint(g)
        if (text.isEmpty()) {
            (g as Graphics2D).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
            val fm = g.getFontMetrics()
            g.setColor(hintColor)
            g.drawString(hint, insets.left, height / 2 + fm.ascent / 2 - 1)
        }
    }
}