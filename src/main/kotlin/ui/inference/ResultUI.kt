package ui.inference

import files.Register
import fuzzy.defuzzyficate
import fuzzy.fuzzyficate
import fuzzy.generateFAM
import models.Output
import models.Range
import models.Variable
import org.knowm.xchart.XChartPanel
import org.knowm.xchart.XYChart
import org.knowm.xchart.XYChartBuilder
import ui.common.Navigation
import ui.common.UIContainer
import ui.questions.QuestionsUI
import java.awt.BorderLayout
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
    }
}