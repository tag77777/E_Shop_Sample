package a77777_888.me.t.ecommercesample.presentation.favoritesfragment

import a77777_888.me.t.domain.repositories.IFavoritesRepository
import a77777_888.me.t.domain.usecases.FavoritesInterActor
import a77777_888.me.t.ecommercesample.R
import a77777_888.me.t.ecommercesample.databinding.FragmentFavoritesBinding
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites),
    FavoritesAdapter.EmptyListListener {

    @Inject lateinit var iFavoritesRepository: IFavoritesRepository
    private lateinit var favoritesInterActor: FavoritesInterActor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFavoritesBinding.bind(view)
        val adapter = FavoritesAdapter(iFavoritesRepository, this)
        favoritesInterActor = FavoritesInterActor(iFavoritesRepository)

        with(binding) {
            favoritesRecyclerView.adapter = adapter

            cancelButton.setOnClickListener {
                exit()
            }
        }

    }

    override fun onEmptyFavoritesList() {
        exit()
    }

    private fun exit() {
        findNavController().navigateUp()
    }

}