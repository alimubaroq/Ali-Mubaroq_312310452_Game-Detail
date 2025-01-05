package com.example.gamedetall;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavoritesFragment extends Fragment {
    private DatabaseHelper dbHelper;
    private RecyclerView recyclerView;
    private TextView emptyText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        dbHelper = new DatabaseHelper(requireContext());
        recyclerView = view.findViewById(R.id.favorites_recycler_view);
        emptyText = view.findViewById(R.id.empty_favorites_text);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        updateFavoritesList();

        return view;
    }

    private void updateFavoritesList() {
        Cursor cursor = dbHelper.getAllFavorites();

        if (cursor == null || cursor.getCount() == 0) {
            recyclerView.setVisibility(View.GONE);
            emptyText.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyText.setVisibility(View.GONE);
            recyclerView.setAdapter(new FavoritesAdapter(cursor));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateFavoritesList();
    }
}