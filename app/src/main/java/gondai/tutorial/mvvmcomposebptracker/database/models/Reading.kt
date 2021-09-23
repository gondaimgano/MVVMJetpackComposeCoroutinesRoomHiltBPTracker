package gondai.tutorial.mvvmcomposebptracker.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
 data class Reading(
    @PrimaryKey(autoGenerate = true)
    val uid:Int,
    @ColumnInfo var sys:Int?=null,
    @ColumnInfo  var dia:Int?=null,
    @ColumnInfo
    var pulse:Int?=null,
    @ColumnInfo
    var weight:Int? = null,
    @ColumnInfo
    var spO2:Int? = null,
    @ColumnInfo
    val dateCreated:Date?=Date(System.currentTimeMillis()),
   @ColumnInfo
   val dateModified:Date?=Date(System.currentTimeMillis())


)