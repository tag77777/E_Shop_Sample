package a77777_888.me.t.ecommercesample.presentation.cartfragment

import a77777_888.me.t.domain.repositories.ICartRepository
import a77777_888.me.t.domain.usecases.CartInterActor
import a77777_888.me.t.ecommercesample.R
import a77777_888.me.t.ecommercesample.databinding.FragmentCartBinding
import android.os.Bundle
import android.view.View
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
//            totalTextView.append(cartInterActor.getTotalPrice().toString())
            totalTextView.text = String.format(
                getString(R.string.dollar),
                cartInterActor.getTotalPrice()
            )
            cartRecyclerView.adapter = adapter

            cancelButton.setOnClickListener {
                exit()
            }

            checkoutButton.setOnClickListener {
                totalTextView.text = String.format(
                    getString(R.string.dollar),
                    cartInterActor.getTotalPrice()
                )
            }
        }


    }

    override fun onStop() {
        super.onStop()

        parentFragmentManager.setFragmentResult(CART, Bundle())
    }

    override fun onEmptyCartList() {
        exit()
    }

    private fun exit() {
        findNavController().navigateUp()
    }

    companion object {
        const val CART = "cart_fragment"
    }
}