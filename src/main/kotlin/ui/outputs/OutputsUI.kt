package ui.outputs

import files.Register
import models.Output
import ui.common.Navigation
import ui.common.UIContainer
import ui.common.chart.ComplexChart
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.inject.Inject
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.border.EmptyBorder

class OutputsUI : JPanel(), UIContainer {
    @Inject
    lateinit var navigation: Navigation
    private lateinit var outputs: ArrayList<Register<Output>>
    private val chart by lazy { ComplexChart() }

    init {
        setupUI()
    }

    private fun setupUI() {
        layout = BorderLayout()
        add(centerUI(), BorderLayout.CENTER)
        add(bottomUI(), BorderLayout.SOUTH)
    }

    override fun fetchData() {
        outputs = Output().fetch()
        outputs.forEach { output ->
            chart.addSeries(output.data.outputName, output.data.points)
        }
        SwingUtilities.updateComponentTreeUI(this)
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
                navigation.navigateToHome()
            }
        })
        ui.add(JButton("Add output").apply {
            addActionListener {
                navigation.navigateToAddOutput()
            }
        })
        return ui
    }
}