package ui.outputs

import ui.common.Navigation
import ui.common.UIContainer
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.inject.Inject
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel

class OutputsUI : JPanel(), UIContainer {
    @Inject
    lateinit var navigation: Navigation

    init {
        setupUI()
    }

    private fun setupUI() {
        layout = BorderLayout()
        add(bottomUI(), BorderLayout.SOUTH)
    }

    override fun fetchData() {
        println("Fetching outputs...")
    }

    private fun bottomUI(): JComponent {
        val ui = JPanel(FlowLayout(FlowLayout.RIGHT, 5, 5))

        ui.add(JButton("Back").apply {
            addActionListener {
                navigation.navigateToHome()
            }
        })
        ui.add(JButton("Add output").apply {
            addActionListener {
                navigation.navigateToAddOutput()
            }
        })
        return ui
    }
}