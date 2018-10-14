package ui.range

import ui.common.Navigation
import ui.common.UIContainer
import ui.common.UIForm
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.inject.Inject
import javax.swing.*

class RangeUI : JPanel(), UIContainer, UIForm {
    private val slider      = JSlider(0,100,50)
    private val txtRange    = JTextField("50%")

    @Inject
    lateinit var navigation: Navigation

    init {
        setupUI()
    }

    private fun setupUI() {
        layout = BorderLayout()
        add(centralPanel(), BorderLayout.CENTER)
        add(bottomUI(), BorderLayout.SOUTH)
    }

    override fun fetchData() {
        println("Fetching ranges....")
    }

    override fun saveData() {
        println("Saving range.....")
    }

    private fun bottomUI(): JComponent {
        val ui      = JPanel(FlowLayout(FlowLayout.RIGHT, 5, 5))

        ui.add(JButton("Get").apply {
            addActionListener {
                println(txtRange.text)
            }
        })
        ui.add(JButton("Back").apply {
            addActionListener {
                navigation.navigateToHome()
            }
        })

        return ui
    }

    private fun centralPanel(): JComponent{
        val centralPanel = JPanel(BorderLayout())

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