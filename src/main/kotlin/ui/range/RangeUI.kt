package ui.range

import files.Register
import models.Output
import models.Range
import models.Variable
import ui.common.Navigation
import ui.common.UIContainer
import ui.common.UIForm
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.inject.Inject
import javax.swing.Box
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.border.EmptyBorder

class RangeUI : JPanel(), UIContainer, UIForm {
    @Inject
    lateinit var navigation: Navigation
    private lateinit var variables: ArrayList<Register<Variable>>
    private lateinit var outputs: ArrayList<Register<Output>>
    private val rangesUI by lazy { Box.createVerticalBox() }
    private val info by lazy { JLabel() }
    private val ranges = ArrayList<Range>()
    private lateinit var range: Range

    init {
        setupUI()
    }

    private fun setupUI() {
        layout = BorderLayout()
        add(topUI(), BorderLayout.NORTH)
        add(centerUI(), BorderLayout.CENTER)
        add(bottomUI(), BorderLayout.SOUTH)
    }

    override fun fetchData() {
        variables = Variable().fetch()
        outputs = Output().fetch()

        info.text = "Range from 0 to ${variables.size}"

        generateRangeUI(rangesUI)
        SwingUtilities.updateComponentTreeUI(this)
    }

    override fun saveData() {
        println("Saving range.....")
    }

    private fun topUI(): JComponent {
        val ui = JPanel()

        ui.add(info)

        return ui.apply {
            border = EmptyBorder(10, 10, 10, 10)
        }
    }

    private fun centerUI(): JComponent {
        return rangesUI.apply {
            border = EmptyBorder(30, 30, 30, 30)
        }
    }

    private fun generateRangeUI(ui: JComponent) {
        val tmpOutputs = ArrayList<Output>()
        outputs.forEach { o -> tmpOutputs.add(o.data) }
        ui.add(SimpleRange(this::addRange, tmpOutputs))
    }

    private fun addRange(min: Int, max: Int, output: Output) {
        saveData()
        ranges.add(Range(min, max, output))
        generateRangeUI(rangesUI)
        SwingUtilities.updateComponentTreeUI(this)
    }

    private fun bottomUI(): JComponent {
        val ui = JPanel(FlowLayout(FlowLayout.RIGHT, 5, 5))

        ui.add(JButton("Back").apply {
            addActionListener {
                navigation.navigateToHome()
            }
        })

        return ui
    }
}