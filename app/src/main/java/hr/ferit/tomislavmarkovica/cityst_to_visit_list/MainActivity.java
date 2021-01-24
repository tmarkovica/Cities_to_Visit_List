package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

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

        createIntents();
        setButtonFavoirtes();
        setButtonExplore();

        //IOData_Favorites.emptyFile(getBaseContext());
    }

    private void createIntents() {
        intentFavorites = new Intent(this, FavoritesActivity.class);
        intentExplore = new Intent(this, ExploreActivity.class);
    }

    private void setButtonFavoirtes() {
        buttonFavorites = (Button) findViewById(R.id.buttonFavorites);

        buttonFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentFavorites.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentFavorites);
            }
        });
    }

    private void setButtonExplore() {
        buttonExplore = (Button) findViewById(R.id.buttonExplore);

        buttonExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentExplore.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentExplore);
            }
        });
    }
}