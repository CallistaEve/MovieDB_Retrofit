package com.uc.week4retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.uc.week4retrofit.adapter.NowPlayingAdapter
import com.uc.week4retrofit.databinding.ActivityMainBinding
import com.uc.week4retrofit.helper.Const
import com.uc.week4retrofit.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NowPlayingAdapter
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loading = LoadingDialog(this)
        loading.startLoading()
        val handler = Handler()
        handler.postDelayed(object : Runnable{
            override fun run() {
                loading.isDismiss()

                moviesViewModel = ViewModelProvider(this@MainActivity).get(MoviesViewModel::class.java)
                moviesViewModel.getNowPlaying(Const.API_KEY, "en-US", 1)

                moviesViewModel.nowPlaying.observe(this@MainActivity, Observer { response ->
                    binding.rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = NowPlayingAdapter(response)
                    binding.rvMain.adapter = adapter
                })
            }
        },1500)

    }
}