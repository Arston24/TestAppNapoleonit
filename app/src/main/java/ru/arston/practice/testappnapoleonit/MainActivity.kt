package ru.arston.practice.testappnapoleonit

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.arston.practice.testappnapoleonit.Adapters.BannerAdapter
import ru.arston.practice.testappnapoleonit.Adapters.MainAdapter
import ru.arston.practice.testappnapoleonit.Adapters.OfferAdapter
import ru.arston.practice.testappnapoleonit.Model.BannerModel
import ru.arston.practice.testappnapoleonit.Model.OfferModel
import ru.arston.practice.testappnapoleonit.NapoleonAPI.Api


class MainActivity : AppCompatActivity() {
    private val url = "https://s3.eu-central-1.amazonaws.com/sl.files/"

    var offers = listOf<OfferModel>()
    var banners = listOf<BannerModel>()
    lateinit var linearLayout: LinearLayout
    private lateinit var mainAdapter: MainAdapter
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var offerAdapter: OfferAdapter
    private lateinit var imageView: ImageView
    private lateinit var itemOffer: List<OfferModel>

    private lateinit var mainRecyclerView: RecyclerView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecyclerView = findViewById(R.id.main_recycler)
        mainRecyclerView.setHasFixedSize(true)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)


        imageView = findViewById(R.id.info_image)
        imageView.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)

        }

        fetchJson()
    }

    private fun fetchJson() {

        var retrofit: Retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()

        var apiService: Api = retrofit.create(Api::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            val offersRequest = apiService.getOffers()
            try {

                val response = offersRequest.await()
                if (response.isSuccessful) {

                    val offersResponse = response.body()
                    itemOffer = offersResponse!!
//                    offerAdapter = OfferAdapter(itemOffer)
//                    mainRecyclerView.adapter = offerAdapter

                } else {
                    Log.e("MainActivity ", response.errorBody().toString())
                }
            } catch (e: Exception) {
                println("Ебаа " + e.message)
            }

            val bannersRequest = apiService.getBanners()
            try {
                Log.e("MainActivity ", "НЕ Успех")

                val response = bannersRequest.await()
                if (response.isSuccessful) {
                    Log.e("MainActivity ", "Успех")
                    banners = response.body()!!
                    Log.e("Bnners ", banners[0].image)
                    mainAdapter = MainAdapter(itemOffer, banners)
                    mainRecyclerView.adapter = mainAdapter

                } else {
                    Log.e("MainActivity ", response.errorBody().toString())
                }
            } catch (e: Exception) {

            }

        }
    }

}