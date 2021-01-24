package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity implements NameClickListener {

    private RecyclerView recyclerView;
    private List<City> dataList;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        customAdapter = new CustomAdapter(getApplicationContext(), this);

        recyclerView = findViewById(R.id.recyclerViewFavoirteCities);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(customAdapter);
    }

    @Override
    public void onXIconClick(int position) {
        ((CustomAdapter)recyclerView.getAdapter()).removeName(position);
    }

    @Override
    public void onNameClick(int position) {
        ((CustomAdapter)recyclerView.getAdapter()).nameClicked(position);
    }
}