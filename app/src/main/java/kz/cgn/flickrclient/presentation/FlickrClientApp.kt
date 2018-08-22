package kz.cgn.flickrclient.presentation

import android.app.Application
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.autoAndroidModule
import kz.cgn.flickrclient.data.DataRepository
import kz.cgn.flickrclient.data.network.END_POINT
import kz.cgn.flickrclient.data.network.NetworkDataRepository
import kz.cgn.flickrclient.data.network.NetworkRepository
import kz.cgn.flickrclient.data.network.createService
import kz.cgn.flickrclient.domain.PhotoRepository
import kz.cgn.flickrclient.domain.PostExecutionThread
import kz.cgn.flickrclient.domain.interactor.GetPhotosByTageUseCase
import kz.cgn.flickrclient.presentation.presenter.MainPresenter

class FlickrClientApp : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(autoAndroidModule(this@FlickrClientApp))

        bind<MainPresenter>() with provider { MainPresenter(instance()) }

        bind<PostExecutionThread>() with singleton { UIThread() }

        bind<GetPhotosByTageUseCase>() with singleton { GetPhotosByTageUseCase(instance(), instance()) }

        bind<PhotoRepository>() with singleton { DataRepository(instance()) }

        bind<NetworkRepository>() with singleton { NetworkDataRepository(createService(END_POINT)) }
    }
}