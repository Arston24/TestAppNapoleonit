package ru.arston.practice.testappnapoleonit.NapoleonAPI

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("banners.json")
    fun getBanners(): Call<List<BannerModel>>
    @GET("offers.json")
    fun getOffers(): Call<List<OfferModel>>

}