package kz.cgn.flickrclient.data

import io.reactivex.Flowable
import kz.cgn.flickrclient.data.network.NetworkRepository
import kz.cgn.flickrclient.data.network.models.generatePhotoUrl
import kz.cgn.flickrclient.domain.Params
import kz.cgn.flickrclient.domain.PhotoRepository
import kz.cgn.flickrclient.domain.models.Photo

class DataRepository(private val networkRepository: NetworkRepository) : PhotoRepository {

    override fun getPhotos(params: Params): Flowable<List<Photo>> {
        return networkRepository.getPhotos(params)
                .map { it.photos.photo }
                .map { it.map { Photo(it.title, it.generatePhotoUrl()) }}
    }
}