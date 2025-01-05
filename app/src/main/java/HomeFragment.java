package com.example.gamedetall;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Mobile Legends click listener
        view.findViewById(R.id.mobile_legends_layout).setOnClickListener(v -> {
            openDetailActivity("Mobile Legends",
                    "Mobile Legends: Bang Bang (MLBB) adalah game MOBA 5v5 buatan Moonton yang dimainkan di ponsel. Tujuan utama adalah menghancurkan base lawan di peta dengan tiga lane (Top, Mid, Bottom).\n" +
                            "\n" +
                            "Game ini memiliki banyak hero dengan peran seperti Tank, Fighter, Marksman, Assassin, Mage, dan Support. Durasi permainan rata-rata 10-20 menit, dengan mode seperti Classic, Ranked, dan Brawl.\n" +
                            "\n" +
                            "MLBB populer di komunitas e-sports dan mendukung turnamen besar seperti MPL dan M-Series. Game ini gratis dimainkan tetapi menawarkan pembelian dalam aplikasi untuk skin dan hero.",
                    R.drawable.mobile_legends);
        });

        // Wukong click listener
        view.findViewById(R.id.wukong_layout).setOnClickListener(v -> {
            openDetailActivity("Wukong Black Myth",
                    "Black Myth: Wukong adalah game action RPG dari Game Science Studio yang terinspirasi dari \"Journey to the West\". Pemain mengendalikan Sun Wukong dengan kemampuan bertarung cepat, transformasi menjadi makhluk lain, serta akses ke berbagai senjata dan sihir.\n" +
                            "\n" +
                            "Dibangun dengan Unreal Engine 5, game ini menghadirkan grafis realistis, dunia open-world penuh elemen mitologi Tiongkok, dan pertarungan melawan musuh serta bos yang menantang. Dijadwalkan rilis pada 2024 untuk PC dan konsol generasi terbaru.",
                    R.drawable.wukong);
        });

        // Chained Together click listener
        view.findViewById(R.id.chained_together_layout).setOnClickListener(v -> {
            openDetailActivity("Chained Together",
                    "Chained Together adalah game platformer 3D yang dikembangkan oleh Anegar Games dan dirilis pada 19 Juni 2024. Dalam permainan ini, hingga empat pemain terhubung oleh rantai dan harus bekerja sama untuk memanjat dari neraka menuju puncak, mengatasi berbagai rintangan dan tantangan di sepanjang jalan. Game ini menawarkan mode single-player dan multiplayer, dengan berbagai tingkat kesulitan, termasuk mode di mana lava terus naik, menambah tekanan pada pemain. Chained Together tersedia untuk platform Windows dan telah menerima ulasan positif dari komunitas game.",
                    R.drawable.chained_together);
        });

        return view;
    }

    private void openDetailActivity(String title, String description, int imageResId) {
        Intent intent = new Intent(getActivity(), DetailGameActivity.class);
        intent.putExtra("gameTitle", title);
        intent.putExtra("gameDescription", description);
        intent.putExtra("gameImage", imageResId);
        startActivity(intent);
    }
    }