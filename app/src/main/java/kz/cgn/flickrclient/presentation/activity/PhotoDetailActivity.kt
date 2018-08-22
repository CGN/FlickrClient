package kz.cgn.flickrclient.presentation.activity

import com.github.salomonbrys.kodein.instance
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_photo_detail.*
import kz.cgn.flickrclient.R
import kz.cgn.flickrclient.domain.model.Photo
import kz.cgn.flickrclient.presentation.extensions.showToast
import kz.cgn.flickrclient.presentation.presenter.PhotoDetailPresenter

class PhotoDetailActivity: RootActivity<PhotoDetailPresenter.View>(), PhotoDetailPresenter.View  {

    override val layoutResourceId: Int = R.layout.activity_photo_detail


    override val presenter: PhotoDetailPresenter by kodein.instance()


    override fun initializeUI() {
        val extras = intent.extras
        if (extras != null) {
            val photoJson = extras.getString("content")
            val photo = Gson().fromJson(photoJson, Photo::class.java)
            image_detail_title.text = photo.title
            Picasso.get()
                    .load(photo.url)
                    .placeholder(R.drawable.loading_default)
                    .error(R.drawable.ic_image_broken)
                    .into(image_detail)
        }
    }

    override fun initializePresenter() {
        presenter.start(this)
    }

    override fun showError(messageId: Int) {
        showToast(messageId)
    }

    override fun showError(message: String) {
        showToast(message)
    }
}