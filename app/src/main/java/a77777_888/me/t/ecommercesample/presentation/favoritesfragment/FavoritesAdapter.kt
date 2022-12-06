package a77777_888.me.t.ecommercesample.presentation.favoritesfragment

import a77777_888.me.t.domain.repositories.IFavoritesRepository
import a77777_888.me.t.domain.usecases.FavoritesInterActor
import a77777_888.me.t.ecommercesample.R
import a77777_888.me.t.ecommercesample.databinding.FavoritesItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FavoritesAdapter(
    iFavoritesRepository: IFavoritesRepository
) : RecyclerView.Adapter<FavoritesAdapter.Holder>(){

    private val favoritesInterActor = FavoritesInterActor(iFavoritesRepository)
    private val items = favoritesInterActor.getList().toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FavoritesItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]

        item.run {
            with(holder.binding) {
                titleTextView.text = title
                priceTextView.text = price.toString()

                favoriteSelectButton.setOnClickListener {
                    if (favoritesInterActor.contains(item)) {
                        favoritesInterActor.removeItem(item)
                        this.favoriteSelectButton.setImageResource(R.drawable.ic_favorite_off)
                        items.remove(item)
                    } else {
                        favoritesInterActor.addItem(item)
                        this.favoriteSelectButton.setImageResource(R.drawable.ic_favorite_on)
                        items.add(item)
                    }
                }

                Glide.with(imageView)
                    .load(image)
                    .centerCrop()
                    .into(imageView)
            }
        }
    }

    override fun getItemCount(): Int {
        return favoritesInterActor.size()
    }

    class Holder(val binding: FavoritesItemBinding) : RecyclerView.ViewHolder(binding.root)
}