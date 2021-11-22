data class Comment(
    var id: Int = 0,
    var count: Int = 0,
    var text: String,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = false,
    val canClose: Boolean = true,
    val canOpen: Boolean = true,
    var isDeleted: Boolean = false
)