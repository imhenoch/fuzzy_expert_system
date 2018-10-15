package ui.inference

import files.Register
import fuzzy.generateFAM
import models.Variable
import ui.common.Navigation
import ui.common.UIContainer
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
                navigation.navigateToHome()
            }
        })

        return ui
    }

    override fun fetchData() {
        variables = Variable().fetch()
        val fam = generateFAM(variables)
        println(fam)
    }
}