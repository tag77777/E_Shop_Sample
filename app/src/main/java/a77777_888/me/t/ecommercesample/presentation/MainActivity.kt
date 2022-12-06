package a77777_888.me.t.ecommercesample.presentation

import a77777_888.me.t.data.remote.mockrepository.MockDataRepository
import a77777_888.me.t.ecommercesample.R
import a77777_888.me.t.ecommercesample.databinding.ActivityMainBinding
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MockDataRepository.init(applicationContext)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.WHITE


    }

}