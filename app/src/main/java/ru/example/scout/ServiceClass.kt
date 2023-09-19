package ru.example.scout

data class MyViewState(
    val loading: Boolean = false,
    val data: String? = null,
    val error: String? = null
)

class OutputText {
    fun SomeText() {
        print("Test string")
    }
}