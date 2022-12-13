package a77777_888.me.t.ecommercesample.presentation.detailsfragment

import a77777_888.me.t.ecommercesample.databinding.DetailsImagesItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImagesDetailsAdapter(
    private val images: List<String>
) : RecyclerView.Adapter<ImagesDetailsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DetailsImagesItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = images[position]
        with(holder.binding.imageView) {
            Glide.with(this)
                .load(image)
                .centerCrop()
                .into(this)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewHolder(val binding: DetailsImagesItemBinding): RecyclerView.ViewHolder(binding.root)
}