package files

data class FileData(
        val id: Long,
        val position: Long,
        val data: ArrayList<Any> = ArrayList()
)