package org.company.app.viewModel

import moe.tlaster.precompose.viewmodel.ViewModel
import org.company.app.data.Model
import org.company.app.repository.FakeRepository

class NoteListViewModel(
    private val fakeRepository: FakeRepository,
) : ViewModel() {
    val items by lazy {
        fakeRepository.items
    }
    fun delete(note: Model) {
        fakeRepository.remove(note = note)
    }
}