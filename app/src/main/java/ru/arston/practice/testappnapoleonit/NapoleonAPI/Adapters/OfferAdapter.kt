package ru.arston.practice.testappnapoleonit.NapoleonAPI.Adapters

import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.daimajia.swipe.SwipeLayout
import com.squareup.picasso.Picasso
import ru.arston.practice.testappnapoleonit.NapoleonAPI.OfferModel
import ru.arston.practice.testappnapoleonit.R
import java.text.DecimalFormat


class OfferAdapter(offerList: List<OfferModel>) :
        RecyclerView.Adapter<OfferAdapter.MainViewHolder>() {



    private var offer: ArrayList<OfferModel> = arrayListOf()
    init {
        offer.addAll(offerList)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    id: Int): MainViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.vertical_card, parent, false)
        return MainViewHolder(view)
    }



    override fun onBindViewHolder(viewHolder: MainViewHolder, id: Int) {
        val oldPrice = DecimalFormat("#0.0").
                format(offer[id].getPrice()/offer[id].getDiscount())
        viewHolder.name.text = offer[id].getName()
        viewHolder.desc.text = offer[id].getDesc()
        viewHolder.newPrice.text = ("${offer[id].getPrice()}$")
        viewHolder.oldPrice.text = ("$oldPrice$")
        viewHolder.discount.text = ("${(100*offer[id].getDiscount()).toLong()}%")

        if (offer[id].getType() == "item"){
            viewHolder.discount.visibility = View.INVISIBLE
            viewHolder.newPrice.visibility = View.INVISIBLE
            viewHolder.oldPrice.visibility = View.INVISIBLE
        }

        Picasso.get()
                .load(offer[id].getImage())
                .fit()
                .centerInside()
                .into(viewHolder.image)

    }
    class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        internal var cardOffer: SwipeLayout
        internal var name: TextView
        internal var desc: TextView
        internal var newPrice: TextView
        internal var oldPrice: TextView
        internal var discount: TextView
        internal var image: ImageView


        init {
            cardOffer = itemView.findViewById(R.id.cardviewOffer)
            name = itemView.findViewById(R.id.offerTitleTextView)
            desc = itemView.findViewById(R.id.offerSubtitleTextView)
            newPrice = itemView.findViewById(R.id.offerActualPriceTextView)
            oldPrice = itemView.findViewById(R.id.offerOldPriceTextView)
            discount = itemView.findViewById(R.id.offerPercentsTextView)
            image = itemView.findViewById(R.id.offerImageMainContainer)
            oldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        }

    }

    override fun getItemCount() = offer.size
}

