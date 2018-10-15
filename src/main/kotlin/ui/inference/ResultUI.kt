package ui.inference

import files.Register
import fuzzy.defuzzyficate
import fuzzy.fuzzyficate
import fuzzy.generateFAM
import fuzzy.getMembershipWithY
import models.Output
import models.Range
import models.Variable
import org.knowm.xchart.XChartPanel
import org.knowm.xchart.XYChart
import org.knowm.xchart.XYChartBuilder
import org.knowm.xchart.XYSeries
import org.knowm.xchart.style.lines.SeriesLines
import org.knowm.xchart.style.markers.SeriesMarkers
import ui.common.Navigation
import ui.common.UIContainer
import ui.questions.QuestionsUI
import java.awt.BorderLayout
import java.awt.Color
import java.awt.FlowLayout
import javax.inject.Inject
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.border.EmptyBorder

class ResultUI : JPanel(), UIContainer {
    @Inject
    lateinit var navigation: Navigation
    private lateinit var variables: ArrayList<Register<Variable>>
    private lateinit var ranges: ArrayList<Register<Range>>
    private lateinit var outputs: ArrayList<Register<Output>>
    private lateinit var chartUI: XYChart

    init {
        setupUI()
    }

    private fun setupUI() {
        layout = BorderLayout()
        add(centerUI(), BorderLayout.CENTER)
        add(bottomUI(), BorderLayout.SOUTH)
    }

    private fun centerUI(): JComponent {
        chartUI = XYChartBuilder().title(QuestionsUI.USER).build()
        chartUI.styler.apply {
            xAxisMin = 0.0
            yAxisMin = 0.0
            xAxisMax = 100.0
            yAxisMax = 1.0
        }

        val ui = XChartPanel<XYChart>(chartUI)
        return ui.apply {
            border = EmptyBorder(30, 30, 30, 30)
        }
    }

    private fun bottomUI(): JComponent {
        val ui = JPanel(FlowLayout(FlowLayout.RIGHT, 10, 10))

        ui.add(JButton("Back to home").apply {
            addActionListener {
                QuestionsUI.ANSWERS.clear()
                QuestionsUI.USER = ""
                navigation.navigateToHome()
            }
        })

        return ui
    }

    override fun fetchData() {
        variables = Variable().fetch()
        ranges = Range().fetch()
        outputs = Output().fetch()
        val answers = QuestionsUI.ANSWERS
        val fam = generateFAM(variables, ranges)
        val fuzzyficatedInputs = fuzzyficate(answers)
        val realOutputs = defuzzyficate(fam, fuzzyficatedInputs, outputs)

        realOutputs.forEach { o ->
            val xOutput = DoubleArray(o.points.size) { i -> o.points[i].x.toDouble() }
            val yOutput = DoubleArray(o.points.size) { i -> o.points[i].y.toDouble() / 100.0 }
            val serie = chartUI.addSeries(o.outputName, xOutput, yOutput)
            serie.apply {
                lineStyle = SeriesLines.DASH_DASH
                marker = SeriesMarkers.NONE
            }

            if (o.weight != 0.0) {
                val xArea = ArrayList<Double>()
                val yArea = ArrayList<Double>()
                xArea.add(o.points[0].x.toDouble())
                yArea.add(o.points[0].y.toDouble())
                for (i in 0 until o.points.size - 1) {
                    xArea.add(getMembershipWithY(o.weight.toInt(), o.points[i], o.points[i + 1]))
                    yArea.add(o.weight / 100.0)
                }
                xArea.add(o.points.last().x.toDouble())
                yArea.add(o.points.last().y.toDouble())
                val areaSerie = chartUI.addSeries("${o.outputName} area", xArea.toDoubleArray(), yArea.toDoubleArray())
                areaSerie.apply {
                    xySeriesRenderStyle = XYSeries.XYSeriesRenderStyle.Area
                    lineStyle = SeriesLines.SOLID
                    marker = SeriesMarkers.NONE
                    lineColor = Color.RED
                    fillColor = Color.RED
                }
            }
        }
        val xOutput = doubleArrayOf(0.0, 100.0)
        val yOutput = doubleArrayOf(0.0, 0.0)
        val serie = chartUI.addSeries("zero", xOutput, yOutput)
        serie.apply {
            lineStyle = SeriesLines.SOLID
            marker = SeriesMarkers.NONE
            lineColor = Color.RED
        }
    }
}