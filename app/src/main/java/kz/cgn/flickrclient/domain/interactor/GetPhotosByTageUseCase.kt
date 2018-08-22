package kz.cgn.flickrclient.domain.interactor

import io.reactivex.Flowable
import kz.cgn.flickrclient.domain.Params
import kz.cgn.flickrclient.domain.PhotoRepository
import kz.cgn.flickrclient.domain.PostExecutionThread
import kz.cgn.flickrclient.domain.models.Photo

class GetPhotosByTageUseCase(postExecutionThread: PostExecutionThread,
                             private val repository: PhotoRepository) : FlowableUseCase<List<Photo>, Params>(postExecutionThread) {

    override fun buildUseCase(parameters: Params): Flowable<List<Photo>> {
        return repository.getPhotos(parameters)
    }
}