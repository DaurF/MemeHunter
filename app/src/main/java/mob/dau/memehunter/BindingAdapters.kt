package mob.dau.memehunter

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import mob.dau.memehunter.network.MemeItem
import mob.dau.memehunter.ui.MemesGridAdapter
import mob.dau.memehunter.ui.Status

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
        imageView.load(imageUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MemeItem>?) {
    val adapter = recyclerView.adapter as MemesGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("statusImage")
fun bindStatusImage(imageView: ImageView, statusApi: Status?) {
    when (statusApi) {
        Status.SUCCESS -> {
            imageView.visibility = View.GONE
        }
        Status.FAILURE -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_connection_error)
        }
        Status.LOADING -> {
            imageView.visibility = View.INVISIBLE
            imageView.setImageResource(R.drawable.loading_animation)
        }
    }
}