package kz.cgn.flickrclient.domain.interactor

import io.reactivex.Flowable
import kz.cgn.flickrclient.domain.Params
import kz.cgn.flickrclient.domain.IPhotoRepository
import kz.cgn.flickrclient.domain.PostExecutionThread
import kz.cgn.flickrclient.domain.interactor.base.FlowableUseCase
import kz.cgn.flickrclient.domain.model.Photo

class GetPhotosByTagUseCase(postExecutionThread: PostExecutionThread,
                            private val repository: IPhotoRepository) : FlowableUseCase<List<Photo>, Params>(postExecutionThread) {

    override fun buildUseCase(parameters: Params): Flowable<List<Photo>> {
        return repository.getPhotos(parameters)
    }
}