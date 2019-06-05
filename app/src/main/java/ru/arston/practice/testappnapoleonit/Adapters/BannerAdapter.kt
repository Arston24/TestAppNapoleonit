package ru.arston.practice.testappnapoleonit.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import ru.arston.practice.testappnapoleonit.Model.BannerModel
import ru.arston.practice.testappnapoleonit.R


class BannerAdapter(private val itemBanner: List<BannerModel>) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {


    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.banner_image)

        fun bind(itemBanner: BannerModel) {
            Picasso.get()
                    .load(itemBanner.image)
                    .fit()
                    .centerInside()
                    .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, id: Int): BannerViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_item, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: BannerViewHolder, id: Int) {
        viewHolder.bind(itemBanner[id])
    }

    override fun getItemCount() = itemBanner.size
}

