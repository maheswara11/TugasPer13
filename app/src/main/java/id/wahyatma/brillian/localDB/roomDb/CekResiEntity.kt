package id.wahyatma.brillian.localDB.roomDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "tableCekOgkir" )
data class CekResiEntity (
    @PrimaryKey(autoGenerate = false)
    val resi: String,
    val courier: String?= "",
    val status: String?= ""
)