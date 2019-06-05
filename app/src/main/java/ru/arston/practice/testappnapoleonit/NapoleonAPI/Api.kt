package ru.arston.practice.testappnapoleonit.NapoleonAPI

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import ru.arston.practice.testappnapoleonit.Model.BannerModel
import ru.arston.practice.testappnapoleonit.Model.OfferModel

interface Api {
    @GET("banners.json")
    fun getBanners(): Deferred<Response<List<BannerModel>>>
    @GET("offers.json")
    fun getOffers(): Deferred<Response<List<OfferModel>>>

}