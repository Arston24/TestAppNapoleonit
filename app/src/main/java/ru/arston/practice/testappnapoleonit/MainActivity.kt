package ru.arston.practice.testappnapoleonit

import android.app.DownloadManager
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.FontsContract.Columns.RESULT_CODE_OK
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recycle.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.arston.practice.testappnapoleonit.NapoleonAPI.Api
import ru.arston.practice.testappnapoleonit.NapoleonAPI.BannerModel
import ru.arston.practice.testappnapoleonit.NapoleonAPI.OfferModel




class MainActivity : AppCompatActivity() {

    var offers = listOf<OfferModel>()
    var banners = listOf<BannerModel>()
    lateinit var linearLayout: LinearLayout
    private lateinit var offerAdapter: OfferAdapter



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //offerList.layoutManager = LinearLayoutManager(this)
        //val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val myList = findViewById(R.id.recyclerBanner) as RecyclerView
        myList.layoutManager = layoutManager


        linearLayout = findViewById(R.id.inner_recyclerView)

        fetchJson()
    }
        fun fetchJson() {

        val url = "https://s3.eu-central-1.amazonaws.com/sl.files/"

        var retrofit: Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()

        var apiServices: Api = retrofit.create(Api::class.java)

        apiServices.getOffers().enqueue(object : retrofit2.Callback<List<OfferModel>> {
            override fun onFailure(call: Call<List<OfferModel>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<OfferModel>>, response: Response<List<OfferModel>>) {
                offers = response.body()!!
                initOffers(offers)
                //runAdapter(offers)

            }

        })


        apiServices.getBanners().enqueue(object : retrofit2.Callback<List<BannerModel>> {
            override fun onFailure(call: Call<List<BannerModel>>, t: Throwable) {
            }


            override fun onResponse(call: Call<List<BannerModel>>, response: Response<List<BannerModel>>) {
                banners = response.body()!!
                runAdapterBanner(banners)

                val snapHelper = LinearSnapHelper()
                snapHelper.attachToRecyclerView(offerList)

            }
        })


    }

    private fun initOffers(offers: List<OfferModel>) {
        val groups = arrayListOf<String>()
        val offersList = arrayListOf<OfferModel>()
        offers.forEach {
            if (!groups.contains(it.getGroupName())) {
                groups.add(it.getGroupName())
            }
        }

        for (i in 0 until groups.size){
            val name = groups[i]
            offers.forEach {
                if (it.getGroupName() == name){
                    offersList.add(it)
                }
            }
            initOfferGroup(name)
            runAdapter(offersList)
            offersList.clear()
        }
    }

    private fun initOfferGroup(title: String) {
        val textView = layoutInflater.inflate(
                R.layout.header_item, null)
        textView.findViewById<TextView>(R.id.header_text).text = title
        linearLayout.addView(textView)
    }

    fun runAdapter(param: List<OfferModel>) {

        val offerView = layoutInflater.inflate(
                R.layout.recycle, null, false) as RecyclerView
        offerView.layoutManager = LinearLayoutManager(this)
        offerAdapter = OfferAdapter(param)
        offerView.adapter = offerAdapter


        linearLayout.addView(offerView)
        //offerAdapter.notifyDataSetChanged()

        var offerAdapter: OfferAdapter = OfferAdapter(param)
        offerList.setAdapter(offerAdapter)

    }

    fun runAdapterBanner(param: List<BannerModel>) {
        var bannerAdapter: BannerAdapter = BannerAdapter(param)
        recyclerBanner.setAdapter(bannerAdapter)
    }
}