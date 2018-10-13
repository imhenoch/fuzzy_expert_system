package ui.home

import ui.common.Navigation
import javax.inject.Inject
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class HomeUI : JPanel() {
    @Inject
    lateinit var navigation: Navigation

    init {
        setUI()
    }

    private fun setUI() {
        add(JLabel("HOME").apply {
            horizontalAlignment = JButton.CENTER
            verticalAlignment = JButton.CENTER
        })
    }
}