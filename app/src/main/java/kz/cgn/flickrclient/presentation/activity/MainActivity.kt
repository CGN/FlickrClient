package kz.cgn.flickrclient.presentation.activity

import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import com.github.salomonbrys.kodein.instance
import kotlinx.android.synthetic.main.activity_main.*
import kz.cgn.flickrclient.R
import kz.cgn.flickrclient.domain.models.Photo
import kz.cgn.flickrclient.presentation.adapter.PhotoAdapter
import kz.cgn.flickrclient.presentation.extensions.showToast
import kz.cgn.flickrclient.presentation.presenter.MainPresenter

class MainActivity : RootActivity<MainPresenter.View>(), MainPresenter.View {

    override val layoutResourceId: Int = R.layout.activity_main

    override val presenter: MainPresenter by kodein.instance()

    val photoAdapter = PhotoAdapter(mutableListOf<Photo>())

    override fun initializeUI() {
        list.setHasFixedSize(true)
        list.layoutManager = GridLayoutManager(this, 3)
        list.adapter = photoAdapter

        list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val gridLayoutManager = list.layoutManager as GridLayoutManager
                val visibleItemCount = gridLayoutManager.childCount
                val totalItemCount = gridLayoutManager.itemCount
                val firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition()

                if (progress.visibility == View.GONE && totalItemCount - firstVisibleItem <= 21) {
                    val page = totalItemCount / 21
                    presenter.load(page = page)
                }
            }
        })

        swipe_refresh.setOnRefreshListener { presenter.load() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_search_example_menu, menu)
        val searchMenu = menu?.findItem(R.id.app_bar_menu_search)
        val searchView = MenuItemCompat.getActionView(searchMenu) as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.updateSearchTag(query?:"")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun initializePresenter() {
        presenter.start(this)
    }

    override fun showError(messageId: Int) {
        showToast(messageId)
    }

    override fun showError(message: String) {
        showToast(message)
    }

    override fun showTestText(text: String) {
        showToast(text)
    }

    override fun clearPhotoList() {
        photoAdapter.clearItems()
    }

    override fun addPhotoList(photoList: List<Photo>) {
        photoAdapter.addItems(photoList as MutableList<Photo>)
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
        swipe_refresh.isRefreshing = true

    }

    override fun hideProgress() {
        progress.visibility = View.GONE
        swipe_refresh.isRefreshing = false
    }
}

