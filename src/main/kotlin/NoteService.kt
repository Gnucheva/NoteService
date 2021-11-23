class NoteService {

    val notes = mutableListOf<Note>() //список заметок в сервисе

    //Добавление заметки
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

    //Добавляет новый комментарий к заметке
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

    //Удаляет заметку текущего пользователя
    fun delete(id: Int) {
        val iterator = notes.iterator()
        while (iterator.hasNext()) {
            if (id == iterator.next().id) {
                iterator.remove()
            }
        }
    }

    //Удаляет комментарий к заметке
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

    //Редактирует заметку текущего пользователя
    fun edit(id: Int, title: String, text: String) {
        for (note in notes) {
            if (id == note.id) {
                note.title = title
                note.text = text
            }
        }
    }

    //Редактирует указанный комментарий у заметки
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

    //Возвращает список заметок, созданных пользователем
    fun getAllNotes(): List<Note>? {
        return notes.ifEmpty {
            null
        }
    }

    //Возвращает заметку по её id
    fun getById(id: Int): Note? {
        for (note in notes) {
            if (id == note.id) {
                return note
            }
        }
        return null
    }

    //Возвращает список комментариев к заметке
    fun getComments(noteId: Int): List<Comment>? {
//      Создаём список comments
        val comments = mutableListOf<Comment>()
        for (note in notes) {
            if (note.id == noteId) {
//      Проходимся по всем элементам коллекции note.comments
                for (comment in note.comments) {
                    if (!comment.isDeleted) {
                        comments.add(comment)
                    }

                }
                return comments
            }
        }
        return null
    }

    //Восстанавливает удалённый комментарий
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