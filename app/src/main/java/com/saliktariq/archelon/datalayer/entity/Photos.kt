package com.saliktariq.archelon.datalayer.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photos(
    @PrimaryKey(autoGenerate = true)
    var photoID: Long,
    var photoFileName: String,
    var nestID: Long?,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var photo: ByteArray?
)

