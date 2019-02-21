package ru.arston.practice.testappnapoleonit

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**Needs for paginate loading*/
class BannersScrollListener(val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val lastVisibleElementPos = layoutManager.findLastVisibleItemPosition()
//        val size = banners.size
//        val isLoading = App.getRequest().isBannersLoading
//
//        if (!isLoading && lastVisibleElementPos == size - 1) {
//            App.getRequest().loadBanners()
//        }
    }
}