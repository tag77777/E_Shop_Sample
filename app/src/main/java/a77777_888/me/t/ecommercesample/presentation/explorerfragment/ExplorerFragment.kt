package a77777_888.me.t.ecommercesample.presentation.explorerfragment

import a77777_888.me.t.ecommercesample.databinding.FragmentExplorerBinding
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class ExplorerFragment : Fragment() {
   private lateinit var binding: FragmentExplorerBinding
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      binding = FragmentExplorerBinding.bind(view)

      initCategoriesTabLayout()
   }

   private fun initCategoriesTabLayout() {

   }
}