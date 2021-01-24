package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ExploreActivity extends AppCompatActivity {

    private Button buttonFavorites;
    private Button buttonLike;
    private Button buttonRandom;

    private DataFetch dataFetch;
    private TextView textViewCityInfo;

    private FragmentTransaction fragmentTransaction;
    private Switch mSwitch;

    private fragment_city_map fragmentCityMap;
    private fragment_city_info fragmentCityInfo;


    private ExploreFragmentsHandler exploreFragmentsHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        textViewCityInfo = (TextView) findViewById(R.id.textViewCityInfo);
        this.dataFetch = DataFetch.getInstance();

        setExploreFragmentsHandler();

        setButtonFavoirtes();
        setButtonLike();
        setButtonRandom();
    }

    private void setExploreFragmentsHandler() {
        mSwitch = (Switch) findViewById(R.id.switchCityInfo);
        exploreFragmentsHandler = new ExploreFragmentsHandler();
        exploreFragmentsHandler.setSwitch(mSwitch);
        exploreFragmentsHandler.setFragmentManager(getSupportFragmentManager());
        exploreFragmentsHandler.switchFragment();
    }

    private void setButtonFavoirtes() {
        buttonFavorites = (Button) findViewById(R.id.buttonFavorites);

        buttonFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExploreActivity.this, FavoritesActivity.class));
            }
        });
    }

    private void setButtonLike() {
        buttonLike = (Button) findViewById(R.id.buttonLike);

        buttonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exploreFragmentsHandler.addCityToFavorites(getApplicationContext());
            }
        });
    }

    private void setButtonRandom() {
        buttonRandom = (Button) findViewById(R.id.buttonRandom);

        buttonRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exploreFragmentsHandler.enableSwitch();
                exploreFragmentsHandler.setCity(getRandomCity());
            }
        });
    }

    private int getRandomNumber() {
        Random rn = new Random();
        int randomNumber = rn.nextInt(this.dataFetch.getNumberOfCities()) + 0;
        return randomNumber;
    }

    private City getRandomCity() {
        return this.dataFetch.getCityAt(getRandomNumber());
    }
}