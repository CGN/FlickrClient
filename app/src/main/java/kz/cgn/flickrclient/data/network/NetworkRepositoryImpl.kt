package kz.cgn.flickrclient.data.network

import io.reactivex.Flowable
import kz.cgn.flickrclient.data.network.model.PhotoListData
import kz.cgn.flickrclient.domain.Params

class NetworkRepositoryImpl(private val api: FlickrApi) : INetworkRepository {

    override fun getPhotos(params: Params): Flowable<PhotoListData> {
        if (params.tag.isNullOrEmpty()) {
            return api.getRecent(page = params.page)
        } else {
            return api.getPhotosByTag(params.tag, page = params.page)
        }
    }
}