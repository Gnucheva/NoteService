import org.junit.Assert.*
import org.junit.Test

class NoteServiceTest {
    @Test
    fun addNoteIfListEmpty() {
        val service = NoteService()
        val comment = Comment(text = "First Comment")
        val note = Note(
            title = "First Note",
            text = "First Text",
            comments = mutableListOf(comment)
        )
        service.add(note)

        val result = note.id

        assertEquals(1, result)
    }

    @Test
    fun addNoteIfListNotEmpty() {
        val service = NoteService()
        val comment = Comment(text = "First Comment")
        val noteFirst = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf(comment)
        )

        val noteSecond = Note(
            title = "Second Title",
            text = "Second Text",
            comments = mutableListOf(comment)
        )

        service.add(noteFirst)
        service.add(noteSecond)

        val result = noteSecond.id

        assertEquals(2, result)
    }

    @Test
    fun deleteNoteWithExistId() {
        val service = NoteService()
        val comment = Comment(text = "First Comment")
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf(comment)
        )
        service.add(note)
        service.delete(1)

        val result = service.notes.size

        assertEquals(0, result)
    }

    @Test
    fun deleteNoteWithNotExistId() {
        val service = NoteService()
        val comment = Comment(text = "First Comment")
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf(comment)
        )
        service.add(note)
        service.delete(2)

        val result = service.notes.size

        assertEquals(1, result)
    }

    @Test
    fun editNoteWithExistId() {
        val service = NoteService()
        val comment = Comment(text = "First Comment")
        val title = "First Title"
        val text = "First Text"
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf(comment)
        )

        service.add(note)
        service.edit(1, title, text)

        val resultTitle = note.title
        val resultText = note.text

        assertEquals(title, resultTitle)
        assertEquals(text, resultText)
    }

    @Test
    fun editNoteWithNotExistId() {
        val service = NoteService()
        val comment = Comment(text = "First Comment")
        val title = "First Title"
        val text = "First Text"
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf(comment)
        )

        service.add(note)
        service.edit(2, title, text)

        val resultTitle = note.title
        val resultText = note.text

        assertEquals("First Title", resultTitle)
        assertEquals("First Text", resultText)
    }

    @Test
    fun getAllNotes() {
        val service = NoteService()
        val comment = Comment(text = "First Comment")
        val noteFirst = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf(comment)
        )

        val noteSecond = Note(
            title = "Second Title",
            text = "Second Text",
            comments = mutableListOf(comment)
        )

        val noteThird = Note(
            title = "Third Title",
            text = "Third Text",
            comments = mutableListOf(comment)
        )

        service.add(noteFirst)
        service.add(noteSecond)
        service.add(noteThird)

        val result = service.getAllNotes()?.size

        assertEquals(3, result)
    }

    @Test
    fun getNoteIfExistId() {
        val service = NoteService()
        val comment = Comment(text = "First Comment")
        val noteFirst = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf(comment)
        )

        val noteSecond = Note(
            title = "Second Title",
            text = "Second Text",
            comments = mutableListOf(comment)
        )

        service.add(noteFirst)
        service.add(noteSecond)

        val result = service.getById(2)

        assertEquals(2, result?.id)
    }

    @Test
    fun getNoteIfNotExistId() {
        val service = NoteService()
        val comment = Comment(text = "First Comment")
        val noteFirst = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf(comment)
        )

        val noteSecond = Note(
            title = "Second Title",
            text = "Second Text",
            comments = mutableListOf(comment)
        )

        service.add(noteFirst)
        service.add(noteSecond)

        val result = service.getById(3)

        assertEquals(null, result?.id)
    }

    @Test
    fun addCommentIfCommentsListIsEmpty() {
        val service = NoteService()
        val comment = Comment(text = "First Comment")
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf()
        )
        service.add(note)
        service.createComment(1, comment)

        val result = note.comments.size

        assertEquals(1, result)
    }

    @Test
    fun addCommentIfCommentsListIsNotEmpty() {
        val service = NoteService()
        val commentFirst = Comment(text = "First Comment")
        val commentSecond = Comment(text = "Second Comment")
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf()
        )

        service.add(note)
        service.createComment(1, commentFirst)
        service.createComment(1, commentSecond)

        val result = commentSecond.id

        assertEquals(2, result)
    }

    @Test
    fun addCommentNotValidNote() {
        val service = NoteService()
        val comment = Comment(text = "First Comment")
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf()
        )
        service.add(note)
        service.createComment(2312, comment)

        val result = note.comments.size

        assertEquals(0, result)
    }

    @Test
    fun deleteComment() {
        val service = NoteService()
        val commentFirst = Comment(text = "First Comment")
        val commentSecond = Comment(text = "Second Comment")
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf()
        )
        service.add(note)
        service.createComment(1, commentFirst)
        service.createComment(1, commentSecond)
        service.deleteComment(1, 1)

        val result = commentFirst.isDeleted

        assertTrue(result)
    }

    @Test
    fun deleteCommentIfNotExistNote() {
        val service = NoteService()
        val commentFirst = Comment(text = "First Comment")
        val commentSecond = Comment(text = "Second Comment")
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf()
        )
        service.add(note)
        service.createComment(1, commentFirst)
        service.createComment(1, commentSecond)
        service.deleteComment(2, 1)

        val result = note.comments.size

        assertEquals(2, result)
    }

    @Test
    fun deleteDeletedComment() {
        val service = NoteService()
        val commentFirst = Comment(text = "First Comment", isDeleted = true)
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf()
        )
        service.add(note)
        service.createComment(1, commentFirst)
        service.deleteComment(1, 1)

        val resultFirst = note.comments.size
        val resultSecond = commentFirst.isDeleted

        assertEquals(1, resultFirst)
        assertEquals(true, resultSecond)
    }

    @Test
    fun restoreComment() {
        val service = NoteService()
        val comment = Comment(text = "First Comment")
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf()
        )
        service.add(note)
        service.createComment(1, comment)
        service.deleteComment(1, 1)
        service.restoreComment(1, 1)

        val resultFirst = comment.isDeleted
        val resultSecond = note.comments.size

        assertFalse(resultFirst)
        assertEquals(1, resultSecond)
    }

    @Test
    fun restoreCommentIfNotExistNote() {
        val service = NoteService()
        val comment = Comment(text = "First Comment")
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf()
        )
        service.add(note)
        service.createComment(1, comment)
        service.deleteComment(1, 1)
        service.restoreComment(2, 1)

        val resultFirst = comment.isDeleted
        val resultSecond = note.comments.size

        assertTrue(resultFirst)
        assertEquals(1, resultSecond)
    }

    @Test
    fun restoreNotValidComment() {
        val service = NoteService()
        val comment = Comment(text = "First Comment", isDeleted = false)
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf()
        )
        service.add(note)
        service.createComment(1, comment)
        service.restoreComment(1, 2)

        val result = note.comments.size

        assertEquals(1, result)
    }

    @Test
    fun getComments() {
        val service = NoteService()
        val commentFirst = Comment(text = "First Comment")
        val commentSecond = Comment(text = "Second Comment")
        val commentThird = Comment(text = "Third Comment")
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf()
        )
        service.add(note)
        service.createComment(1, commentFirst)
        service.createComment(1, commentSecond)
        service.createComment(1, commentThird)

        val result = service.getComments(1)?.size

        assertEquals(3, result)
    }

    @Test
    fun getCommentsNotExistNote() {
        val service = NoteService()
        val commentFirst = Comment(text = "First Comment")
        val commentSecond = Comment(text = "Second Comment")
        val commentThird = Comment(text = "Third Comment")
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf()
        )
        service.add(note)
        service.createComment(1, commentFirst)
        service.createComment(1, commentSecond)
        service.createComment(1, commentThird)

        val result = service.getComments(2)?.size

        assertEquals(null, result)
    }

    @Test
    fun editComment() {
        val service = NoteService()
        val comment = Comment(text = "First Comment")
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf()
        )
        service.add(note)
        service.createComment(1, comment)
        service.editComment(1, 1, "New Text")

        val result = comment.text

        assertEquals("New Text", result)
    }

    @Test
    fun editCommentIfNotExistNote() {
        val service = NoteService()
        val comment = Comment(text = "First Comment")
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf()
        )
        service.add(note)
        service.createComment(1, comment)
        service.editComment(2, 1, "New Text")

        val result = comment.text

        assertEquals("First Comment", result)
    }

    @Test
    fun editDeletedComment() {
        val service = NoteService()
        val comment = Comment(text = "First Comment", isDeleted = true)
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf()
        )
        service.add(note)
        service.createComment(1, comment)
        service.editComment(1, 1, "New Text")

        val result = comment.text

        assertEquals("First Comment", result)
    }

    @Test
    fun editNotValidComment() {
        val service = NoteService()
        val comment = Comment(text = "First Comment", isDeleted = false)
        val note = Note(
            title = "First Title",
            text = "First Text",
            comments = mutableListOf()
        )
        service.add(note)
        service.createComment(1, comment)
        service.editComment(1, 2, "New Text")

        val result = comment.text

        assertEquals("First Comment", result)
    }
}