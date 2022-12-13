package a77777_888.me.t.ecommercesample.presentation.favoritesfragment

import a77777_888.me.t.domain.repositories.IFavoritesRepository
import a77777_888.me.t.domain.usecases.FavoritesInterActor
import a77777_888.me.t.ecommercesample.databinding.FavoritesItemBinding
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FavoritesAdapter(
    iFavoritesRepository: IFavoritesRepository,
    private val emptyListListener: EmptyListListener
) : RecyclerView.Adapter<FavoritesAdapter.Holder>(){

    private val favoritesInterActor = FavoritesInterActor(iFavoritesRepository)
    private var items = favoritesInterActor.getList().toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FavoritesItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]

        item.run {
            with(holder.binding) {
                titleTextView.text = title
                priceTextView.text = price.toString()

                removeBtn.setOnClickListener {
                    val actualPosition = holder.adapterPosition
                    val removedItem = items[actualPosition]
                    favoritesInterActor.removeItem(removedItem)
                    items.removeAt(actualPosition)
                    if (items.isEmpty()) emptyListListener.onEmptyFavoritesList()

                    notifyItemRemoved(actualPosition)
                    notifyItemRangeChanged(actualPosition, items.size)
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

    interface EmptyListListener {
        fun onEmptyFavoritesList()
    }

    class Holder(val binding: FavoritesItemBinding) : RecyclerView.ViewHolder(binding.root)
}