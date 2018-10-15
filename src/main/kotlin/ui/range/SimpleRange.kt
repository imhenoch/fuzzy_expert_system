package ui.range

import models.Output
import ui.common.UIInput
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.util.Vector
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JPanel
import javax.swing.border.EmptyBorder

typealias RangeFucntion = (Int, Int, Output) -> Unit

class SimpleRange(val function: RangeFucntion,
                  val outputs: ArrayList<Output>,
                  val label: String = "Save") : JPanel() {
    private val min by lazy { UIInput("Minimum") }
    private val max by lazy { UIInput("Maximum") }
    private val outputsUI by lazy {
        val vector = Vector<Output>()
        outputs.forEach { o -> vector.addElement(o) }
        JComboBox<Output>(vector)
    }
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
        add(min, c)

        c.fill = GridBagConstraints.HORIZONTAL
        c.weightx = 1.0
        c.gridx = 1
        c.gridy = 0
        add(max, c)

        c.fill = GridBagConstraints.HORIZONTAL
        c.weightx = 1.0
        c.gridx = 2
        c.gridy = 0
        c.gridwidth = 2
        add(outputsUI, c)

        c.fill = GridBagConstraints.HORIZONTAL
        c.weightx = 1.0
        c.gridx = 0
        c.gridy = 1
        c.gridwidth = 4
        add(btn, c)

        border = EmptyBorder(10, 10, 10, 10)
    }

    private fun setupEvents() {
        btn.addActionListener {
            try {
                function(min.text.toInt(), max.text.toInt(), outputsUI.selectedItem as Output)
                min.isEnabled = false
                max.isEnabled = false
                outputsUI.isEnabled = false
                remove(btn)
            } catch (ex: NumberFormatException) {
            }
        }
    }
}