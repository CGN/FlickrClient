package kz.cgn.flickrclient.data.network.model

data class PhotoListData(
        val stat: String,
        val photos: Photos
)

data class Photos (
        val page: Int,
        val patges: Int,
        val perpage: Int,
        val total: Int,
        val photo: List<Photo>
)

data class Photo (
        val id: String,
        val owner: String,
        val secret: String,
        val server: String,
        val farm: Int,
        val title: String,
        val ispublic: Int,
        val isfriend: Int,
        val isfamilty: Int
)

fun Photo.generatePhotoUrl(size: String = ""): String {
    val sizePrefix = if (size.isEmpty())  "" else  "_"
    return "https://farm${farm}.staticflickr.com/${server}/${id}_${secret}${sizePrefix}${size}.jpg"
}