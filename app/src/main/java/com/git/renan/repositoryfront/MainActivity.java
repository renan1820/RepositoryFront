package com.git.renan.repositoryfront;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.git.renan.repositoryfront.adapter.ExpandableRecyclerAdapter;
import com.git.renan.repositoryfront.adapter.MainMenuAdapter;
import com.git.renan.repositoryfront.repository.MainMenuRepository;

import static com.git.renan.repositoryfront.repository.MainMenuRepository.onMenuItemSelectedRecycler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponentMenu();

    }

    private void initComponentMenu(){
        RecyclerView recyclerView = findViewById(R.id.main_recycler);
        MainMenuAdapter mainMenuAdapter = new MainMenuAdapter(this, MainMenuRepository.generateMenuItemsRepository(), new MainMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int itemId) {
                onMenuItemSelectedRecycler(itemId,MainActivity.this);
            }
        });
        (findViewById(R.id.bt_about)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "icon info", Toast.LENGTH_SHORT).show();
            }
        });

        mainMenuAdapter.setMode(ExpandableRecyclerAdapter.MODE_ACCORDION);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(mainMenuAdapter);

    }


    private void getThemeView(){
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                // Night mode is not active, we're using the light theme
                Toast.makeText(this, "TEMA CLARO", Toast.LENGTH_SHORT).show();
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                // Night mode is active, we're using dark theme
                Toast.makeText(this, "TEMA ESCURO", Toast.LENGTH_SHORT).show();

                break;
        }
    }

}