package kz.cgn.flickrclient.presentation.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kz.cgn.flickrclient.R
import kz.cgn.flickrclient.domain.models.Photo

class PhotoAdapter(private val photoList: MutableList<Photo>) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val viewHolder =  ViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.row_image_list,
                parent,
                false
        ))
        return viewHolder
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageview.setTag(R.integer.list_pos_key, position)

        val photo = photoList.get(position)
        Picasso.get()
                .load(photo.url)
                .placeholder(R.drawable.loading_default)
                .error(R.drawable.ic_image_broken)
                .into(holder.imageview)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var imageview: ImageView

        init {
            imageview = v.findViewById(R.id.image) as ImageView
        }
    }

    fun clearItems() {
        photoList.clear()
        notifyDataSetChanged()
    }

    fun addItem(photo: Photo) {
        photoList.add(photo)
        notifyItemInserted(photoList.size - 1)
    }

    fun addItems(photoList: MutableList<Photo>) {
        val newPhotoList = ArrayList<Photo>()
        newPhotoList.addAll(this.photoList)
        newPhotoList.addAll(photoList)

        val diffResult =
                DiffUtil.calculateDiff(
                        PhotoDiffCallback(
                                this.photoList,
                                newPhotoList
                        )
                )
        this.photoList.addAll(photoList)
        diffResult.dispatchUpdatesTo(this)
    }

    class PhotoDiffCallback(
            private val oldList: List<Photo>,
            private val newList: List<Photo>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
            return oldList[p0].url == newList[p1].url
        }

        override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
            return oldList[p0].url == newList[p1].url
        }

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size
    }
}