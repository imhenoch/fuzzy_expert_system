package files

import java.io.RandomAccessFile

abstract class BinaryObject(fileName: String) {
    val index = RandomAccessFile("$fileName.index", "rw")
    val master = RandomAccessFile("$fileName.master", "rw")

    fun fetchAll(): ArrayList<FileData> {
        val data = ArrayList<FileData>()
        val ds = dataSpecification()
        val rawData = readAll(index, master, ds)
        var fileData: FileData
        val iterations = rawData.size / (ds.size + 2)
        var counter = 0

        repeat(iterations) { i ->
            fileData = FileData(rawData[counter] as Long, rawData[++counter] as Long)
            data.add(fileData)
            ds.forEach { ds ->
                fileData.data.add(rawData[++counter])
            }
            counter++
        }

        return data
    }

    fun init() {
        val ds = dataSpecification()
        val (id, position) = write(master, ds)
        write(index, arrayListOf(
                DataSpecification(id, Long::class),
                DataSpecification(position, Long::class)
        ))
    }

    fun commit() {

    }

    fun delete() {

    }

    abstract fun dataSpecification(): List<DataSpecification>
}