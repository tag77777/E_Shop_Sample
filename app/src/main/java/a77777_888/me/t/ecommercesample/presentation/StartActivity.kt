package a77777_888.me.t.ecommercesample.presentation

import a77777_888.me.t.data.remote.mockrepository.MockDataRepository
import a77777_888.me.t.ecommercesample.TAG
import a77777_888.me.t.ecommercesample.databinding.ActivitySplashBinding
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Fade
import android.transition.TransitionManager
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        window.statusBarColor = Color.BLACK
        setContentView(binding.root)

        MockDataRepository.init(applicationContext)

        animationAndExit()

    }

    private fun animationAndExit() {
        with(binding) {
            layout.postDelayed(
                {
                    val transition = Fade().apply { duration = 900 }
                    TransitionManager.beginDelayedTransition(layout, transition)
                    imageView.visibility = VISIBLE
                }, 100
            )
            layout.postDelayed(
                {
                    val transition = ChangeBounds().apply { duration = 900 }
                    TransitionManager.beginDelayedTransition(layout, transition)
                    imageView.scaleX = 5.5f
                    imageView.scaleY = 5.5f
                }, 1000
            )
            layout.postDelayed(
                {
                    val transition = Fade().apply { duration = 900 }
                    TransitionManager.beginDelayedTransition(layout, transition)
                    ecommerceTextView.visibility = VISIBLE
                }, 2000
            )
            layout.postDelayed(
                {
                    val transition = Fade().apply { duration = 900 }
                    TransitionManager.beginDelayedTransition(layout, transition)
                    conceptTextView.visibility = VISIBLE
                }, 3000
            )
            layout.postDelayed(
                {
                    val transition = Fade().apply { duration = 900 }
                    TransitionManager.beginDelayedTransition(layout, transition)
                    layout.visibility = INVISIBLE
                }, 6000
            )
            layout.postDelayed(
                {
                    val intent = Intent(this@StartActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 7000
            )
        }
    }

}