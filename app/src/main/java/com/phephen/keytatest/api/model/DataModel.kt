package com.phephen.keytatest.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by Fendy Saputro on 16/12/2020.
 * vidis194@gmail.com
 */
@Parcelize
data class DataModel(
        @field:SerializedName("id")
        val id: Int = 0,

        @field:SerializedName("name")
        val name: String? = null
) : Parcelable
