package kz.cgn.flickrclient.data.network

import io.reactivex.Flowable
import kz.cgn.flickrclient.data.network.model.PhotoListData
import kz.cgn.flickrclient.domain.Params

interface INetworkRepository {

    fun getPhotos(params: Params): Flowable<PhotoListData>
}