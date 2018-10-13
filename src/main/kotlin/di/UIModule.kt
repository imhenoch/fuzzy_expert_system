package di

import dagger.Module
import dagger.Provides
import ui.ParentWindow
import ui.common.Navigation

@Module
class UIModule(private val parentWindow: ParentWindow) {
    @Provides
    @UIScope
    fun providesNavigation() = Navigation(parentWindow)
}