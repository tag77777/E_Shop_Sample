package a77777_888.me.t.ecommercesample.presentation.favoritesfragment

import a77777_888.me.t.domain.repositories.IFavoritesRepository
import a77777_888.me.t.domain.usecases.FavoritesInterActor
import a77777_888.me.t.ecommercesample.R
import a77777_888.me.t.ecommercesample.databinding.FragmentFavoritesBinding
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites){

    @Inject lateinit var iFavoritesRepository: IFavoritesRepository
    private lateinit var favoritesInterActor: FavoritesInterActor

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFavoritesBinding.bind(view)
        val adapter = FavoritesAdapter(iFavoritesRepository)
        favoritesInterActor = FavoritesInterActor(iFavoritesRepository)

        with(binding) {
            favoritesRecyclerView.adapter = adapter

            cancelButton.setOnClickListener {
                findNavController().navigateUp()
            }

            checkoutButton.setOnClickListener {
                adapter.notifyDataSetChanged()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()

        val bundle = bundleOf(
            NUMBER to favoritesInterActor.size()
        )
        parentFragmentManager.setFragmentResult(FAVORITES, bundle)
    }

    companion object {
        const val FAVORITES = "favorites_fragment"
        const val NUMBER = "favorites_number"
    }
}