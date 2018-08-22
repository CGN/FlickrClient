package kz.cgn.flickrclient.domain

import io.reactivex.Flowable
import kz.cgn.flickrclient.domain.model.Photo

interface IPhotoRepository {
    fun getPhotos(params: Params): Flowable<List<Photo>>
}

data class Params(val tag: String = "", val page: Int = 0)