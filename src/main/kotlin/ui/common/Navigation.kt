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
    private val homeUI by lazy { HomeUI() }
    private val variablesUI by lazy { VariablesUI() }
    private val outputsUI by lazy { OutputsUI() }
    private val rangeUI by lazy { RangeUI() }

    fun setupUI(component: JComponent, inject: ComponentFunction) {
        parentWindow.contentPane.removeAll()
        inject(component)
        parentWindow.add(component)
        SwingUtilities.updateComponentTreeUI(parentWindow)
    }

    @Suppress("UNCHECKED_CAST")
    fun navigateToHome() {
        setupUI(homeUI, parentWindow.fuzzyComponent::injectHomeUI as ComponentFunction)
    }

    @Suppress("UNCHECKED_CAST")
    fun navigateToAddVariable() {
        setupUI(variablesUI, parentWindow.fuzzyComponent::injectVariablesUI as ComponentFunction)
    }

    @Suppress("UNCHECKED_CAST")
    fun navigateToOutput() {
        setupUI(outputsUI, parentWindow.fuzzyComponent::injectOutputsUI as ComponentFunction)
    }

    @Suppress("UNCHECKED_CAST")
    fun navigateToAddOutput() {
        val ui = AddOutputUI()
        setupUI(ui, parentWindow.fuzzyComponent::injectAddOutputUI as ComponentFunction)
    }

    @Suppress("UNCHECKED_CAST")
    fun navigateToRange() {
        setupUI(rangeUI, parentWindow.fuzzyComponent::injectRangeUI as ComponentFunction)
    }

    fun navigateToInference() {
        println(":)")
    }
}