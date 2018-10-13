package ui.home

import ui.common.Navigation
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.inject.Inject
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel

class HomeUI : JPanel() {
    @Inject
    lateinit var navigation: Navigation

    init {
        setUI()
    }

    private fun setUI() {
        layout = BorderLayout()
        add(bottomUI(), BorderLayout.SOUTH)
    }

    private fun bottomUI(): JComponent {
        val ui = JPanel(FlowLayout(FlowLayout.RIGHT, 5, 5))

        ui.add(JButton("Agregar variable").apply {
            addActionListener {
                navigation.navigateToAddVariable()
            }
        })
        ui.add(JButton("Configurar rangos").apply {
            addActionListener {
                navigation.navigateToRange()
            }
        })
        ui.add(JButton(":)").apply {
            addActionListener {
                navigation.navigateToInference()
            }
        })
        return ui
    }
}