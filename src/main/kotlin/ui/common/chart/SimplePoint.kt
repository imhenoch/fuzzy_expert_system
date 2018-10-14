package ui.common.chart

import ui.common.UIInput
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JButton
import javax.swing.JPanel

typealias PointFunction = (Int, Int) -> Unit

class SimplePoint(val function: PointFunction,
                  val label: String = "Add point") : JPanel() {
    private val x by lazy { UIInput("X") }
    private val y by lazy { UIInput("Y") }
    private val btn by lazy { JButton(label) }

    init {
        setupUI()
        setupEvents()
    }

    private fun setupUI() {
        layout = GridBagLayout()
        val c = GridBagConstraints()

        c.fill = GridBagConstraints.HORIZONTAL
        c.weightx = 1.0
        c.gridx = 0
        c.gridy = 0
        add(x, c)

        c.fill = GridBagConstraints.HORIZONTAL
        c.weightx = 1.0
        c.gridx = 1
        c.gridy = 0
        add(y, c)

        c.fill = GridBagConstraints.HORIZONTAL
        c.weightx = 1.0
        c.gridx = 0
        c.gridy = 1
        c.gridwidth = 2
        add(btn, c)
    }

    private fun setupEvents() {
        btn.addActionListener {
            try {
                function(x.text.toInt(), y.text.toInt())
                x.isEnabled = false
                y.isEnabled = false
                remove(btn)
            } catch (ex: NumberFormatException) {
            }
        }
    }
}