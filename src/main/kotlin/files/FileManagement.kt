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
        if (i < data.length)
            file.writeChar(data[i].toInt())
        else
            file.writeChar('*'.toInt())
    }
}

fun readAll(index: RandomAccessFile, master: RandomAccessFile, dataSpecification: List<DataSpecification>): ArrayList<Any> {
    val data = ArrayList<Any>()

    while (index.filePointer != index.length()) {
        data.add(index.readLong())
        data.add(index.readLong())
        master.seek(data.last() as Long)
        dataSpecification.forEach { ds ->
            when (ds.type) {
                Int::class -> data.add(master.readInt())
                Long::class -> data.add(master.readLong())
                String::class -> data.add(readString(master, ds.length))
            }
        }
    }

    return data
}

fun readString(file: RandomAccessFile, length: Int): String {
    var string = ""

    for (i in 0 until length)
        string += file.readChar()

    return string
}