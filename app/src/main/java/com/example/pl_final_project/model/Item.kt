package com.example.pl_final_project.model

import java.util.Date
import java.util.UUID

interface Item {
    val id: String
    var text: String
    var isCompleted: Boolean
    val creationDate: Date
    var kanbanColumn: String
    fun toggleCompleteStatus()
}

abstract class AbstractItem(
    override val id: String = UUID.randomUUID().toString(),
    override var text: String,
    override var isCompleted: Boolean = false,
    override val creationDate: Date = Date(),
    override var kanbanColumn: String = "ToDo"
): Item {
    override fun toggleCompleteStatus() {
        isCompleted = !isCompleted
    }
}

data class SimpleTask(
    override val id: String = UUID.randomUUID().toString(),
    override var text: String,
    override var isCompleted: Boolean = false,
    override val creationDate: Date = Date(),
    override var kanbanColumn: String = "ToDo"
) : AbstractItem(id, text, isCompleted, creationDate,kanbanColumn)



data class TaskWithDeadline(
    override val id: String = UUID.randomUUID().toString(),
    override var text: String,
    override var isCompleted: Boolean = false,
    override val creationDate: Date = Date(),
    override var kanbanColumn: String = "ToDo",
    val deadline: Date
) : AbstractItem(id, text, isCompleted, creationDate, kanbanColumn) {
    fun isOverdue(): Boolean = !isCompleted && deadline.before(Date())
}
