package com.saliktariq.archelon.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Location (
    @PrimaryKey(autoGenerate = true)
    var locationID: Long,
    var distance2sea: Float?,
    var distance2LMB: Float?,
    var distance2RMB: Float?,
    var distance2thirdBM: Float?,
    var gpsLatitude: Float?,
    var gpsLongitude: Float?,
    var subSectorName: String?,
    var tag: String?,
    var comments: String?,
    var nestID: Long?




)