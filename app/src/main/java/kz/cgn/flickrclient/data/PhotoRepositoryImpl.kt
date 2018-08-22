package kz.cgn.flickrclient.data

import io.reactivex.Flowable
import kz.cgn.flickrclient.data.network.INetworkRepository
import kz.cgn.flickrclient.data.network.model.generatePhotoUrl
import kz.cgn.flickrclient.domain.Params
import kz.cgn.flickrclient.domain.IPhotoRepository
import kz.cgn.flickrclient.domain.model.Photo

class PhotoRepositoryImpl(private val networkRepository: INetworkRepository) : IPhotoRepository {

    override fun getPhotos(params: Params): Flowable<List<Photo>> {
        return networkRepository.getPhotos(params)
                .map { it.photos.photo }
                .map { it.map { Photo(it.title, it.generatePhotoUrl()) }}
    }
}