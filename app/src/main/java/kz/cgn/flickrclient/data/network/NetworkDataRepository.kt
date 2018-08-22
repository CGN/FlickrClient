package kz.cgn.flickrclient.data.network

import io.reactivex.Flowable
import kz.cgn.flickrclient.data.network.models.PhotoListData
import kz.cgn.flickrclient.domain.Params

class NetworkDataRepository(private val api: FlickrApi) : NetworkRepository {

    override fun getPhotos(params: Params): Flowable<PhotoListData> {
        if (params.tag.isNullOrEmpty()) {
            return api.getRecent(page = params.page)
        } else {
            return api.getPhotosByTag(params.tag, page = params.page)
        }
    }
}