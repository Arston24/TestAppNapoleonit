package ru.arston.practice.testappnapoleonit

import android.app.PendingIntent.getActivity
import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.daimajia.swipe.SwipeLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import ru.arston.practice.testappnapoleonit.NapoleonAPI.BannerModel
import ru.arston.practice.testappnapoleonit.NapoleonAPI.OfferModel
import java.text.DecimalFormat
import ru.arston.practice.testappnapoleonit.MainAdapter.HorizontalViewHolder
import ru.arston.practice.testappnapoleonit.MainAdapter.VerticalViewHolder



class MainAdapter(offerList: List<OfferModel>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var offers = listOf<OfferModel>()
    var banners = listOf<BannerModel>()
    private var context: Context? = null
    private var items: ArrayList<OfferModel> = arrayListOf()
    private val VERTICAL = 1
    private val HORIZONTAL = 2


    private var offer: ArrayList<OfferModel> = arrayListOf()
    private var banner: ArrayList<BannerModel> = arrayListOf()
    init {
        offer.addAll(offerList)
        //banner.addAll(bannerList)
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    id: Int): RecyclerView.ViewHolder  {
        val inflater = LayoutInflater.from(context)
        val view: View
        val holder: RecyclerView.ViewHolder
        when (id) {
            VERTICAL -> {
                view = LayoutInflater.from(parent.context)
                        .inflate(ru.arston.practice.testappnapoleonit.R.layout.vertical_card, parent, false)
                holder = VerticalViewHolder(view)
            }
            HORIZONTAL -> {
                view = LayoutInflater.from(parent.context)
                        .inflate(ru.arston.practice.testappnapoleonit.R.layout.horizontal_card, parent, false)
                holder = HorizontalViewHolder(view)
            }

            else -> {
                view = LayoutInflater.from(parent.context)
                        .inflate(ru.arston.practice.testappnapoleonit.R.layout.horizontal_card, parent, false)
                holder = HorizontalViewHolder(view)
            }
        }


        return holder
    }



    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, id: Int) {
        if (viewHolder.getItemViewType() === VERTICAL)
            verticalView(viewHolder as VerticalViewHolder)
        else if (viewHolder.getItemViewType() === HORIZONTAL)
            horizontalView(viewHolder as HorizontalViewHolder)

    }

    private fun verticalView(holder: VerticalViewHolder) {

        val adapter1 = OfferAdapter(offers)
        holder.recyclerView.setLayoutManager(LinearLayoutManager(context))
        holder.recyclerView.setAdapter(adapter1)
    }


    private fun horizontalView(holder: HorizontalViewHolder) {
        val adapter = BannerAdapter(banners)
        holder.recyclerView.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false))
        holder.recyclerView.setAdapter(adapter)
    }


    override fun getItemViewType(position: Int): Int {
        if (items?.get(position) is OfferModel)
            return VERTICAL
          else return HORIZONTAL
    }

//    override fun getItemViewType(position: Int) = when (items[position]) {
//        is OfferModel -> VERTICAL
//       // is BannerModel -> HORIZONTAL
//        else -> { HORIZONTAL
//        }
//    }

    class HorizontalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        internal var recyclerView: RecyclerView

        init {
            recyclerView = itemView.findViewById(R.id.inner_recyclerView)
        }
    }

    class VerticalViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var recyclerView: RecyclerView

        init {
            recyclerView = itemView.findViewById(R.id.inner_recyclerView)
        }
    }

    override fun getItemCount() = items.size


}

