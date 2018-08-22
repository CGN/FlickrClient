package kz.cgn.flickrclient.presentation.activity

import kz.cgn.flickrclient.presentation.presenter.PhotoDetailPresenter
import kz.cgn.flickrclient.presentation.presenter.Presenter

class PhotoDetailActivity: RootActivity<PhotoDetailPresenter.View>(), PhotoDetailPresenter.View  {

    override val layoutResourceId: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.


    override val presenter: Presenter<PhotoDetailPresenter.View>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun initializeUI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initializePresenter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(messageId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}