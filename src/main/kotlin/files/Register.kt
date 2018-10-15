package files

data class Register<T>(
        val id: Long,
        val position: Long = 0,
        val data: T? = null
)