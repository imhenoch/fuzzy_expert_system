package ui.common.chart

import models.Point
import org.knowm.xchart.XChartPanel
import org.knowm.xchart.XYChart
import org.knowm.xchart.XYChartBuilder
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.Box
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.border.EmptyBorder

class SimpleChart : JPanel() {
    private val pointsUI by lazy { Box.createVerticalBox() }
    private lateinit var chartUI: XYChart
    private val xData = ArrayList<Double>()
    private val yData = ArrayList<Double>()
    private val points = ArrayList<Point>()

    init {
        setupUI()
    }

    private fun setupUI() {
        minimumSize = Dimension(500, 400)
        layout = BorderLayout()
        add(center(), BorderLayout.CENTER)
        add(left(), BorderLayout.WEST)
    }

    private fun left(): JComponent {
        generatePointUI(pointsUI)

        return pointsUI.apply {
            border = EmptyBorder(0, 0, 0, 0)
        }
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
        ui.add(XChartPanel<XYChart>(chartUI))

        return ui
    }

    private fun generatePointUI(ui: JComponent) {
        ui.add(SimplePoint(this::addPoint))
    }

    private fun addPoint(x: Int, y: Int) {
        points.add(Point(x, y))
        xData.add(x.toDouble())
        yData.add(y.toDouble() / 100.0)
        if (points.size < 10)
            generatePointUI(pointsUI)
        if (points.size > 1)
            chartUI.updateXYSeries("Data", xData, yData, null)
        else
            chartUI.addSeries("Data", xData, yData)
        SwingUtilities.updateComponentTreeUI(this)
    }
}