package org.company.app.viewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import moe.tlaster.precompose.stateholder.SavedStateHolder
import moe.tlaster.precompose.viewmodel.ViewModel
import org.company.app.repository.FakeRepository

class HomeViewModel(
    private val id: Int?,
    savedStateHolder: SavedStateHolder,
    private val fakeRepository: FakeRepository,
): ViewModel() {
    val name = MutableStateFlow("")
    fun setName(value: String) {
        name.update { value }
    }
}