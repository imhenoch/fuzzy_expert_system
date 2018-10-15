package ui.questions

import models.Answer
import models.Variable
import ui.common.Navigation
import ui.common.UIContainer
import java.awt.BorderLayout
import javax.inject.Inject
import javax.swing.JPanel
import javax.swing.SwingUtilities

class QuestionsUI : JPanel(), UIContainer {
    companion object {
        val ANSWERS = ArrayList<Answer>()
    }

    @Inject
    lateinit var navigation: Navigation
    private val variables by lazy { ArrayList<Variable>() }
    private val answers by lazy { ArrayList<Answer>() }
    private var actualVariable = 0

    init {
        setupUI()
    }

    private fun setupUI() {
        layout = BorderLayout()
    }

    override fun fetchData() {
        Variable().fetch().forEach { v ->
            variables.add(v.data!!)
        }
        if (variables.isEmpty())
            navigation.navigateToHome()
        else
            generateNewQuestion()
    }

    private fun generateNewQuestion() {
        removeAll()
        add(SimpleQuestionUI(variables[actualVariable], this::nextQuestion), BorderLayout.CENTER)
        SwingUtilities.updateComponentTreeUI(this)
    }

    private fun nextQuestion(percentage: Int, variable: Variable) {
        answers.add(Answer(variable, percentage))
        actualVariable++
        if (actualVariable == variables.size)
            finishQuestions()
        else
            generateNewQuestion()
    }

    private fun finishQuestions() {
        ANSWERS.addAll(answers)
        navigation.navigateToResult()
    }
}