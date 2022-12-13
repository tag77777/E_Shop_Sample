package a77777_888.me.t.ecommercesample.presentation.detailsfragment

import a77777_888.me.t.domain.model.*
import a77777_888.me.t.domain.repositories.ICartRepository
import a77777_888.me.t.domain.repositories.IFavoritesRepository
import a77777_888.me.t.domain.usecases.CartInterActor
import a77777_888.me.t.domain.usecases.FavoritesInterActor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import a77777_888.me.t.ecommercesample.R
import a77777_888.me.t.ecommercesample.TAG
import a77777_888.me.t.ecommercesample.databinding.FragmentDetailsBinding
import a77777_888.me.t.ecommercesample.presentation.model.product.UiDetails
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    @Inject lateinit var iFavoritesRepository: IFavoritesRepository
    @Inject lateinit var iCartRepository: ICartRepository

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel by viewModels<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailsBinding.bind(view)

        loadResultObserve()
        viewModel.getData()
    }

    private fun loadResultObserve() =
      lifecycleScope.launch {
         viewModel.loadResultFlow.collect {
            with(binding) {
               when (it) {
                  is EmptyLoadResult -> {
                     baseLayout.visibility = INVISIBLE
                     loadStateView.progressBar.visibility = INVISIBLE
                     loadStateView.messageTextView.visibility = INVISIBLE
                     loadStateView.tryAgain.visibility = INVISIBLE
                  }
                  is PendingLoadResult -> {
                     baseLayout.visibility = INVISIBLE
                     loadStateView.progressBar.visibility = VISIBLE
                     loadStateView.messageTextView.visibility = VISIBLE
                     loadStateView.tryAgain.visibility = INVISIBLE
                  }
                  is SuccessLoadResult -> {
                     baseLayout.visibility = VISIBLE
                     loadStateView.progressBar.visibility = INVISIBLE
                     loadStateView.messageTextView.visibility = INVISIBLE
                     loadStateView.tryAgain.visibility = INVISIBLE

                     initUI(mock(UiDetails(it.value as IProductDetails)))
                  }
                  is ErrorLoadResult -> {
                     baseLayout.visibility = INVISIBLE
                     loadStateView.progressBar.visibility = INVISIBLE
                     loadStateView.messageTextView.visibility = VISIBLE
                     loadStateView.tryAgain.visibility = VISIBLE

                     loadStateView.messageTextView.setText(R.string.error_message)
                     Log.i(TAG, "ExplorerFragment.loadResultObserve: ", it.exception)
                     loadStateView.tryAgain.setOnClickListener { viewModel.getData() }
                  }
               }
            }
         }
      }

    private fun initUI(phoneUIDetails: UiDetails) {
            with(binding) {
                imagesCarousel.apply {
                    adapter = ImagesDetailsAdapter(phoneUIDetails.images)
                    setInfinite(true)
//                    setIntervalRatio(0.4f)
                    setAlpha(true)
                }

                cancelButton.setOnClickListener { findNavController().navigateUp() }

                cartImageBtn.setOnClickListener {
                    findNavController().navigate(R.id.action_phoneDetailsFragment_to_cartFragment)
                }

                titleTextView.text = phoneUIDetails.title

                favoriteButton.apply {
                    val favoritesInterActor = FavoritesInterActor(iFavoritesRepository)
                    val item =ProductItem(phoneUIDetails)
                    setImageResource(
                        if (favoritesInterActor.contains(item)) R.drawable.ic_favorite_on
                        else R.drawable.ic_favorite_off
                    )
                    setOnClickListener {
                        if (favoritesInterActor.contains(item)) {
                            favoritesInterActor.removeItem(item)
                            setImageResource(R.drawable.ic_favorite_off)
                        } else {
                            favoritesInterActor.addItem(item)
                            setImageResource(R.drawable.ic_favorite_on)
                        }
                    }
                }

                addToCartBtn.apply {
                    val cartInterActor = CartInterActor(iCartRepository)
                    val cartItem = CartItem(ProductItem(phoneUIDetails), 1)

                    if (cartInterActor.contains(cartItem)) {
                        setText(R.string.remove_from_cart)
                    } else {
//                        setText(R.string.add_to_cart)
//                        append(cartItem.product.price.toString())
                        text = String.format(
                            getString(R.string.add_to_cart),
                            cartItem.product.price
                        )
                    }

                    setOnClickListener {
                        if (cartInterActor.contains(cartItem)) {
                            cartInterActor.removeCartItem(cartItem)
                        } else {
                            cartInterActor.addCartItem(cartItem)
                        }

                        parentFragmentManager.setFragmentResult(DETAILS, Bundle())

                        findNavController().navigateUp()
                    }
                }

            }
    }

    private fun mock(details: UiDetails): UiDetails {
        with(details) {
            id = requireArguments().getString(ID)!!
            title = requireArguments().getString(TITLE)!!
            price = requireArguments().getInt(PRICE)

            val pictures = mutableListOf(requireArguments().getString(PICTURE)!!)
            pictures.addAll(images)
            images = pictures
        }

        return details
    }

    companion object {
        const val ID = "id"
        const val TITLE = "title"
        const val PICTURE = "picture"
        const val PRICE = "price"

        const val DETAILS = "fragment_details"
    }
}