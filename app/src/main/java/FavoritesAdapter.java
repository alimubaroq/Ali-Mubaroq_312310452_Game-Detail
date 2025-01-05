package com.example.gamedetall;

import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {
    private Cursor cursor;

    public FavoritesAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (cursor.moveToPosition(position)) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow("image"));

            holder.titleText.setText(title);
            holder.imageView.setImageResource(image);

            // Tambahkan onClick listener
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), DetailGameActivity.class);
                intent.putExtra("gameTitle", title);
                intent.putExtra("gameDescription", description);
                intent.putExtra("gameImage", image);
                v.getContext().startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleText;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            titleText = view.findViewById(R.id.favorite_title);
            imageView = view.findViewById(R.id.favorite_image);
        }
    }
}