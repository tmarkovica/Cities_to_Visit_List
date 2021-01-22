package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_city_info#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_city_info extends Fragment {

    private TextView textViewCityInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_city_info, container, false);
        this.textViewCityInfo = view.findViewById(R.id.textViewCityInfo);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayCity();
    }

    private String cityInfo = "Randomize to get city!";

    public void showCityInfo(String cityInfo) {
        this.cityInfo = cityInfo;
        displayCity();
    }

    private void displayCity() {
        textViewCityInfo.setText(cityInfo);
    }
}