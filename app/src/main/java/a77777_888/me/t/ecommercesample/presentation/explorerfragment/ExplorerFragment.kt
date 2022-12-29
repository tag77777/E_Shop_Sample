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
import a77777_888.me.t.ecommercesample.presentation.detailsfragment.DetailsFragment
import a77777_888.me.t.ecommercesample.presentation.explorerfragment.adapters.BestSellerAdapter
import a77777_888.me.t.ecommercesample.presentation.explorerfragment.adapters.CategoriesAdapter
import a77777_888.me.t.ecommercesample.presentation.explorerfragment.adapters.HomeStoreAdapter
import a77777_888.me.t.ecommercesample.presentation.model.listCategory
import a77777_888.me.t.ecommercesample.presentation.model.product.UiBestSellerItem
import a77777_888.me.t.ecommercesample.presentation.model.product.UiHomeStoreItem
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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

   private lateinit var favoritesInterActor: FavoritesInterActor
   private lateinit var cartInterActor: CartInterActor

   private var bestSellersList: List<UiBestSellerItem>? = null
   private var homeStoresList: List<UiHomeStoreItem>? = null

   private lateinit var cartBadge: BadgeDrawable
   private lateinit var favoritesBadge: BadgeDrawable

   private lateinit var pulseIconAnimation: Animation
   private lateinit var enterAnimation: Animation

   private var categoriesAdapter = CategoriesAdapter(this)
   private var categoriesPosition = 0
   private var cartNumberIsChanged = false

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      favoritesInterActor = FavoritesInterActor(favoritesRepository)
      cartInterActor = CartInterActor(cartRepository)
      pulseIconAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.pulse)
      enterAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      binding = FragmentExplorerBinding.bind(view)
      initFragmentsResults()
      loadResultObserve()

      viewModel.savedState?.let {
         homeStoresList = it.homeStoreList
         bestSellersList = it.bestSellersList
         categoriesPosition = it.categoriesPosition
      }

      if (bestSellersList == null) {
            onCategorySelect(0)
      } else {
         setupUI()
      }

   }

   override fun onPause() {
      super.onPause()

      viewModel.savedState = State(
         homeStoresList,
         bestSellersList,
         categoriesPosition,
         binding.homeStoreCarousel.getSelectedPosition(),
         (binding.bestsellerRecyclerView.layoutManager as GridLayoutManager)
            .findFirstVisibleItemPosition()
      )
   }

   private fun initFragmentsResults() {
      childFragmentManager.setFragmentResultListener(
         SearchDialogFragment.SEARCH_DIALOG,
          viewLifecycleOwner,
      ) { _, bundle ->
         val searchString = bundle.getString(SearchDialogFragment.SEARCH_STRING)
         Toast.makeText(requireContext(), "Search - $searchString", Toast.LENGTH_LONG).show()
      }

      childFragmentManager.setFragmentResultListener(
         FilterDialogFragment.FILTER_DIALOG,
          viewLifecycleOwner,
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

      parentFragmentManager.setFragmentResultListener(
         DetailsFragment.DETAILS,
         viewLifecycleOwner,
      ) { _, _ ->
         cartNumberIsChanged = true
      }

       parentFragmentManager.setFragmentResultListener(
           CartFragment.CART,
           viewLifecycleOwner,
       ) { _, _ ->
           cartNumberIsChanged = true
       }
   }

   private fun loadResultObserve() =
      lifecycleScope.launch {
         viewModel.loadResultFlow.collect {
            with(binding) {
               when (it) {
                  is EmptyLoadResult -> {
                     loadStateView.messageTextView.setText(R.string.empty_list_message)
                     bestSellersList = listOf()
                     homeStoresList = listOf()
                     setupUI()

                     baseLayout.visibility = INVISIBLE
                     loadStateView.progressBar.visibility = INVISIBLE
                     loadStateView.messageTextView.visibility = VISIBLE
                     loadStateView.tryAgain.visibility = INVISIBLE
                  }
                  is PendingLoadResult -> {
                     loadStateView.messageTextView.setText(R.string.please_wait)
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

                     initLists(it.value as IProducts)
                     setupUI()
                     baseLayout.startAnimation(enterAnimation)
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

   private fun setupUI() {
      with(binding) {
         categoriesRecyclerView.adapter = categoriesAdapter

         homeStoreCarousel.apply {
            adapter = HomeStoreAdapter(this@ExplorerFragment, homeStoresList!!)
            setInfinite(true)
            setAlpha(false)
//            setIntervalRatio(0.4f)
//            setFlat(true)
         }

         bestsellerRecyclerView.adapter = BestSellerAdapter(
            this@ExplorerFragment,
            bestSellersList!!,
            favoritesRepository
         )

         if (viewModel.savedState != null) {
            categoriesRecyclerView.layoutManager?.scrollToPosition(categoriesPosition)
            categoriesAdapter.selectPosition(categoriesPosition)

            homeStoreCarousel.layoutManager
               ?.scrollToPosition(viewModel.savedState!!.homeStorePosition)

            bestsellerRecyclerView.layoutManager
               ?.scrollToPosition(viewModel.savedState!!.bestSellersPosition)
         }

         hotSalesSeeMoreBtn.setOnClickListener {
            val nextPosition = (homeStoreCarousel.getSelectedPosition() + 1) % 3
            homeStoreCarousel.getCarouselLayoutManager().scrollToPosition(nextPosition)
         }

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

         navigationBarView.favoritesBtn.setOnClickListener {
            findNavController().navigate(R.id.action_explorerFragment_to_favoritesFragment)
         }

         navigationBarView.exitBtn.setOnClickListener {
            requireActivity().finish()
         }

         val handler = Handler(Looper.getMainLooper())

         handler.postDelayed(
            {
               cartBadge = BadgeDrawable.create(requireContext())
               cartBadge.maxCharacterCount = 2
               BadgeUtils.attachBadgeDrawable(cartBadge, navigationBarView.cartBtn)
               cartBadge.number = cartInterActor.size()

               favoritesBadge = BadgeDrawable.create(requireContext())
               favoritesBadge.maxCharacterCount = 2
               BadgeUtils.attachBadgeDrawable(favoritesBadge, navigationBarView.favoritesBtn)
               favoritesBadge.number = favoritesInterActor.size()

            },
            500L
         )

         handler.postDelayed(
            {
               if (cartNumberIsChanged) {
                  navigationBarView.cartBtn.startAnimation(pulseIconAnimation)
                  cartNumberIsChanged = false
               }
            },
            1000L
         )

      }
   }
   @Suppress("UNCHECKED_CAST")
   private fun initLists(products: IProducts) {
      homeStoresList = products.homeStore as List<UiHomeStoreItem>
      bestSellersList = products.bestSeller as List<UiBestSellerItem>
   }

   override fun onCategorySelect(position: Int) {
      categoriesPosition = position
      viewModel.iProductsRepository = listCategory[position].productsRepository
   }

   override fun onHomeStoreBuyButtonClick(id: Int) {
      Toast.makeText(requireContext(), "Home store item purchased", Toast.LENGTH_SHORT).show()
   }

   override fun onBestSellerItemClick(args: Bundle) {
      findNavController().navigate(R.id.action_explorerFragment_to_phoneDetailsFragment, args)
   }

   override fun onFavoritesChanged() {
      favoritesBadge.number = favoritesInterActor.size()
      binding.navigationBarView.favoritesBtn.startAnimation(pulseIconAnimation)
   }

   class State (
      val homeStoreList: List<UiHomeStoreItem>?,
      val bestSellersList: List<UiBestSellerItem>?,
      val categoriesPosition: Int,
      val homeStorePosition: Int,
      val bestSellersPosition: Int
   )

}