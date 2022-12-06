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
    iCartRepository: ICartRepository
) : RecyclerView.Adapter<CartAdapter.Holder>(){

    private val cartInterActor = CartInterActor(iCartRepository)
    private val items = cartInterActor.getList().toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CartItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]

        item.run {
            with(holder.binding) {
                titleTextView.text = product.title
                priceTextView.text = product.price.toString()
                numberTextView.text = number.toString()

                plusBtn.setOnClickListener {
                    numberTextView.text = (++number).toString()
                }

                minusBtn.setOnClickListener {
                    if (number < 2) cartInterActor.removeCartItem(item)
                    else numberTextView.text = (--number).toString()
                }

                removeBtn.setOnClickListener {
                    cartInterActor.removeCartItem(item)
                    items.remove(item)
                    notifyDataSetChanged()
                }

                Glide.with(imageView)
                    .load(product.image)
                    .centerCrop()
                    .into(imageView)
            }
        }
    }

    override fun getItemCount(): Int {
        return cartInterActor.size()
    }

    class Holder(val binding:CartItemBinding) : RecyclerView.ViewHolder(binding.root)
}