package kz.cgn.flickrclient.presentation.presenter

import kz.cgn.flickrclient.domain.Params
import kz.cgn.flickrclient.domain.interactor.ClearSearchHistoryUserCase
import kz.cgn.flickrclient.domain.interactor.GetPhotosByTagUseCase
import kz.cgn.flickrclient.domain.interactor.GetSearchHistoryByPrefixUseCase
import kz.cgn.flickrclient.domain.interactor.UpdateSearchHistoryUseCase
import kz.cgn.flickrclient.domain.model.Photo
import kz.cgn.flickrclient.domain.model.SearchHistory

class PhotoListPresenter(private val getPhotosByTagUseCase: GetPhotosByTagUseCase,
                         private val getSearchHistoryByPrefixUseCase: GetSearchHistoryByPrefixUseCase,
                         private val updateSearchHistoryUseCase: UpdateSearchHistoryUseCase,
                         private val clearSearchHistoryUserCase: ClearSearchHistoryUserCase) : Presenter<PhotoListPresenter.View>() {

    private var searchTag: String = ""

    override fun initialize() {
        load()
    }

    fun updateSearchTag(tag: String) {
        searchTag = tag
        updateSearchHistoryUseCase.execute({}, { view?.showError(it.message!!) }, tag)
        view?.clearPhotoList()
        load()
    }

    fun load(page: Int = 0) {
        view?.showProgress()
        getPhotosByTagUseCase.execute({
            view?.addPhotoList(it)
            view?.hideProgress()
        }, {
            view?.showError(it.message!!)
            view?.hideProgress()
        }, params = Params(searchTag, page))
    }

    fun loadSearchHistory(tag: String) {
        getSearchHistoryByPrefixUseCase.execute({
            view?.updateSearchHistory(it)
        }, {
            view?.showError(it.message!!)
        }, tag)
    }

    fun clearSearchHistory() {
        clearSearchHistoryUserCase.execute({
            view?.updateSearchHistory(emptyList())
        }, {
            view?.showError(it.message!!)
        }, Unit)
    }

    override fun pause() {
    }

    override fun stop() {
    }

    override fun destroy() {
        getPhotosByTagUseCase.dispose()
        getSearchHistoryByPrefixUseCase.dispose()
        updateSearchHistoryUseCase.dispose()
        clearSearchHistoryUserCase.dispose()
    }

    interface View : Presenter.View {
        fun showTestText(text: String)
        fun clearPhotoList()
        fun addPhotoList(photoList: List<Photo>)
        fun showProgress()
        fun hideProgress()
        fun updateSearchHistory(searchHistoryList: List<SearchHistory>)
        fun navigateToDetail(photo: Photo)
    }
}