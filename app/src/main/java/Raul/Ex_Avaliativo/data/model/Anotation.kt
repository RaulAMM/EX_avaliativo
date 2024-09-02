package Raul.Ex_Avaliativo.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "tb_diario")
class Anotation(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")

    var id: Long = 0,
    @NonNull

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "anotation")
    var anotation: String = "",

    @ColumnInfo(name = "anotation")
    var local: String = "",

    deadLineDate: LocalDate = LocalDate.now()
) {
    @ColumnInfo(name = "deadline")
    var deadline: String = ""

    @Ignore
    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    init {
        deadline = formatter.format(deadLineDate)
    }
    fun isHighPriority(): Boolean{
        val dead = LocalDate.parse(deadline, formatter)
        return dead <= LocalDate.now()
    }
}