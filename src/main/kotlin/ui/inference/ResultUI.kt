package ui.inference

import files.Register
import fuzzy.fuzzyficateInput
import fuzzy.generateFAM
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
        val fam = generateFAM(variables, ranges)
        val answers = QuestionsUI.ANSWERS
        val fuzzyficatedInputs = fuzzyficateInput(answers)
    }
}