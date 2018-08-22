package kz.cgn.flickrclient.presentation.activity

import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.github.salomonbrys.kodein.instance
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_photo_list.*
import kz.cgn.flickrclient.R
import kz.cgn.flickrclient.domain.model.Photo
import kz.cgn.flickrclient.domain.model.SearchHistory
import kz.cgn.flickrclient.presentation.adapter.PhotoAdapter
import kz.cgn.flickrclient.presentation.extensions.showToast
import kz.cgn.flickrclient.presentation.presenter.PhotoListPresenter

class PhotoListActivity : RootActivity<PhotoListPresenter.View>(), PhotoListPresenter.View {

    override val layoutResourceId: Int = R.layout.activity_photo_list

    override val presenter: PhotoListPresenter by kodein.instance()

    private val photoAdapter = PhotoAdapter(mutableListOf())

    private lateinit var searchAdapter: ArrayAdapter<String>

    override fun initializeUI() {
        list.setHasFixedSize(true)
        list.layoutManager = GridLayoutManager(this, 3)
        list.adapter = photoAdapter

        list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val gridLayoutManager = list.layoutManager as GridLayoutManager
                val totalItemCount = gridLayoutManager.itemCount
                val firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition()

                if (!swipe_refresh.isRefreshing && totalItemCount - firstVisibleItem <= 21) {
                    val page = totalItemCount / 21
                    presenter.load(page = page)
                }
            }
        })

        photoAdapter.onClickCallback = object : PhotoAdapter.OnClickCallback {
            override fun onClick(v: View, photo: Photo) {
                showTestText(photo.title)
                val photoJson = Gson().toJson(photo)

                val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this@PhotoListActivity, v, getString(R.string.trans_list2detail))

                val intent = Intent(this@PhotoListActivity, PhotoDetailActivity::class.java)
                intent.putExtra("content", photoJson)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                ActivityCompat.startActivity(this@PhotoListActivity, intent, optionsCompat.toBundle())
            }
        }

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
                presenter.loadSearchHistory(newText?:"")
                return false
            }
        })

        val searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text) as SearchView.SearchAutoComplete
        searchAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, listOf())
        searchAutoComplete.setAdapter(searchAdapter)
        searchAutoComplete.setDropDownBackgroundResource(android.R.color.holo_blue_light)

        searchAutoComplete.onItemClickListener = object: AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val queryString = searchAdapter.getItem(position)
                searchView.setQuery(queryString, true)
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_menu_clear_search_history -> {
                presenter.clearSearchHistory()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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
        swipe_refresh.isRefreshing = true

    }

    override fun hideProgress() {
        swipe_refresh.isRefreshing = false
    }

    override fun updateSearchHistory(searchHistoryList: List<SearchHistory>) {
        searchAdapter.clear()
        searchAdapter.addAll(searchHistoryList.map { it.tag })
    }

    override fun navigateToDetail(photo: Photo) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

