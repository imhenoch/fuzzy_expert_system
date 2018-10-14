package files

import kotlin.reflect.KClass

data class DataSpecification(
        val data: Any,
        val type: KClass<*>,
        val length: Int = 0
)