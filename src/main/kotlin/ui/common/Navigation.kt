package ui.common

import ui.ParentWindow
import ui.home.HomeUI
import javax.inject.Inject
import javax.swing.SwingUtilities

class Navigation @Inject constructor(val parentWindow: ParentWindow) {
    fun navigateToHome() {
        val homeUI = HomeUI()
        parentWindow.contentPane.removeAll()
        parentWindow.fuzzyComponent.injectHomeUI(homeUI)
        parentWindow.contentPane.add(homeUI)
        SwingUtilities.updateComponentTreeUI(parentWindow)
    }
}