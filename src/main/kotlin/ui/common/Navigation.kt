package ui.common

import ui.ParentWindow
import ui.home.HomeUI
import ui.outputs.AddOutputUI
import ui.outputs.OutputsUI
import ui.range.RangeUI
import ui.variables.VariablesUI
import javax.inject.Inject
import javax.swing.JComponent
import javax.swing.SwingUtilities

typealias ComponentFunction = (JComponent) -> Unit

class Navigation @Inject constructor(val parentWindow: ParentWindow) {
    fun setupUI(component: JComponent, inject: ComponentFunction) {
        parentWindow.contentPane.removeAll()
        inject(component)
        parentWindow.add(component)
        if (component is UIContainer)
            component.fetchData()
        SwingUtilities.updateComponentTreeUI(parentWindow)
    }

    @Suppress("UNCHECKED_CAST")
    fun navigateToHome() {
        val ui = HomeUI()
        setupUI(ui, parentWindow.fuzzyComponent::injectHomeUI as ComponentFunction)
    }

    @Suppress("UNCHECKED_CAST")
    fun navigateToAddVariable() {
        val ui = VariablesUI()
        setupUI(ui, parentWindow.fuzzyComponent::injectVariablesUI as ComponentFunction)
    }

    @Suppress("UNCHECKED_CAST")
    fun navigateToOutput() {
        val ui = OutputsUI()
        setupUI(ui, parentWindow.fuzzyComponent::injectOutputsUI as ComponentFunction)
    }

    @Suppress("UNCHECKED_CAST")
    fun navigateToAddOutput() {
        val ui = AddOutputUI()
        setupUI(ui, parentWindow.fuzzyComponent::injectAddOutputUI as ComponentFunction)
    }

    @Suppress("UNCHECKED_CAST")
    fun navigateToRange() {
        val ui = RangeUI()
        setupUI(ui, parentWindow.fuzzyComponent::injectRangeUI as ComponentFunction)
    }

    fun navigateToInference() {
        println(":)")
    }
}