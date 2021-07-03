package cz.lastaapps.routes.fragments

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Message(val message: String) : Parcelable
