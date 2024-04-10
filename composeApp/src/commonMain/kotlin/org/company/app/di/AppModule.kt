package org.company.app.di

import moe.tlaster.precompose.stateholder.SavedStateHolder
import org.company.app.repository.FakeRepository
import org.company.app.viewModel.HomeViewModel
import org.koin.dsl.module

object AppModule {

    val appModule = module {
        single { FakeRepository() }
        factory {(id: Int?, savedStateHolder: SavedStateHolder) ->
            HomeViewModel(//any field can be or not
                id = id,
                savedStateHolder = savedStateHolder,
                fakeRepository = get()
            )
        }
//        factory { HomeViewModel() }
    }
}