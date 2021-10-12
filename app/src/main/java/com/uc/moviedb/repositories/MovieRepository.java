package com.uc.moviedb.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uc.moviedb.helper.Const;
import com.uc.moviedb.model.Movies;
import com.uc.moviedb.model.nowplaying;
import com.uc.moviedb.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static MovieRepository repository;

    private MovieRepository(){}

    public static MovieRepository getInstance(){
        if(repository == null){
            repository = new MovieRepository();
        }
        return repository;
    }

    public MutableLiveData<Movies> getMovieData (String movieId){
        final MutableLiveData<Movies> result = new MutableLiveData<>();

        ApiService.endPoint().getMovieById(movieId, Const.API_KEY).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
            Log.e("cek", t.toString());
            }
        });

        return result;
    }

    public MutableLiveData<nowplaying> getNowPlayingData (){
        final MutableLiveData<nowplaying> result = new MutableLiveData<>();

        ApiService.endPoint().getNowPlaying(Const.API_KEY).enqueue(new Callback<nowplaying>() {
            @Override
            public void onResponse(Call<nowplaying> call, Response<nowplaying> response) {
            result.setValue(response.body());
            }
            @Override
            public void onFailure(Call<nowplaying> call, Throwable t) {

            }
        });
        return result;
    }


}
