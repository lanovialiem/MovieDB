package com.uc.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uc.moviedb.R;
import com.uc.moviedb.helper.Const;
import com.uc.moviedb.model.Movies;
import com.uc.moviedb.viewmodel.MovieViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView detail_tv, rate_tv_detail, title_tv_detail, des_tv_detail, genre_tv_detail;
    private String movie_id = "", movieGenre=""; //untuk passing data
    private ImageView imgage_movie_detail;
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);

        Intent intent = getIntent();
        movie_id = intent.getStringExtra("movie_id");
        viewModel = new ViewModelProvider(MovieDetailsActivity.this).get(MovieViewModel.class);

        detail_tv = findViewById(R.id.detail_tv);
        rate_tv_detail = findViewById(R.id.rate_tv_detail);
        title_tv_detail = findViewById(R.id.title_tv_detail);
        des_tv_detail = findViewById(R.id.des_tv_detail);
        genre_tv_detail = findViewById(R.id.genre_tv_detail);
        imgage_movie_detail  = findViewById(R.id.imgage_movie_detail);

        viewModel.getMovieById(movie_id);
        viewModel.getResultGetMovieById().observe(MovieDetailsActivity.this, showResultMovie);
    }


    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String img_path = movies.getPoster_path().toString();
            Glide.with(MovieDetailsActivity.this).load(Const.IMG_URL + img_path).into(imgage_movie_detail);


            for (int i=0; i < movies.getGenres().size(); i++){
                if(i == movies.getGenres().size()-1){
                    movieGenre+=movies.getGenres().get(i).getName(); // jika isi size cuman satu ga pake koma
                }else{
                    movieGenre+= movies.getGenres().get(i).getName()+ ", "; // jika isi lebih dari 1 pake koma
                }
            }

            title_tv_detail.setText(movies.getTitle()); //nampilin judul
            detail_tv.setText(movie_id); // nampilin id
            rate_tv_detail.setText(""+movies.getVote_average()); // nambah rating
            des_tv_detail.setText(movies.getOverview()); //nambah deskripsi
            genre_tv_detail.setText(movieGenre); //nampilin genre

        }
    };

    public void onBackPressed(){
        finish();
    }


}