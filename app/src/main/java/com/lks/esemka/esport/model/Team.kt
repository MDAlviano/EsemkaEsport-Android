package com.lks.esemka.esport.model

import android.os.Parcel
import android.os.Parcelable

data class Team(
    val id: Int,
    val name: String?,
    val about: String?,
    val kills: Int?,
    val deaths: Int?,
    val assists: Int?,
    val gold: Int?,
    val damage: Int?,
    val lordKills: Int?,
    val tortoiseKills: Int?,
    val towerDestroy: Int?,
    val logo500: String?,
    val logo256: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(about)
        parcel.writeValue(kills)
        parcel.writeValue(deaths)
        parcel.writeValue(assists)
        parcel.writeValue(gold)
        parcel.writeValue(damage)
        parcel.writeValue(lordKills)
        parcel.writeValue(tortoiseKills)
        parcel.writeValue(towerDestroy)
        parcel.writeString(logo500)
        parcel.writeString(logo256)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Team> {
        override fun createFromParcel(parcel: Parcel): Team {
            return Team(parcel)
        }

        override fun newArray(size: Int): Array<Team?> {
            return arrayOfNulls(size)
        }
    }
}
