package com.saliktariq.archelon.datalayer.entity


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Nest(
    @PrimaryKey(autoGenerate = true)
    var nestID: Long,
    var photoID: Long,
    var nestCode: String?,
    var protectionMeasures: String?,
    var inundatedDuringIncubation: Boolean?,
    var predationDuringIncubation: Boolean?,
    var dateOfFirstHatching: Long?,
    var dateOfLastHatching: Long?,
    var inundatedDuringHatching: Boolean?,
    var predatedDuringHatching: Boolean?,
    var affectedByLightPollution: Boolean?,
    var excavationDate: Long?,
    var excavationBottomOfNestDepth: Float?,
    var hatchedEggs: Int?,
    var pippedDeadHatchlings: Int?,
    var pippedLiveHatchlings: Int?,
    var noEmbryosInUnhatchedEggs: Int?,
    var deadEmbryosInUnhatchedEggsEyeSpot: Int?,
    var deadEmbryosInUnhatchedEggsEarly: Int?,
    var deadEmbryosInUnhatchedEggsMiddle: Int?,
    var deadEmbryosInUnhatchedEggsEyeLate: Int?,
    var liveEmbryosInUnhatchedEggs: Int?,
    var deadHatchlingsInNest: Int?,
    var liveHatchlingsInNest: Int?,
    var excavationComments: String?,
    var generalComments: String?
)

