package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ExploreActivity extends AppCompatActivity {

    private Button buttonFavorites;
    private Button buttonLike;
    private Button buttonRandom;

    //
    private DataFetch dataFetch;
    private TextView textViewCityInfo;

    private FragmentTransaction fragmentTransaction;
    private Switch mSwitch;

    private TextView tvTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        textViewCityInfo = (TextView) findViewById(R.id.textViewCityInfo);

        this.dataFetch = DataFetch.getInstance();

        tvTemp = (TextView) findViewById(R.id.tvTemp);

        createFragments();
        setSwitch();

        setButtonFavoirtes();
        setButtonLike();
        setButtonRandom();

        //getRandomCity();
    }

    private fragment_city_map fragmentCityMap;
    private fragment_city_info fragmentCityInfo;

    private void createFragments() {
        fragmentCityMap = new fragment_city_map();
        fragmentCityInfo = new fragment_city_info();
    }

    private void setSwitch() {
        mSwitch = (Switch) findViewById(R.id.switchCityInfo);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragmentCityInfo);
        fragmentTransaction.commit();

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer, fragmentCityMap);
                    fragmentTransaction.commit();
                    mSwitch.setText("Show city info");
                    //displayCitiy();
                }
                else {
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer, fragmentCityInfo);
                    fragmentTransaction.commit();
                    mSwitch.setText("Show on map");
                    //displayCitiy();
                }
            }
        });
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
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setButtonRandom() {
        buttonRandom = (Button) findViewById(R.id.buttonRandom);

        buttonRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRandomCity();Log.i("My Tag", String.valueOf(fragmentCityInfo instanceof fragment_city_info));
            }
        });
    }

    private City currentCity;

    private void displayCitiy() {
        if (mSwitch.isChecked()) {
            //fragmentCityMap.setCooridnates(currentCity.get_longitude(), currentCity.get_longitude());
        }
        else {
            //fragmentCityInfo.showCityInfo(currentCity.toString());

            //fragmentCityInfo.showCityInfo("hahahhohoh");

            Log.i("My Tag", String.valueOf(fragmentCityInfo instanceof fragment_city_info));

            tvTemp.setText(currentCity.toString());

        }
    }

    private int getRandomNumber() {
        Random rn = new Random();
        int randomNumber = rn.nextInt(this.dataFetch.getNumberOfCities()-1) + 0;
        return randomNumber;
    }

    private void getRandomCity() {
        this.currentCity = this.dataFetch.getCityAt(getRandomNumber());
        displayCitiy();
    }
}