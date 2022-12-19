package a77777_888.me.t.ecommercesample.presentation

import a77777_888.me.t.ecommercesample.R
import a77777_888.me.t.ecommercesample.databinding.FragmentSearchBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchDialogFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentSearchBinding

    override fun getTheme(): Int  = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isCancelable = false

        with(binding) {
            closeButton.setOnClickListener { dismiss() }

            searchButton.setOnClickListener {
                val bundle = bundleOf(SEARCH_STRING to searchText.text.toString())
                parentFragmentManager.setFragmentResult(SEARCH_DIALOG, bundle)
                dismiss()
            }
        }
    }

    companion object {
        const val SEARCH_DIALOG = "search_dialog"
        const val SEARCH_STRING = "search_string"
    }

}