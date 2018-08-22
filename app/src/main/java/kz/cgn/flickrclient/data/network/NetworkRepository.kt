package kz.cgn.flickrclient.data.network

import io.reactivex.Flowable
import kz.cgn.flickrclient.data.network.models.PhotoListData
import kz.cgn.flickrclient.domain.Params

interface NetworkRepository {

    fun getPhotos(params: Params): Flowable<PhotoListData>
}