package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_info, container, false);
    }

    private TextView textViewCityInfo;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //textViewCityInfo = view.findViewById(R.id.textViewCityInfo);
    }

    public void showCityInfo(City city) {
        //textViewCityInfo.setText(cityInfo);
        //textViewCityInfo.setText(city.toString());
    }

    public void showCityInfo(String info) {
        textViewCityInfo.setText(info);
    }
}