package com.example.project1239.domain

import android.content.ClipDescription
import android.media.Rating
import java.io.Serializable
import java.net.URL

data class ItemsModel(
    var title:String="",
    var description: String="",
    var picUrl: ArrayList<String> = ArrayList(),
    var price: Double=0.0,
    var rating: Double=0.0,
    var numberInCart: Int=0,
    var extra: String=""

): Serializable
