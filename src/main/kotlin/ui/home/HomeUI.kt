package ui.home

import files.Register
import models.Variable
import ui.common.Navigation
import ui.common.UIContainer
import ui.common.chart.ComplexChart
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.GridLayout
import javax.inject.Inject
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.SwingUtilities

class HomeUI : JPanel(), UIContainer {
    @Inject
    lateinit var navigation: Navigation
    private lateinit var variables: ArrayList<Register<Variable>>
    private val chartsUI by lazy { JPanel() }

    init {
        setupUI()
    }

    private fun setupUI() {
        layout = BorderLayout()
        add(centerUI(), BorderLayout.CENTER)
        add(bottomUI(), BorderLayout.SOUTH)
    }

    override fun fetchData() {
        variables = Variable().fetch()
        var rows = variables.size / 3
        if (variables.size % 3 != 0)
            rows += 1

        chartsUI.layout = GridLayout(rows, 3)
        variables.forEach { v ->
            val chart = ComplexChart(v.data!!.variableName)
            v.data.labels.forEach { l ->
                chart.addSeries(l.labelName, l.points)
            }
            chartsUI.add(chart)
        }
        SwingUtilities.updateComponentTreeUI(this)
    }

    private fun centerUI(): JComponent {
        val scroll = JScrollPane(chartsUI)
        return scroll
    }

    private fun bottomUI(): JComponent {
        val ui = JPanel(FlowLayout(FlowLayout.RIGHT, 5, 5))

        ui.add(JButton("Add variable").apply {
            addActionListener {
                navigation.navigateToAddVariable()
            }
        })
        ui.add(JButton("Set outputs").apply {
            addActionListener {
                navigation.navigateToOutput()
            }
        })
        ui.add(JButton("Set ranges").apply {
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