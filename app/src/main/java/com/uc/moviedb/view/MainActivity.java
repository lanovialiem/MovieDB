package com.uc.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.uc.moviedb.R;
import com.uc.moviedb.helper.Const;
import com.uc.moviedb.model.Movies;
import com.uc.moviedb.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel viewModel;
    Button btn_hit;
    private ImageView img_poster;
    private TextView txt_show;
    private TextInputLayout til_movie_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        til_movie_id = findViewById(R.id.til_movie_id_main);
        txt_show = findViewById(R.id.txt_show_main);
        img_poster = findViewById(R.id.img_poster_main);
        viewModel = new ViewModelProvider(MainActivity.this).get(MovieViewModel.class);

        btn_hit = findViewById((R.id.btn_hit_main));
        btn_hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieId = til_movie_id.getEditText().getText().toString().trim();
                if (movieId.isEmpty()){
                    til_movie_id.setError("please fill id field");
                }else{
                    til_movie_id.setError(null);
                    viewModel.getMovieById(movieId);
                    viewModel.getResultGetMovieById().observe(MainActivity.this, showResultMovie);
                }
            }
        });
    }








    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
//            Log.e("cek", movies.getTitle());
            if ( movies == null){
                txt_show.setText("Movie ID is not available in movieDB");
            }else {
                String title = movies.getTitle();
                String img_path = movies.getPoster_path().toString();
                Glide.with(MainActivity.this).load(Const.IMG_URL + img_path).into(img_poster);
                txt_show.setText(title);
            }
        }
    };
}