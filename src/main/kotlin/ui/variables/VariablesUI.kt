package ui.variables

import ui.common.Navigation
import ui.common.UIForm
import ui.common.UIInput
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.inject.Inject
import javax.swing.Box
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.border.EmptyBorder

class VariablesUI : JPanel(), UIForm {
    @Inject
    lateinit var navigation: Navigation
    val variableName by lazy { UIInput("Variable name") }

    init {
        setupUI()
    }

    private fun setupUI() {
        layout = BorderLayout()
        add(topUI(), BorderLayout.NORTH)
        add(bottomUI(), BorderLayout.SOUTH)
    }

    override fun saveData() {
        println("Saving variable...")
    }

    private fun topUI(): JComponent {
        val ui = Box.createHorizontalBox()

        ui.add(variableName)

        return ui.apply {
            border = EmptyBorder(10, 10, 10, 10)
        }
    }

    private fun bottomUI(): JComponent {
        val ui = JPanel(FlowLayout(FlowLayout.RIGHT, 5, 5))

        ui.add(JButton("Cancel").apply {
            addActionListener {
                navigation.navigateToHome()
            }
        })
        ui.add(JButton("Save variable").apply {
            addActionListener {
                println("Save variable")
            }
        })

        return ui
    }
}