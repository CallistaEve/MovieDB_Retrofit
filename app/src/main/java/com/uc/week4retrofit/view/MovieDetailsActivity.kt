package com.uc.week4retrofit.view

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.uc.week4retrofit.adapter.Genre_Adapter
import com.uc.week4retrofit.adapter.LangAdapter
import com.uc.week4retrofit.adapter.ProductionCompAdapter
import com.uc.week4retrofit.databinding.ActivityMovieDetailsBinding
import com.uc.week4retrofit.helper.Const
import com.uc.week4retrofit.viewmodel.MoviesViewModel
import com.uc.week4retrofit.model.Genre
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var moviesViewModel: MoviesViewModel
    private var movieID = 0
    private lateinit var adapterGenre: Genre_Adapter
    private lateinit var adapterProCom: ProductionCompAdapter
    private lateinit var adapterLang: LangAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GetIntent()

        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        setViewModel()
    }

    private fun GetIntent() {
        movieID = intent.getIntExtra("movieID", 0)
    }

    private fun setViewModel() {
        val loading = LoadingDialog(this)
        loading.startLoading()
        val handler = Handler()
        moviesViewModel.getMovieDetails(movieID, Const.API_KEY)
        handler.postDelayed(object : Runnable {
            override fun run() {
                loading.isDismiss()
                moviesViewModel.movieDetails.observe(this@MovieDetailsActivity, Observer { response ->
                    binding.tvTitleMoviedetails.text = response.title
                    binding.descContentTv.text = response.overview

                    Glide.with(applicationContext)
                        .load(Const.IMG_URL + response.backdrop_path)
                        .into(binding.ivPosterMoviedetails)

                    binding.genreRv.layoutManager =
                        LinearLayoutManager(this@MovieDetailsActivity, LinearLayoutManager.HORIZONTAL, false)
                    adapterGenre = Genre_Adapter(response.genres)
                    binding.genreRv.adapter = adapterGenre

                    binding.productionCompRv.layoutManager =
                        LinearLayoutManager(this@MovieDetailsActivity, LinearLayoutManager.HORIZONTAL, false)
                    adapterProCom = ProductionCompAdapter(response.production_companies)
                    binding.productionCompRv.adapter = adapterProCom

                    binding.langRv.layoutManager =
                        LinearLayoutManager(this@MovieDetailsActivity, LinearLayoutManager.HORIZONTAL, false)
                    adapterLang = LangAdapter(response.spoken_languages)
                    binding.langRv.adapter = adapterLang

                })
            }
        }, 1500)
    }
}