package ui

import di.DaggerFuzzyComponent
import di.FuzzyComponent
import di.UIModule
import ui.common.Navigation
import java.awt.Dimension
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import javax.inject.Inject
import javax.swing.JFrame

class ParentWindow : JFrame() {
    @Inject
    lateinit var navigation: Navigation
    lateinit var fuzzyComponent: FuzzyComponent

    init {
        setUIProperties()
        setEvents()
        isVisible = true
    }

    private fun setUIProperties() {
        title = "Fuzzy expert system"
        size = Dimension(400, 400)
        setLocationRelativeTo(null)
    }

    private fun setEvents() {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        addWindowListener(object : WindowListener {
            override fun windowDeiconified(e: WindowEvent?) {}

            override fun windowClosing(e: WindowEvent?) {}

            override fun windowClosed(e: WindowEvent?) {}

            override fun windowActivated(e: WindowEvent?) {}

            override fun windowDeactivated(e: WindowEvent?) {}

            override fun windowOpened(e: WindowEvent?) {
                fuzzyComponent = DaggerFuzzyComponent.builder().uIModule(UIModule(this@ParentWindow)).build()
                fuzzyComponent.injectParentWindow(this@ParentWindow)
                navigation.navigateToHome()
            }

            override fun windowIconified(e: WindowEvent?) {}
        })
    }
}