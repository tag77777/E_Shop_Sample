package a77777_888.me.t.ecommercesample.presentation

import a77777_888.me.t.ecommercesample.R
import a77777_888.me.t.ecommercesample.databinding.FragmentFilterBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterDialogFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentFilterBinding

    override fun getTheme(): Int  = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isCancelable = false

        with(binding) {
            closeButton.setOnClickListener { dismiss() }

            doneButton.setOnClickListener {
                val bundle = bundleOf(
                    BRAND to brandSpinner.selectedItem as String,
                    PRICE to priceSpinner.selectedItem as String,
                    SIZE to sizeSpinner.selectedItem as String
                )
                parentFragmentManager.setFragmentResult(FILTER_DIALOG, bundle)
                dismiss()
            }
        }
    }

    companion object {
        const val FILTER_DIALOG = "filter dialog"
        const val BRAND = "brand"
        const val PRICE = "price"
        const val SIZE = "size"
    }
}