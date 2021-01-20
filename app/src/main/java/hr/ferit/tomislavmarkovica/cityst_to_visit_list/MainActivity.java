package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOError;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String DATA_FETCH = "hr.ferit.tomislavmarkovica.cityst_to_visit_list.DATA_FETCH";

    private Button buttonFavorites;
    private Button buttonExplore;

    private TextView result;
    private OkHttpClient client;

    private DataFetch dataFetch;

    private Intent intentFavorites;
    private Intent intentExplore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.result);

        this.dataFetch = new DataFetch(this);

        createIntents();

        setButtonFavoirtes();

        setButtonExplore();
    }

    private void createIntents() {
        intentFavorites = new Intent(this, FavoritesActivity.class);

        intentExplore = new Intent(this, ExploreActivity.class);

        // continue here
        //MyParcelable myParcelable = new MyParcelable();
        //myParcelable.setDataFetch(dataFetch);
        //intentExplore.putExtra(DATA_FETCH, (MyParcelable) myParcelable);
        //intentExplore.putExtra(DATA_FETCH, 101); // ovaj radi

        MyParcelable myParcelable = new MyParcelable();
        //myParcelable.setDataFetch(dataFetch);
        myParcelable.setN(1234);

        intentExplore.putExtra(DATA_FETCH, myParcelable);
    }

    private void setButtonFavoirtes() {
        buttonFavorites = (Button) findViewById(R.id.buttonFavorites);

        buttonFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentFavorites);
            }
        });
    }

    private void setButtonExplore() {
        buttonExplore = (Button) findViewById(R.id.buttonExplore);

        buttonExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentExplore);
            }
        });
    }
}