package ui.common.chart

import models.Point
import org.knowm.xchart.XChartPanel
import org.knowm.xchart.XYChart
import org.knowm.xchart.XYChartBuilder
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JComponent
import javax.swing.JPanel

class ComplexChart(val title: String = "") : JPanel() {
    private lateinit var chartUI: XYChart

    init {
        setupUI()
    }

    private fun setupUI() {
        minimumSize = Dimension(500, 400)
        layout = BorderLayout()
        add(center(), BorderLayout.CENTER)
    }

    private fun center(): JComponent {
        val ui = JPanel()

        chartUI = XYChartBuilder().width(480).height(320).build()
        chartUI.styler.apply {
            xAxisMin = 0.0
            yAxisMin = 0.0
            xAxisMax = 100.0
            yAxisMax = 1.0
        }
        chartUI.title = title
        ui.add(XChartPanel<XYChart>(chartUI))

        return ui
    }

    fun addSeries(label: String, points: ArrayList<Point>) {
        val xData = ArrayList<Double>()
        val yData = ArrayList<Double>()
        points.forEach { p ->
            xData.add(p.x.toDouble())
            yData.add(p.y.toDouble() / 100.0)
        }
        chartUI.addSeries(label, xData, yData)
    }
}