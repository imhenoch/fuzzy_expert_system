package ui.range

import ui.common.Navigation
import ui.common.UIContainer
import ui.common.UIForm
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.inject.Inject
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel

class RangeUI : JPanel(), UIContainer, UIForm {
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
        println("Fetching ranges...")
    }

    override fun saveData() {
        println("Saving ranges...")
    }

    private fun bottomUI(): JComponent {
        val ui = JPanel(FlowLayout(FlowLayout.RIGHT, 5, 5))

        ui.add(JButton("Cancel").apply {
            addActionListener {
                navigation.navigateToHome()
            }
        })
        ui.add(JButton("Save range").apply {
            addActionListener {
                println("Save range")
            }
        })
        return ui
    }
}