package a77777_888.me.t.ecommercesample.presentation.cartfragment

import a77777_888.me.t.domain.repositories.ICartRepository
import a77777_888.me.t.domain.usecases.CartInterActor
import a77777_888.me.t.ecommercesample.R
import a77777_888.me.t.ecommercesample.databinding.FragmentCartBinding
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart),
    CartAdapter.EmptyListListener {

    @Inject lateinit var iCartRepository: ICartRepository
    private lateinit var cartInterActor: CartInterActor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCartBinding.bind(view)
        val adapter = CartAdapter(iCartRepository, this)
        cartInterActor = CartInterActor(iCartRepository)

        with(binding) {
            totalTextView.text = cartInterActor.getTotalPrice().toString()
            cartRecyclerView.adapter = adapter

            cancelButton.setOnClickListener {
                exit()
            }

            checkoutButton.setOnClickListener {
                totalTextView.text = cartInterActor.getTotalPrice().toString()
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()

        val bundle = bundleOf(NUMBER to cartInterActor.size())
        parentFragmentManager.setFragmentResult(CART, bundle)
    }

    override fun onEmptyCartList() {
        exit()
    }

    private fun exit() {
        findNavController().navigateUp()
    }

    companion object {
        const val CART = "cart_fragment"
        const val NUMBER = "cart_number"
    }
}