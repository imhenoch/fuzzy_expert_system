package ui.questions

import models.Variable
import java.awt.BorderLayout
import javax.swing.Box
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JSlider
import javax.swing.border.EmptyBorder

typealias QuestionFunction = (Int, Variable) -> Unit

class SimpleQuestionUI(val variable: Variable,
                       val function: QuestionFunction) : JPanel() {
    private val question by lazy {
        JLabel("${variable.variableName}?").apply {
            horizontalAlignment = JLabel.CENTER
        }
    }
    private val slider by lazy {
        JSlider(JSlider.HORIZONTAL, 0, 100, 1).apply {
            majorTickSpacing = 10
            minorTickSpacing = 1
            paintTicks = true
            paintLabels = true
        }
    }
    private val value by lazy {
        JLabel().apply {
            border = EmptyBorder(20, 20, 20, 20)
        }
    }
    private val btn by lazy { JButton("Next") }

    init {
        setupUI()
        setupEvents()
    }

    private fun setupUI() {
        layout = BorderLayout()

        val percentageUI = Box.createHorizontalBox()
        percentageUI.add(slider)
        percentageUI.add(value)

        add(question, BorderLayout.NORTH)
        add(percentageUI, BorderLayout.CENTER)
        add(btn, BorderLayout.SOUTH)

        border = EmptyBorder(100, 50, 100, 50)
    }

    private fun setupEvents() {
        btn.addActionListener {
            function(slider.value, variable)
        }
        slider.addChangeListener {
            value.text = "${slider.value} %"
        }
    }
}