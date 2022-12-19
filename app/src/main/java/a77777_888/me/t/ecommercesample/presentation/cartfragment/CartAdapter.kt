package a77777_888.me.t.ecommercesample.presentation.cartfragment

import a77777_888.me.t.domain.repositories.ICartRepository
import a77777_888.me.t.domain.usecases.CartInterActor
import a77777_888.me.t.ecommercesample.databinding.CartItemBinding
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CartAdapter(
    iCartRepository: ICartRepository,
    private val emptyListListener: EmptyListListener
) : RecyclerView.Adapter<CartAdapter.Holder>(){

    private val cartInterActor = CartInterActor(iCartRepository)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CartItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = cartInterActor.getItem(position)

        with(holder.binding) {
            titleTextView.text = item.product.title
            priceTextView.text = item.product.price.toString()
            numberTextView.text = item.number.toString()
            removeBtn.tag = item

            plusBtn.setOnClickListener {
                val cartItem = cartInterActor.getItem(holder.adapterPosition)
                cartItem.number++
                numberTextView.text = cartItem.number.toString()
            }

            minusBtn.setOnClickListener {
                if (item.number > 1) {
                    val cartItem = cartInterActor.getItem(holder.adapterPosition)
                    cartItem.number--
                    numberTextView.text = cartItem.number.toString()
                }
            }

            removeBtn.setOnClickListener {
                val removablePosition = holder.adapterPosition
                val removableItem = cartInterActor.getItem(removablePosition)
                cartInterActor.removeCartItem(removableItem)
                if (cartInterActor.size() == 0) {
                    emptyListListener.onEmptyCartList()
                    return@setOnClickListener
                }
                notifyItemRemoved(removablePosition )
                notifyItemRangeChanged(removablePosition , itemCount - removablePosition)
            }

            Glide.with(imageView)
                .load(item.product.image)
                .centerCrop()
                .into(imageView)
        }

    }

    override fun getItemCount(): Int {
        return cartInterActor.size()
    }

    interface EmptyListListener {
        fun onEmptyCartList()
    }

    class Holder(val binding:CartItemBinding) : RecyclerView.ViewHolder(binding.root)

}