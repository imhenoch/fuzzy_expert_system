package di

import dagger.Component
import ui.ParentWindow
import ui.home.HomeUI

@Component(modules = [
    UIModule::class
])
@UIScope
interface FuzzyComponent {
    fun injectParentWindow(parentWindow: ParentWindow)

    fun injectHomeUI(homeUI: HomeUI)
}