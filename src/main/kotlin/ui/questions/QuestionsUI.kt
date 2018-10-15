package ui.questions

import models.Variable
import ui.common.Navigation
import ui.common.UIContainer
import ui.common.UIForm
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.inject.Inject
import javax.swing.*

class QuestionsUI : JPanel(), UIContainer, UIForm {

    private var lblQuestion = JLabel()
    private var slider      = JSlider()
    private var txtRange    = JTextField("50%")

    @Inject
    lateinit var navigation: Navigation

    init {
        setupUI()
    }

    private fun setupUI(){
        layout = BorderLayout()
        add(centralPanel(), BorderLayout.CENTER)
        add(bottomUI(), BorderLayout.SOUTH)
    }

    override fun saveData() {
        println("Fetching ranges....")
    }

    override fun fetchData() {
        println("Fetching ranges....")
    }

    private fun bottomUI(): JComponent {
        val ui      = JPanel(FlowLayout(FlowLayout.RIGHT, 5, 5))

        ui.add(JButton("Back").apply {
            addActionListener {
                navigation.navigateToHome()
            }
        })

        ui.add(JButton("Next").apply {
            addActionListener{
                println(Variable().fetch())
            }
        })

        return ui
    }

    private fun centralPanel(): JComponent{
        val centralPanel = JPanel(BorderLayout())

        centralPanel.add(lblQuestion.apply {

        }, BorderLayout.NORTH)

        centralPanel.add(slider.apply {
            majorTickSpacing    = 10
            minorTickSpacing    = 1
            paintTicks          = true
            paintLabels         = true

            addChangeListener{
                txtRange.text = slider.value.toString() + "%"
            }
        }, BorderLayout.CENTER)

        centralPanel.add(txtRange.apply {
            txtRange.setBounds(20,20,20,20)
            txtRange.isEditable = false
        },BorderLayout.SOUTH)

        return centralPanel
    }

}