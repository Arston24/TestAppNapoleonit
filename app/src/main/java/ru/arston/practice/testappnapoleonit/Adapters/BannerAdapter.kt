package ru.arston.practice.testappnapoleonit.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import ru.arston.practice.testappnapoleonit.Model.BannerModel
import ru.arston.practice.testappnapoleonit.R


class BannerAdapter(private val itemBanner: List<BannerModel>) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {


    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.banner_image)
        var title: TextView = itemView.findViewById(R.id.titleBanner)
        var desc: TextView = itemView.findViewById(R.id.descBanner)
        val titleLabel: View = itemView.findViewById(R.id.titleMode)

        fun bind(itemBanner: BannerModel) {
            if (itemBanner.title != null) {
                titleLabel.visibility = View.VISIBLE
                title.text = itemBanner.title
            }
            if (itemBanner.desc!= null) {
                titleLabel.visibility = View.VISIBLE
                desc.text = itemBanner.desc
            }
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

