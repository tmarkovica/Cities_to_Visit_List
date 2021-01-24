package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class ExploreFragmentsHandler {

    private fragment_city_map fragmentCityMap;
    private fragment_city_info fragmentCityInfo;

    private Switch mSwitch;

    public ExploreFragmentsHandler() {
        createFragments();
    }

    private void createFragments() {
        fragmentCityMap = new fragment_city_map();
        fragmentCityInfo = new fragment_city_info();
    }

    public void setSwitch(Switch mSwitch) {
        this.mSwitch = mSwitch;
    }

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public void setFragmentManager(FragmentManager supportFragmentManager) {
        this.fragmentManager = supportFragmentManager;

    }

    public void switchFragment() {
        this.fragmentTransaction = this.fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragmentContainer, fragmentCityInfo);
        fragmentTransaction.commit();

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer, fragmentCityMap);
                    fragmentTransaction.commit();
                    mSwitch.setText("Show city info");
                    displayCitiy();
                }
                else {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer, fragmentCityInfo);
                    fragmentTransaction.commit();
                    mSwitch.setText("Show on map");
                    displayCitiy();
                }
            }
        });
    }

    private City currentCity = null;

    public void setCity(City city) {
        if (city != null) {

        }
        this.currentCity = city;
        displayCitiy();
    }

    private void displayCitiy() {
        if (this.mSwitch.isChecked()) {
            this.fragmentCityMap.setCooridnates(this.currentCity.get_latitude(), this.currentCity.get_longitude());
        }
        else {
            this.fragmentCityInfo.showCityInfo(this.currentCity.toString());
        }
    }

    public void enableSwitch() {
        this.mSwitch.setEnabled(true);
    }

    public void addCityToFavorites(Context context) {
        if (currentCity != null) {
            IOData_Favorites.saveCity(context, currentCity);
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
