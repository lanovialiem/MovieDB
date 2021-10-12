package com.uc.moviedb.retrofit;

import com.uc.moviedb.model.Movies;
import com.uc.moviedb.model.nowplaying;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndPoint {

    @GET("movie/{movie_id}")
    Call<Movies> getMovieById(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey //setelah (?) disebut qurry dalam  example APi request
    );

    @GET("movie/now_playing")
    Call<nowplaying> getNowPlaying(
            @Query("api_key") String apiKey //setelah (?) disebut qurry dalam  example APi request
    );

}
