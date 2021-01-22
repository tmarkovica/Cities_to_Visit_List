package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity implements NameClickListener {

    private RecyclerView recyclerView;
    private List<City> dataList;
    private CustomAdapter customAdapter;

    //private Button buttonAddPerson;
    //private EditText editTextAddPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        setupNames();
        setupRecyclerView();
    }

    private void setupNames() {
        dataList = new ArrayList<>();
        dataList.add(new City("Zagreb"));
        dataList.add(new City("Osijek"));
        dataList.add(new City("Varaždin"));
        dataList.add(new City("Đurđevac"));
        dataList.add(new City("Koprivnica"));
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewFavoirteCities);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        customAdapter = new CustomAdapter(dataList, this);
        recyclerView.setAdapter(customAdapter);
    }

    @Override
    public void onNameClick(int position) {
        //Log.d("onNameClick", "remove name" + dataList.get(position));

        ((CustomAdapter)recyclerView.getAdapter()).removeName(position);
    }

    public void onXIconClick(int position) {
        ((CustomAdapter)recyclerView.getAdapter()).removeName(position);
    }
}