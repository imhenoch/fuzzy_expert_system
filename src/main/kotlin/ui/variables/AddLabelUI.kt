package ui.variables

import ui.common.Navigation
import ui.common.UIForm
import ui.common.UIInput
import ui.common.chart.SimpleChart
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.inject.Inject
import javax.swing.Box
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.border.EmptyBorder

class AddLabelUI : JPanel(), UIForm {
    @Inject
    lateinit var navigation: Navigation
    private val chart by lazy { SimpleChart() }
    private val labelName by lazy { UIInput("Label name") }

    init {
        setupUI()
    }

    private fun setupUI() {
        layout = BorderLayout()
        add(bottomUI(), BorderLayout.SOUTH)
        add(centerUI(), BorderLayout.CENTER)
        add(topUI(), BorderLayout.NORTH)
    }

    override fun saveData() {
        println("Saving data...")
    }

    private fun topUI(): JComponent {
        val ui = Box.createHorizontalBox()

        ui.add(labelName)

        return ui.apply {
            border = EmptyBorder(10, 10, 10, 10)
        }
    }

    private fun centerUI(): JComponent {
        val ui = JPanel()

        ui.add(chart)

        return ui.apply {
            border = EmptyBorder(10, 10, 10, 10)
        }
    }

    private fun bottomUI(): JComponent {
        val ui = JPanel(FlowLayout(FlowLayout.RIGHT, 5, 5))

        ui.add(JButton("Back").apply {
            addActionListener {
                navigation.navigateToAddVariable()
            }
        })
        ui.add(JButton("Save label").apply {
            addActionListener {
                saveData()
            }
        })
        return ui
    }
}