package com.example.gamedetall;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;

public class DetailGameActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private boolean isFavorite = false;
    private String gameTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hilangkan Action Bar/Header
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_detail_game);

        // Inisialisasi DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Setup tombol back dengan style yang benar
        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setImageResource(R.drawable.ic_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Atau bisa gunakan finish();
            }
        });

        // Ambil data game dari intent
        gameTitle = getIntent().getStringExtra("gameTitle");
        String gameDesc = getIntent().getStringExtra("gameDescription");
        int gameImage = getIntent().getIntExtra("gameImage", 0);

        // Setup Views
        TextView titleView = findViewById(R.id.detail_game_title);
        TextView descView = findViewById(R.id.detail_game_description);
        ImageView imageView = findViewById(R.id.detail_game_image);
        ToggleButton favButton = findViewById(R.id.favorite_button);

        // Set data ke dalam Views
        titleView.setText(gameTitle);
        descView.setText(gameDesc);
        imageView.setImageResource(gameImage);

        // Cek apakah game sudah ada di favorit
        isFavorite = dbHelper.isFavorite(gameTitle);
        favButton.setChecked(isFavorite);

        // Atur listener untuk tombol favorite
        favButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                dbHelper.addFavorite(gameTitle, gameDesc, gameImage);
            } else {
                dbHelper.removeFavorite(gameTitle);
            }
        });
    }
}