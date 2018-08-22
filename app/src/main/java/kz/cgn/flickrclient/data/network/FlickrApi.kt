package kz.cgn.flickrclient.data.network

import io.reactivex.Flowable
import kz.cgn.flickrclient.data.network.models.PhotoListData
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

    @GET("${METHOD_PREFIX}flickr.photos.search$FORMAT_JSON$JSON_CALLBACK$APIKEY_SEARCH_STRING")
    fun getPhotosByTag(@Query("tags") tags: String, @Query("page") page: Int = 1, @Query("per_page") perPage: Int = 20): Flowable<PhotoListData>

    @GET("${METHOD_PREFIX}flickr.photos.getRecent$FORMAT_JSON$JSON_CALLBACK$APIKEY_SEARCH_STRING")
    fun getRecent(@Query("page") page: Int = 0, @Query("per_page") perPage: Int = 21): Flowable<PhotoListData>
}