package a77777_888.me.t.ecommercesample.presentation

import a77777_888.me.t.data.remote.mockrepository.MockDataRepository
import a77777_888.me.t.ecommercesample.R
import a77777_888.me.t.ecommercesample.databinding.ActivityMainBinding
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayout

const val TAG = "Ecommerce"

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        val tabLayout = TabLayout(this)


        binding.textView.text = "Hello Sasha!!!"
        Log.e(TAG, "onCreate: Hello Sasha!!!", )


        try {
            MockDataRepository.init(this)
            binding.textView.text = MockDataRepository.phoneDetailsResponse.toString()
            Log.i(
                TAG,
                MockDataRepository.phoneDetailsResponse.toString() + "\n" +
                        MockDataRepository.phonesResponse.toString()
            )
        } catch (e: Exception) {
            Log.e(TAG, "onCreate: ", e)
        }
    }


}