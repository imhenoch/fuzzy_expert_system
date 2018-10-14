package ui.outputs

import models.Output
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

class AddOutputUI : JPanel(), UIForm {
    @Inject
    lateinit var navigation: Navigation
    private val chart by lazy { SimpleChart() }
    private val outputName by lazy { UIInput("Output name,") }

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
        val variable = Output(outputName.text, chart.points)
        variable.init()
        navigation.navigateToOutput()
    }

    private fun topUI(): JComponent {
        val ui = Box.createHorizontalBox()

        ui.add(outputName)

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