package di

import dagger.Component
import ui.ParentWindow
import ui.home.HomeUI
import ui.outputs.OutputsUI
import ui.range.RangeUI
import ui.variables.VariablesUI

@Component(modules = [
    UIModule::class
])
@UIScope
interface FuzzyComponent {
    fun injectParentWindow(parentWindow: ParentWindow)

    fun injectHomeUI(homeUI: HomeUI)

    fun injectVariablesUI(variablesUI: VariablesUI)

    fun injectOutputsUI(outputsUI: OutputsUI)

    fun injectRangeUI(rangeUI: RangeUI)
}