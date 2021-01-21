package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataFetch extends AsyncTask<Void,Void,Void> {

    // static variable single_instance of type Singleton
    private static DataFetch single_instance = null;

    // private constructor restricted to this class itself
    private DataFetch() {
        connectAPI();
    }

    // static method to create instance of Singleton class
    public static DataFetch getInstance()
    {
        if (single_instance == null)
            single_instance = new DataFetch();

        return single_instance;
    }

    private String data = "";
    private List<City> cities = new ArrayList<>();

    private Response response;
    private OkHttpClient client;

    public void connectAPI() {
        client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://wft-geo-db.p.rapidapi.com/v1/geo/cities") // https://wft-geo-db.p.rapidapi.com/v1/geo/cities/Q60
                .get()
                .addHeader("x-rapidapi-key", "11ff87a06emshe34071c564f77fdp104cbcjsncd878d29d813")
                .addHeader("x-rapidapi-host", "wft-geo-db.p.rapidapi.com")
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // do nothing
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                setResponse(response);
                doInBackground();
            }
        });
    }

    private void setResponse(Response response) {
        this.response = response;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            InputStream inputStream = response.body().byteStream(); // to return it you have to change the method's signature. From String to InputStream

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while(line!=null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject JO = new JSONObject(data);
            JSONArray JA = JO.getJSONArray("data"); // "data" "type" "properties"

            for (int i = 0; i < JA.length(); i++)
            {
                JSONObject tempJO = JA.getJSONObject(i);

                City tempCity = new City(
                        tempJO.getInt("id"),
                        tempJO.get("wikiDataId").toString(),
                        tempJO.get("type").toString(),
                        tempJO.get("city").toString(),
                        tempJO.get("name").toString(),
                        tempJO.get("country").toString(),
                        tempJO.get("countryCode").toString(),
                        tempJO.get("region").toString(),
                        tempJO.get("regionCode").toString(),
                        tempJO.getDouble("latitude"),
                        tempJO.getDouble("longitude")
                );

                cities.add(tempCity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }


    public String getData() { return data; } //cities.size()

    public int getNumberOfCities() {
        return cities.size();
    }

    public City getCityAt(int position) {
        return cities.get(position);
    }
}
