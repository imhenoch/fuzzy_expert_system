package di

import dagger.Component
import ui.ParentWindow
import ui.home.HomeUI
import ui.outputs.AddOutputUI
import ui.outputs.OutputsUI
import ui.range.RangeUI
import ui.variables.AddLabelUI
import ui.variables.VariablesUI

@Component(modules = [
    UIModule::class
])
@UIScope
interface FuzzyComponent {
    fun injectParentWindow(parentWindow: ParentWindow)

    fun injectHomeUI(homeUI: HomeUI)

    fun injectVariablesUI(variablesUI: VariablesUI)

    fun injectAddLabelUI(addLabelUI: AddLabelUI)

    fun injectOutputsUI(outputsUI: OutputsUI)

    fun injectAddOutputUI(addOutputUI: AddOutputUI)

    fun injectRangeUI(rangeUI: RangeUI)
}