package kz.cgn.flickrclient.data.network

const val END_POINT = "https://api.flickr.com/services/"
const val METHOD_PREFIX = "rest/?method="
const val APIKEY_SEARCH_STRING = "&api_key=f10fd45da525ee96bdab03ddc6ffbd07"
const val FORMAT_JSON = "&format=json"
const val JSON_CALLBACK = "&nojsoncallback=1"
const val PER_PAGE = "&per_page="
const val PAGE = "&page="
const val POPULAR = "flickr.interestingness.getList"
const val PHOTO_INFO = "flickr.photos.getInfo"
const val PEOPLE_INFO = "flickr.people.getInfo"
const val PUBLIC_PHOTO = "flickr.people.getPublicPhotos"
const val PEOPLE_FAVORITE = "flickr.favorites.getPublicList"
const val COMMENT_LIST = "flickr.photos.comments.getList"
const val HOT_TAG_LIST = "flickr.tags.getHotList"
const val PHOTO_SEARCH = "flickr.photos.search"