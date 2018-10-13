package di

import dagger.Component
import ui.ParentWindow

@Component(modules = [
    UIModule::class
])
interface FuzzyComponent {
    fun parentWindow(): ParentWindow
}