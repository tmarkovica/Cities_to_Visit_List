package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    public String onNameClick(int position) {
        return ((CustomAdapter)recyclerView.getAdapter()).nameClicked(position);
    }
}