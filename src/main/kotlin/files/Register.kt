package files

data class Register<T>(
        val id: Long,
        val position: Long,
        val data: T
)