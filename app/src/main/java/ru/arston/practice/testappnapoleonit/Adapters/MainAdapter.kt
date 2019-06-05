package ru.arston.practice.testappnapoleonit.Adapters

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import ru.arston.practice.testappnapoleonit.Model.BannerModel
import ru.arston.practice.testappnapoleonit.Model.OfferModel
import ru.arston.practice.testappnapoleonit.R
import java.text.DecimalFormat


class MainAdapter(private val offerList: List<OfferModel>, private val bannerList: List<BannerModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.e("ViewType", viewType.toString())
        return when (viewType) {
            CellType.HORIZONTAL.ordinal -> BannerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.banner_card, parent, false))
            else -> OfferViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.vertical_card, parent, false))

        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, id: Int) {
        when (viewHolder.itemViewType) {
            CellType.HORIZONTAL.ordinal -> {
                val bannerViewHolder = viewHolder as BannerViewHolder
                val itemBannerAdapter = BannerAdapter(bannerList)
                bannerViewHolder.itemBannerRecycler.setHasFixedSize(false)
                bannerViewHolder.itemBannerRecycler.layoutManager = LinearLayoutManager(viewHolder.itemBannerRecycler.context, LinearLayoutManager.HORIZONTAL, false)
                bannerViewHolder.itemBannerRecycler.adapter = itemBannerAdapter
                bannerViewHolder.itemBannerRecycler.isNestedScrollingEnabled = false
            }
            CellType.VERTICAL.ordinal -> {
                val offerViewHolder = viewHolder as OfferViewHolder
                val oldPrice = DecimalFormat("#0.0").format(offerList[id-1].getPrice() / offerList[id-1].getDiscount())
                offerViewHolder.name.text = offerList[id-1].getName()
                offerViewHolder.desc.text = offerList[id-1].getDesc()
                offerViewHolder.newPrice.text = ("${offerList[id-1].getPrice()}$")
                offerViewHolder.oldPrice.text = ("$oldPrice$")
                offerViewHolder.discount.text = ("${(100 * offerList[id-1].getDiscount()).toLong()}%")

                if (offerList[id-1].getType() == "item") {
                    offerViewHolder.discount.visibility = View.INVISIBLE
                    offerViewHolder.newPrice.visibility = View.INVISIBLE
                    offerViewHolder.oldPrice.visibility = View.INVISIBLE
                }

                Picasso.get()
                        .load(offerList[id-1].getImage())
                        .fit()
                        .centerInside()
                        .into(offerViewHolder.image)

            }
        }
    }

    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemBannerRecycler: RecyclerView = itemView.findViewById(R.id.banner_recycler)
    }

    class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var name: TextView = itemView.findViewById(R.id.offerTitleTextView)
        internal var desc: TextView = itemView.findViewById(R.id.offerSubtitleTextView)
        internal var newPrice: TextView = itemView.findViewById(R.id.offerActualPriceTextView)
        internal var oldPrice: TextView = itemView.findViewById(R.id.offerOldPriceTextView)
        internal var discount: TextView = itemView.findViewById(R.id.offerPercentsTextView)
        internal var image: ImageView = itemView.findViewById(R.id.offerImageMainContainer)
    }

    enum class CellType(viewType: Int) {
        HORIZONTAL(0),
        VERTICAL(1)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> CellType.HORIZONTAL.ordinal
            else -> CellType.VERTICAL.ordinal
        }
    }


    override fun getItemCount() = offerList.size + 1
}

