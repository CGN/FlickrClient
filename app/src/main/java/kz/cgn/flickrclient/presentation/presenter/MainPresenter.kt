package kz.cgn.flickrclient.presentation.presenter

import kz.cgn.flickrclient.domain.Params
import kz.cgn.flickrclient.domain.interactor.GetPhotosByTageUseCase
import kz.cgn.flickrclient.domain.models.Photo

class MainPresenter(private val getPhotosByTageUseCase: GetPhotosByTageUseCase) : Presenter<MainPresenter.View>() {

    private var searchTag: String = ""

    override fun initialize() {
        load()
    }

    fun updateSearchTag(tag: String) {
        searchTag = tag
        view?.clearPhotoList()
        load()
    }

    fun load(page: Int = 0) {
        view?.showProgress()
        getPhotosByTageUseCase.execute({
            view?.addPhotoList(it)
            view?.hideProgress()
        }, {
            view?.showError(it.message!!)
            view?.hideProgress()
        }, params = Params(searchTag, page))
    }

    override fun pause() {
    }

    override fun stop() {
    }

    override fun destroy() {
        getPhotosByTageUseCase.dispose()
    }

    interface View : Presenter.View {
        fun showTestText(text: String)
        fun clearPhotoList()
        fun addPhotoList(photoList: List<Photo>)
        fun showProgress()
        fun hideProgress()
    }
}