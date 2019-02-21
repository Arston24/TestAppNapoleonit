package ru.arston.practice.testappnapoleonit.NapoleonAPI

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class BannerModel (

@SerializedName("id")
val id: String,

@SerializedName("title")
val title: String,

@SerializedName("desc")
val desc: String,

@SerializedName("image")
val image: String
)