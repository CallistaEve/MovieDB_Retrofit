package com.uc.week4retrofit.repository

import javax.inject.Inject
import com.uc.week4retrofit.retrofit.EndPointAPI
class MoviesRepository @Inject constructor(
    private val api: EndPointAPI
) {

    suspend fun getNowPlayingData(
        apiKey: String,
        language: String,
        page: Int)
            = api.getNowPlaying(apiKey, language, page)

    suspend fun getMovieDetails(
        movie_id: Int,
        apiKey: String)
            = api.getMovieDetails(movie_id, apiKey)

}