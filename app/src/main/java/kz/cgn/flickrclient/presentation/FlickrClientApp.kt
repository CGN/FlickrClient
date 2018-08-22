package kz.cgn.flickrclient.presentation

import android.app.Application
import android.arch.persistence.room.Room
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.autoAndroidModule
import kz.cgn.flickrclient.data.PhotoRepositoryImpl
import kz.cgn.flickrclient.data.SearchHistoryRepositoryImpl
import kz.cgn.flickrclient.data.network.END_POINT
import kz.cgn.flickrclient.data.network.NetworkRepositoryImpl
import kz.cgn.flickrclient.data.network.INetworkRepository
import kz.cgn.flickrclient.data.network.createService
import kz.cgn.flickrclient.data.persistence.AppDatabase
import kz.cgn.flickrclient.data.persistence.IPersistenceRepository
import kz.cgn.flickrclient.data.persistence.PersistenceRepositoryImpl
import kz.cgn.flickrclient.domain.IPhotoRepository
import kz.cgn.flickrclient.domain.ISearchHistoryRepository
import kz.cgn.flickrclient.domain.PostExecutionThread
import kz.cgn.flickrclient.domain.interactor.ClearSearchHistoryUserCase
import kz.cgn.flickrclient.domain.interactor.GetPhotosByTagUseCase
import kz.cgn.flickrclient.domain.interactor.GetSearchHistoryByPrefixUseCase
import kz.cgn.flickrclient.domain.interactor.UpdateSearchHistoryUseCase
import kz.cgn.flickrclient.presentation.presenter.PhotoDetailPresenter
import kz.cgn.flickrclient.presentation.presenter.PhotoListPresenter

class FlickrClientApp : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(autoAndroidModule(this@FlickrClientApp))

        bind<PhotoListPresenter>() with provider { PhotoListPresenter(instance(), instance(), instance(), instance()) }

        bind<PhotoDetailPresenter>() with provider { PhotoDetailPresenter() }

        bind<PostExecutionThread>() with singleton { UIThread() }

        bind<GetPhotosByTagUseCase>() with singleton { GetPhotosByTagUseCase(instance(), instance()) }

        bind<GetSearchHistoryByPrefixUseCase>() with singleton { GetSearchHistoryByPrefixUseCase(instance(), instance()) }

        bind<UpdateSearchHistoryUseCase>() with singleton { UpdateSearchHistoryUseCase(instance(), instance()) }

        bind<ClearSearchHistoryUserCase>() with singleton { ClearSearchHistoryUserCase(instance(), instance()) }

        bind<IPhotoRepository>() with singleton { PhotoRepositoryImpl(instance()) }

        bind<INetworkRepository>() with singleton { NetworkRepositoryImpl(createService(END_POINT)) }

        bind<AppDatabase>() with eagerSingleton {
            Room.databaseBuilder(this@FlickrClientApp, AppDatabase::class.java, "flickr-client-db")
                    .allowMainThreadQueries()
                    .build()
        }

        bind<ISearchHistoryRepository>() with singleton { SearchHistoryRepositoryImpl(instance()) }

        bind<IPersistenceRepository>() with singleton { PersistenceRepositoryImpl(instance()) }
    }
}