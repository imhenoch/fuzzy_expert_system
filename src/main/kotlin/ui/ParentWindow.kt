package ui

import java.awt.Dimension
import javax.swing.JFrame

class ParentWindow : JFrame() {
    init {
        title = "Fuzzy expert system"
        size = Dimension(400, 400)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        isVisible = true
    }
}