package ui.outputs

import ui.common.Navigation
import ui.common.UIContainer
import ui.common.UIForm
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.inject.Inject
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel

class OutputsUI : JPanel(), UIContainer, UIForm {
    @Inject
    lateinit var navigation: Navigation

    init {
        setUI()
    }

    private fun setUI() {
        layout = BorderLayout()
        add(bottomUI(), BorderLayout.SOUTH)
    }

    override fun fetchData() {
        println("Fetching outputs...")
    }

    override fun saveData() {
        println("Saving outputs...")
    }

    private fun bottomUI(): JComponent {
        val ui = JPanel(FlowLayout(FlowLayout.RIGHT, 5, 5))

        ui.add(JButton("Cancel").apply {
            addActionListener {
                navigation.navigateToHome()
            }
        })
        ui.add(JButton("Save outputs").apply {
            addActionListener {
                println("Save outputs")
            }
        })
        return ui
    }
}