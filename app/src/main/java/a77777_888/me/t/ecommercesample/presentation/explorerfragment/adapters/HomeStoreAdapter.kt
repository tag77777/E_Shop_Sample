package a77777_888.me.t.ecommercesample.presentation.explorerfragment.adapters

import a77777_888.me.t.domain.model.IHomeStoreItem
import a77777_888.me.t.ecommercesample.databinding.HomeStoreItemBinding
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HomeStoreAdapter(
    private val listener: HomeStoreBuyButtonOnClickListener,
    private val items: List<IHomeStoreItem>
) : RecyclerView.Adapter<HomeStoreAdapter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeStoreItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]
        item.run {
            with(holder.binding) {
                item.isNew?.let { newTextView.isVisible = it }
                newTextView.isVisible = item.isNew ?: false
                titleTextView.setUnderlinedText(title)
                subtitleTextView.setUnderlinedText(subtitle)
                buyNowButton.setOnClickListener { listener.onHomeStoreBuyButtonClick(id) }

                Glide.with(pictureImageView)
                    .load(picture)
                    .centerCrop()
                    .into(pictureImageView)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class Holder(val binding: HomeStoreItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface HomeStoreBuyButtonOnClickListener {
        fun onHomeStoreBuyButtonClick(id: Int)
    }
}

fun TextView.setUnderlinedText(newText: String) {
        text = newText
        paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }
