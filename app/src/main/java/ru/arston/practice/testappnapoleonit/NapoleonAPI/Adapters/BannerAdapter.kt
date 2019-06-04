package ru.arston.practice.testappnapoleonit.NapoleonAPI.Adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import ru.arston.practice.testappnapoleonit.NapoleonAPI.BannerModel
import ru.arston.practice.testappnapoleonit.R


class BannerAdapter(list: List<BannerModel>) :
        RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    private var offer: ArrayList<BannerModel> = arrayListOf()
    init {
        offer.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    id: Int): BannerViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.horizontal_card, parent, false)
        return BannerViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: BannerViewHolder, id: Int) {

        Picasso.get()
                .load(offer[id].image)
                .fit()
                .centerInside()
                .into(viewHolder.image)

    }
    class BannerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        internal var cv_inStock: CardView
        internal var image: ImageView

        init {
            cv_inStock = itemView.findViewById(R.id.banner_card)
            image = itemView.findViewById(R.id.banner_image)

        }

    }

    override fun getItemCount() = offer.size
}

