class NoteService {

    val notes = mutableListOf<Note>()

    fun add(note: Note) {
//        Если список пустой, присвоить заметке id = 1, если список не пустой,
//        присвоить заметке id на 1 больше, чем id у последней заметки в списке
        if (notes.isEmpty()) {
            note.id = 1
        } else {
            note.id = notes.last().id + 1
        }
        notes.add(note)
    }

    fun createComment(id: Int, comment: Comment) {
        for (note in notes) {
            if (id == note.id) {
//        Если список пустой, присвоить полю id значение 1, если список не пустой,
//        присвоить полю id значение на 1 больше, чем у поля id последнего комментария в списке
                if (note.comments.isEmpty()) {
                    comment.id = 1
                } else {
                    comment.id = note.comments.last().id + 1
                }
                note.comments.add(comment)
            }
        }
    }

    fun delete(id: Int) {
        val iterator = notes.iterator()
        while (iterator.hasNext()) {
            if (id == iterator.next().id) {
                iterator.remove()
            }
        }
    }

    fun deleteComment(noteId: Int, commentId: Int) {
        for (note in notes) {
            if (noteId == note.id) {
                for (comment in note.comments) {
                    if (comment.id == commentId && !comment.isDeleted) {
                        comment.isDeleted = true
                    }
                }
            }
        }
    }

    fun edit(id: Int, title: String, text: String) {
        for (note in notes) {
            if (id == note.id) {
                note.title = title
                note.text = text
            }
        }
    }

    fun editComment(noteId: Int, commentId: Int, text: String) {
        for (note in notes) {
            if (noteId == note.id) {
                for (comment in note.comments) {
                    if (comment.id == commentId && !comment.isDeleted) {
                        comment.text = text
                    }
                }
            }
        }
    }

    fun getAllNotes(): List<Note>? {
        return notes.ifEmpty {
            null
        }
    }

    fun getById(id: Int): Note? {
        for (note in notes) {
            if (id == note.id) {
                return note
            }
        }
        return null
    }

    fun getComments(noteId: Int): List<Comment>? {
//      Создаём список comments
        val comments = mutableListOf<Comment>()
        for (note in notes) {
            if (note.id == noteId) {
//      Проходимся по всем элементам коллекции note.comments
//      и на каждом шаге проверяем значение поля isDeleted у комментария
//      Если оно равно false (т.е. комментарий не удалён, то добавляем этот комментарий в список comments
                for (comment in note.comments) {
                    if (!comment.isDeleted) {
                        comments.add(comment)
                    }
                }
//      Переносим все значения из списка comments в список note.commets (В список попадают только неудалённые
//      элементы и возвращаем этот список
                note.comments = comments.toMutableList()
                return note.comments
            }
        }
        return null
    }

    fun restoreComment(noteId: Int, commentId: Int) {
        for (note in notes) {
            if (noteId == note.id) {
                for (comment in note.comments) {
                    if (comment.id == commentId && comment.isDeleted) {
                        comment.isDeleted = false
                    }
                }
            }
        }
    }
}