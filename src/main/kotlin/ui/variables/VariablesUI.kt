package ui.variables

import models.Label
import models.Variable
import ui.common.Navigation
import ui.common.UIContainer
import ui.common.UIForm
import ui.common.UIInput
import ui.common.chart.ComplexChart
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.inject.Inject
import javax.swing.Box
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.border.EmptyBorder

class VariablesUI : JPanel(), UIForm, UIContainer {
    companion object {
        val LABELS = ArrayList<Label>()
    }

    @Inject
    lateinit var navigation: Navigation
    private val chart by lazy { ComplexChart() }
    private val variableName by lazy { UIInput("Variable name") }

    init {
        setupUI()
    }

    private fun setupUI() {
        layout = BorderLayout()
        add(topUI(), BorderLayout.NORTH)
        add(centerUI(), BorderLayout.CENTER)
        add(bottomUI(), BorderLayout.SOUTH)
    }

    override fun saveData() {
        val variable = Variable(variableName.text, LABELS)
        variable.init()
        LABELS.clear()
        navigation.navigateToHome()
    }

    override fun fetchData() {
        LABELS.forEach { l ->
            chart.addSeries(l.labelName, l.points)
        }
    }

    private fun topUI(): JComponent {
        val ui = Box.createHorizontalBox()

        ui.add(variableName)

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

        ui.add(JButton("Cancel").apply {
            addActionListener {
                navigation.navigateToHome()
            }
        })
        ui.add(JButton("Add label").apply {
            addActionListener {
                navigation.navigateToAddLabel()
            }
        })
        ui.add(JButton("Save variable").apply {
            addActionListener {
                saveData()
            }
        })

        return ui
    }
}