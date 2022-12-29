package a77777_888.me.t.ecommercesample.presentation.explorerfragment.adapters

import a77777_888.me.t.ecommercesample.R
import a77777_888.me.t.ecommercesample.databinding.CategoriesItemBinding
import a77777_888.me.t.ecommercesample.presentation.model.listCategory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CategoriesAdapter(
    private val listener: CategoryListener
) : RecyclerView.Adapter<CategoriesAdapter.Holder>(){

    private var selectedPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoriesItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    @SuppressWarnings("deprecation")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val category = listCategory[position]
        with(holder.binding.titleTextView) {
            setText(category.tile)
            isSelected = holder.adapterPosition == selectedPosition
        }

        with(holder.binding.iconImageButton) {
            setImageResource(category.icon)
            setBackgroundResource(
                if (position == selectedPosition) R.drawable.category_ic_bg_selected
                else 0
            )
            isSelected =  holder.adapterPosition == selectedPosition
            setOnClickListener {
                selectPosition(holder.adapterPosition)
                listener.onCategorySelect(holder.adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }

    fun selectPosition(position: Int) {
        val oldSelectedPosition = selectedPosition
        selectedPosition = position
        notifyItemChanged(oldSelectedPosition)
        notifyItemChanged(selectedPosition)
    }

    class Holder(val binding: CategoriesItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface CategoryListener {
        fun onCategorySelect(position: Int)
    }
}