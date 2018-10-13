package di

import dagger.Module
import dagger.Provides
import ui.ParentWindow

@Module
class UIModule {
    @Provides
    fun provideParentWindow() = ParentWindow()
}