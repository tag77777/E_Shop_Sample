package a77777_888.me.t.ecommercesample.presentation.explorerfragment.adapters

import a77777_888.me.t.domain.model.ProductItem
import a77777_888.me.t.domain.repositories.IFavoritesRepository
import a77777_888.me.t.domain.usecases.FavoritesInterActor
import a77777_888.me.t.ecommercesample.R
import a77777_888.me.t.ecommercesample.databinding.BestsellerItemBinding
import a77777_888.me.t.ecommercesample.presentation.model.phone.UiBestSellerItem
import a77777_888.me.t.ecommercesample.presentation.phonedetailsfragment.PhoneDetailsFragment
import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BestSellerAdapter(
    private val eventListener: EventListener,
    items: List<UiBestSellerItem>,
    iFavoritesRepository: IFavoritesRepository
) : RecyclerView.Adapter<BestSellerAdapter.Holder>(){

    private val favoritesInterActor = FavoritesInterActor(iFavoritesRepository)

    var bestSellerList = items
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BestsellerItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = UiBestSellerItem(bestSellerList[position])
        val favoritesItem = ProductItem(item)

        item.run {
            with(holder.binding) {
                titleTextView.text = title
                discountPriceTextView.text = discountPrice.toString()
                priceTextView.setStrikeText(priceWithoutDiscount.toString())

                if (favoritesInterActor.contains(favoritesItem))
                    favoriteImageButton.setImageResource(R.drawable.ic_favorite_on)
                else favoriteImageButton.setImageResource(R.drawable.ic_favorite_off)

                Glide.with(pictureImageView)
                    .load(picture)
                    .placeholder(R.drawable.ic_phone)
                    .centerCrop()
                    .into(pictureImageView)

                favoriteImageButton.setOnClickListener {
                    if (favoritesInterActor.contains(favoritesItem)) {
                        favoriteImageButton.setImageResource(R.drawable.ic_favorite_off)
                        favoritesInterActor.removeItem(favoritesItem)
                    } else {
                        favoriteImageButton.setImageResource(R.drawable.ic_favorite_on)
                        favoritesInterActor.addItem(favoritesItem)
                    }
                    eventListener.onFavoritesChanged()
                }

                root.setOnClickListener {
                    val bundle = bundleOf(
                        PhoneDetailsFragment.ID to item.id.toString(),
                        PhoneDetailsFragment.TITLE to item.title,
                        PhoneDetailsFragment.PICTURE to item.picture,
                        PhoneDetailsFragment.PRICE to item.discountPrice
                    )
                    eventListener.onBestSellerItemClick(bundle)
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return bestSellerList.size
    }

    class Holder(val binding: BestsellerItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface EventListener {
        fun onBestSellerItemClick(args: Bundle)
        fun onFavoritesChanged()
    }
}

fun TextView.setStrikeText(newText: String) {
        text = newText
        paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }