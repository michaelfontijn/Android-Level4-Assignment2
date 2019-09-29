package com.example.rockpaperscissors_level4_assignment2.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

@Parcelize
@Entity(tableName = "Result")
data class Result(
    @ColumnInfo(name = "myRoll")
    val myRoll : Int,
    @ColumnInfo(name = "computerRoll")
    val computerRoll : Int,
    @ColumnInfo(name = "win")
    val ganeResult : Int,
    @ColumnInfo(name = "date")
    val date : String? = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date()),
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id : Long? = null
): Parcelable