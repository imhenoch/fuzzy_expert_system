package files

abstract class BinaryObject(val fileName: String) {
    fun fetch() {

    }

    fun commit() {
        println(dataSpecification())
    }

    fun delete() {

    }

    abstract fun dataSpecification(): List<DataSpecification>
}