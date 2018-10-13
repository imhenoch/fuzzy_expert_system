package ui.outputs

import ui.common.Navigation
import ui.common.UIForm
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.inject.Inject
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel

class AddOutputUI : JPanel(), UIForm {
    @Inject lateinit var navigation: Navigation

    init {
        setupUI()
    }

    private fun setupUI() {
        layout = BorderLayout()
        add(bottomUI(), BorderLayout.SOUTH)
    }

    override fun saveData() {
        println("Saving output...")
    }

    private fun bottomUI(): JComponent {
        val ui = JPanel(FlowLayout(FlowLayout.RIGHT, 5, 5))

        ui.add(JButton("Back").apply {
            addActionListener {
                navigation.navigateToOutput()
            }
        })
        ui.add(JButton("Save output").apply {
            addActionListener {
                saveData()
            }
        })
        return ui
    }
}