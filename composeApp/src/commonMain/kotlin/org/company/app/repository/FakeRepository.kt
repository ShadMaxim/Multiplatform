package org.company.app.repository

import kotlinx.coroutines.flow.MutableStateFlow
import org.company.app.data.Model

private val fakeInitialNotes = mutableListOf(
    Model("0", title = "This is my first note", content = "Wow!"),
    Model("1", title = "You can create one", content = "Sounds great!"),
    Model("2", title = "Or you can edit one", content = "Awesome!"),
    Model("3", title = "You can also delete one", content = "Ok!"),
    Model("4", title = "Finally, you can click one to view", content = "Thanks!"),
)

class FakeRepository {
    private val watchers = hashMapOf<Int, MutableStateFlow<Model>>()
    val items = MutableStateFlow(fakeInitialNotes)

    fun get(id: String): Model? {
        return items.value.firstOrNull { it.id == id }
    }

    fun add(title: String, content: String) {
        items.value.let {
            items.value = (it + Model(id = items.value.size.toString(), title = title, content = content)).toMutableList()
        }
    }

    fun remove(note: Model) {
        items.value.let {
            items.value = (it - note).toMutableList()
        }
    }

    fun update(note: Model) {
        watchers[note.id?.toInt()]?.let {
            it.value = note
        }
        get(note.id.toString())?.let { n ->
            items.value.let { list ->
                list[list.indexOf(n)] = note
                items.value = list
            }
        }
    }

    fun getLiveData(id: String): MutableStateFlow<Model> {
        return get(id)?.let {
            watchers.getOrPut(id.toInt()) {
                MutableStateFlow(it)
            }
        } ?: throw IllegalArgumentException()
    }
}