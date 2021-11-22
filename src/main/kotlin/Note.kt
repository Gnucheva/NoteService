data class Note(
    var id: Int = 0,
    var title: String,
    var text: String,
    val date: Int = 0,
    var comments: MutableList<Comment>,
    val viewUrl: String = "https://www.vk.com",
    val canComment: Boolean = true
)