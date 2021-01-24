package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.NameViewHolder> {

    private static final String TAG = "CustomAdapter";
    private NameClickListener nameClickListener;

    private List<City> dataList;

    private IOData_Favorites fetchFavorites;
    private Context context;

    public CustomAdapter(Context context, NameClickListener nameClickListener) {
        this.context = context;
        this.nameClickListener = nameClickListener;

        loadFavorites();
    }

    private void loadFavorites() {
        dataList = IOData_Favorites.load(context);
        notifyDataSetChanged();
    }

    private void updateFavoritesFile() {
        IOData_Favorites.save(context, dataList);
    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,false);

        return new NameViewHolder(listItemView, nameClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder holder, int position) {
        holder.setName(dataList.get(position).get_name());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void removeName(int position) {
        if(dataList.size() > position) {
            dataList.remove(position);
            notifyDataSetChanged();
            updateFavoritesFile();
        }
    }

    public void nameClicked(int position) {
        //DataStore_Favorites.save(context, dataList);
    }

    public void addName(City name) {
        dataList.add(name);
    }

    public static class NameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView textViewCityName;
        private NameClickListener nameClickListener;

        public NameViewHolder(@NonNull View itemView, NameClickListener nameClickListener) {
            super(itemView);

            textViewCityName = itemView.findViewById(R.id.textViewCity);
            this.nameClickListener = nameClickListener;
            //itemView.setOnClickListener(this);

            setTextViewCityName();


            textViewRemoveCity = itemView.findViewById(R.id.textViewRemoveFromFavorites);
            textViewRemoveCity.setOnClickListener(this);
        }

        public void setName(String name) {
            textViewCityName.setText(name);
        }

        @Override
        public void onClick(View v) {
            nameClickListener.onXIconClick(getAdapterPosition());
        }

        private final TextView textViewRemoveCity;

        public void setTextViewCityName() {
            textViewCityName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nameClickListener.onNameClick(getAdapterPosition());
                }
            });
        }
    }
}
