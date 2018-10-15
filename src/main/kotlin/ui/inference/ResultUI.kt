package ui.inference

import files.Register
import fuzzy.defuzzyficate
import fuzzy.fuzzyficate
import fuzzy.generateFAM
import models.Output
import models.Range
import models.Variable
import ui.common.Navigation
import ui.common.UIContainer
import ui.questions.QuestionsUI
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.inject.Inject
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel

class ResultUI : JPanel(), UIContainer {
    @Inject
    lateinit var navigation: Navigation
    private lateinit var variables: ArrayList<Register<Variable>>
    private lateinit var ranges: ArrayList<Register<Range>>
    private lateinit var outputs: ArrayList<Register<Output>>

    init {
        setupUI()
    }

    private fun setupUI() {
        layout = BorderLayout()
        add(bottomUI(), BorderLayout.SOUTH)
    }

    private fun bottomUI(): JComponent {
        val ui = JPanel(FlowLayout(FlowLayout.RIGHT, 10, 10))

        ui.add(JButton("Back to home").apply {
            addActionListener {
                QuestionsUI.ANSWERS.clear()
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