package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

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
        DataFetch df = DataFetch.getInstance();
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