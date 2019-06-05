package ru.arston.practice.testappnapoleonit.Adapters

import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.daimajia.swipe.SwipeLayout
import com.squareup.picasso.Picasso
import ru.arston.practice.testappnapoleonit.Model.OfferModel
import ru.arston.practice.testappnapoleonit.R
import java.text.DecimalFormat


class OfferAdapter(private val offerList: List<OfferModel>) : RecyclerView.Adapter<OfferAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, id: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vertical_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, id: Int) {

        val oldPrice = DecimalFormat("#0.0").format(offerList[id].getPrice() / offerList[id].getDiscount())
        viewHolder.name.text = offerList[id].getName()
        viewHolder.desc.text = offerList[id].getDesc()
        viewHolder.newPrice.text = ("${offerList[id].getPrice()}$")
        viewHolder.oldPrice.text = ("$oldPrice$")
        viewHolder.discount.text = ("${(100 * offerList[id].getDiscount()).toLong()}%")

        if (offerList[id].getType() == "item") {
            viewHolder.discount.visibility = View.INVISIBLE
            viewHolder.newPrice.visibility = View.INVISIBLE
            viewHolder.oldPrice.visibility = View.INVISIBLE
        }

        Picasso.get()
                .load(offerList[id].getImage())
                .fit()
                .centerInside()
                .into(viewHolder.image)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var cardOffer: SwipeLayout = itemView.findViewById(R.id.cardviewOffer)
        internal var name: TextView = itemView.findViewById(R.id.offerTitleTextView)
        internal var desc: TextView = itemView.findViewById(R.id.offerSubtitleTextView)
        internal var newPrice: TextView = itemView.findViewById(R.id.offerActualPriceTextView)
        internal var oldPrice: TextView = itemView.findViewById(R.id.offerOldPriceTextView)
        internal var discount: TextView = itemView.findViewById(R.id.offerPercentsTextView)
        internal var image: ImageView = itemView.findViewById(R.id.offerImageMainContainer)


        init {
            oldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        }

    }

    override fun getItemCount() = offerList.size
}

