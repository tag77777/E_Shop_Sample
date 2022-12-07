package a77777_888.me.t.ecommercesample.presentation.explorerfragment

import a77777_888.me.t.domain.model.*
import a77777_888.me.t.domain.repositories.ICartRepository
import a77777_888.me.t.domain.repositories.IFavoritesRepository
import a77777_888.me.t.domain.usecases.CartInterActor
import a77777_888.me.t.domain.usecases.FavoritesInterActor
import a77777_888.me.t.ecommercesample.R
import a77777_888.me.t.ecommercesample.TAG
import a77777_888.me.t.ecommercesample.databinding.FragmentExplorerBinding
import a77777_888.me.t.ecommercesample.presentation.FilterDialogFragment
import a77777_888.me.t.ecommercesample.presentation.SearchDialogFragment
import a77777_888.me.t.ecommercesample.presentation.cartfragment.CartFragment
import a77777_888.me.t.ecommercesample.presentation.explorerfragment.adapters.BestSellerAdapter
import a77777_888.me.t.ecommercesample.presentation.explorerfragment.adapters.CategoriesAdapter
import a77777_888.me.t.ecommercesample.presentation.explorerfragment.adapters.HomeStoreAdapter
import a77777_888.me.t.ecommercesample.presentation.favoritesfragment.FavoritesFragment
import a77777_888.me.t.ecommercesample.presentation.model.phone.UiBestSellerItem
import a77777_888.me.t.ecommercesample.presentation.model.phone.UiHomeStoreItem
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalBadgeUtils
class ExplorerFragment : Fragment(R.layout.fragment_explorer),
   CategoriesAdapter.CategoryListener,
   HomeStoreAdapter.HomeStoreBuyButtonOnClickListener,
   BestSellerAdapter.EventListener {

   @Inject lateinit var favoritesRepository: IFavoritesRepository
   @Inject lateinit var cartRepository: ICartRepository

   private lateinit var binding: FragmentExplorerBinding
   private val viewModel by viewModels<ExplorerViewModel>()
   private lateinit var bestSellersList: List<UiBestSellerItem>
   private lateinit var homeStoresList: List<UiHomeStoreItem>
   private lateinit var favoritesInterActor: FavoritesInterActor
   private lateinit var cartInterActor: CartInterActor
   private lateinit var cartBadge: BadgeDrawable
   private lateinit var favoritesBadge: BadgeDrawable

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      binding = FragmentExplorerBinding.bind(view)
      favoritesInterActor = FavoritesInterActor(favoritesRepository)
      cartInterActor = CartInterActor(cartRepository)

      initFragmentsResults()
      loadResultObserve()
      viewModel.getData()
   }

   private fun initFragmentsResults() {
      childFragmentManager.setFragmentResultListener(
         SearchDialogFragment.SEARCH_DIALOG,
         this,
      ) { _, bundle ->
         val searchString = bundle.getString(SearchDialogFragment.SEARCH_STRING)
         Toast.makeText(requireContext(), "Search - $searchString", Toast.LENGTH_LONG).show()
      }

      childFragmentManager.setFragmentResultListener(
         FilterDialogFragment.FILTER_DIALOG,
         this,
      ) { _, bundle ->
         val brand = bundle.getString(FilterDialogFragment.BRAND)
         val price = bundle.getString(FilterDialogFragment.PRICE)
         val size = bundle.getString(FilterDialogFragment.SIZE)
         Toast.makeText(
            requireContext(),
            "Filter - $brand, $price, $size",
            Toast.LENGTH_LONG
         ).show()
      }

      childFragmentManager.setFragmentResultListener(
         FavoritesFragment.FAVORITES,
         this,
      ) { _, bundle ->
         favoritesBadge.number = bundle.getInt(FavoritesFragment.NUMBER)
      }

childFragmentManager.setFragmentResultListener(
         CartFragment.CART,
         this,
      ) { _, bundle ->
         cartBadge.number = bundle.getInt(CartFragment.NUMBER)
      }
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

                     initLists(it.value as IPhones)
                     initUI()
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

   private fun initUI() {
      with(binding) {
         categoriesRecyclerView.adapter = CategoriesAdapter(this@ExplorerFragment)

         hotSaleCarousel.apply {
            adapter = HomeStoreAdapter(this@ExplorerFragment, homeStoresList)
            setInfinite(true)
//            setIntervalRatio(0.4f)
//            setAlpha(true)
            setFlat(true)
         }

         hotSalesSeeMoreBtn.setOnClickListener {
            val nextPosition = hotSaleCarousel.getSelectedPosition() + 1
            hotSaleCarousel.getCarouselLayoutManager().scrollToPosition(nextPosition)
         }

         bestsellerRecyclerView.adapter = BestSellerAdapter(
            this@ExplorerFragment,
            bestSellersList,
            favoritesRepository
         )

         bestSellersSeeMoreBtn.setOnClickListener {
            val newPosition = (bestsellerRecyclerView.layoutManager as GridLayoutManager)
               .findLastVisibleItemPosition() + 1
            bestsellerRecyclerView.layoutManager?.scrollToPosition(newPosition)
         }

         navigationBarView.searchBtn.setOnClickListener {
            SearchDialogFragment().show(childFragmentManager, SearchDialogFragment.SEARCH_DIALOG)
         }

         navigationBarView.filterBtn.setOnClickListener {
            FilterDialogFragment().show(childFragmentManager, FilterDialogFragment.FILTER_DIALOG)
         }

         navigationBarView.cartBtn.setOnClickListener {
            findNavController().navigate(R.id.action_explorerFragment_to_cartFragment)
         }
         cartBadge = BadgeDrawable.create(requireContext())
         BadgeUtils.attachBadgeDrawable(cartBadge, navigationBarView.cartBtn)
         cartBadge.maxCharacterCount = 2
         cartBadge.number = cartInterActor.size()

         navigationBarView.favoritesBtn.setOnClickListener {
            findNavController().navigate(R.id.action_explorerFragment_to_favoritesFragment)
         }
         favoritesBadge = BadgeDrawable.create(requireContext())
         BadgeUtils.attachBadgeDrawable(favoritesBadge, navigationBarView.favoritesBtn)
         favoritesBadge.maxCharacterCount = 2
         favoritesBadge.number = favoritesInterActor.size()
      }
   }
   @Suppress("UNCHECKED_CAST")
   private fun initLists(phones: IPhones) {
      homeStoresList = phones.homeStore as List<UiHomeStoreItem>
//      val homeStores = mutableListOf<UiHomeStoreItem>()
//      phones.homeStore.forEach { it as UiHomeStoreItem }
//      homeStoresList = homeStores

      bestSellersList = phones.bestSeller as List<UiBestSellerItem>
//      val bestSellers = mutableListOf<UiBestSellerItem>()
//      phones.bestSeller.forEach { it as UiBestSellerItem }
//      bestSellersList = bestSellers
   }

   override fun onCategorySelect(position: Int) {
      if (position == 0) viewModel.getData()
   }

   override fun onHomeStoreBuyButtonClick(id: Int) {
      Toast.makeText(requireContext(), "Home store item purchased", Toast.LENGTH_SHORT).show()
   }

   override fun onBestSellerItemClick(args: Bundle) {
      findNavController().navigate(R.id.action_explorerFragment_to_phoneDetailsFragment, args)
   }

   override fun onFavoritesChanged() {
      favoritesBadge.number = favoritesInterActor.size()
   }
}