package files

import java.io.RandomAccessFile

fun write(file: RandomAccessFile, dataSpecification: List<DataSpecification>): Pair<Long, Long> {
    val position = file.length()
    val id: Long
    file.seek(position)
    dataSpecification.forEach { ds ->
        when (ds.type) {
            Int::class -> file.writeInt(ds.data as Int)
            Long::class -> file.writeLong(ds.data as Long)
            String::class -> writeString(file, ds.data as String, ds.length)
        }
    }
    id = file.length() / (file.filePointer - position)
    return Pair(id, position)
}

private fun writeString(file: RandomAccessFile, data: String, length: Int) {
    for (i in 0 until length) {
        if (i < data.length - 1)
            file.writeChar(data[i].toInt())
        else
            file.writeChar('*'.toInt())
    }
}