package vkstatistic.apoyark.com.vkstatistics.domain.global.models.group

data class Photo(
        val id: Int,
        val album_id: Int,
        val owner_id: Int,
        val user_id: Int,
        val sizes: List<Size>,
        val text: String,
        val date: Int,
        val post_id: Int
)