package ui.result

import ui.common.Navigation
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.inject.Inject
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel

class ResultUI : JPanel() {
    @Inject
    lateinit var navigation: Navigation

    init {
        setupUI()
    }

    private fun setupUI() {
        layout = BorderLayout()
        add(bottomUI(), BorderLayout.SOUTH)
    }

    private fun bottomUI(): JComponent {
        val ui = JPanel(FlowLayout(FlowLayout.RIGHT, 10, 10))

        ui.add(JButton("Back to home").apply {
            addActionListener {
                navigation.navigateToHome()
            }
        })

        return ui
    }
}