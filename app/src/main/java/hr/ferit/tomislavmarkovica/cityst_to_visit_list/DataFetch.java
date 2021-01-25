package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        client = new OkHttpClient();
        doInBackground();
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

    private String link = "";
    private City city;
    private String result = "";
    private boolean requestProcessed = false;

    public void connectAPI() {

        link = "https://wft-geo-db.p.rapidapi.com/v1/geo/cities/";
        link += String.valueOf(getRandomNumber());
        //link += String.valueOf(408);
        Log.i("tag", "connectAPI to: " + link);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        if (this.city==null){
            Log.i("tag", "doInBackground()");
            makeRequest();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    public City getCity() { Log.i("tag", "getCity()");
        if (this.city != null)
        {
            City temp = this.city;
            this.city = null;
            doInBackground();
            return temp;
        }
        else
        {

            return null;
        }
    }

    private int getRandomNumber() {
        Random rn = new Random();
        int randomNumber = rn.nextInt(263000) + 1;
        return randomNumber;
    }

    private String pullJSONFromThisString(String input) {
        String output = "";
        for (int i=8; i<input.length()-1; i++) {
            output += input.charAt(i);
        }
        return output;
    }

    private Request request;

    private void makeRequest() {
        //OkHttpClient client = new OkHttpClient();

        String link = "https://wft-geo-db.p.rapidapi.com/v1/geo/cities/";
        link += String.valueOf(getRandomNumber());
        //link += String.valueOf(408);

        request = new Request.Builder()
                .url(link)
                .get()
                .addHeader("x-rapidapi-key", "11ff87a06emshe34071c564f77fdp104cbcjsncd878d29d813")
                .addHeader("x-rapidapi-host", "wft-geo-db.p.rapidapi.com")
                .build();

        makeCall();
    }

    private void makeCall() { Log.i("tag", "makeCall()");

        Response response = null;
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("tag", "response failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("tag", "response succesful");
                getJSON(response);
                requestProcessed = true;
            }
        });
    }

    private void getJSON(Response response) { Log.i("tag", "getJSON()");
        try
        {
            InputStream inputStream = response.body().byteStream(); // to return it you have to change the method's signature. From String to InputStream

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            result = "";
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result = result + line;
            }

            if (result.contains("data") == false) {
                Thread.sleep(1000);
                Log.i("tag", "NIJE dohvacen grad");
                makeRequest();
                return;
            } else if (result.contains("data") == true) {
                JSONObject JO = new JSONObject(pullJSONFromThisString(result));
                Log.i("tag", JO.toString());

                City tempCity = makeCityFromJSON(JO);


                city = tempCity;

                Log.i("tag", "Dohvacen je grad");
                if (city != null)
                Log.i("tag", city.toString());
                doInBackground();
            }
        }
        catch (IOException e) {
            e.printStackTrace();  Log.i("tag", "*IOException in getJSON()");
        }
        catch (JSONException e) {
            e.printStackTrace();  Log.i("tag", "*JSONException in getJSON()");
        }
        catch (InterruptedException e) {
            e.printStackTrace();  Log.i("tag", "*InterruptedException in getJSON()");
        }
    }

    private City makeCityFromJSON(JSONObject JO) {
        City tempCity = null;
        tempCity = new City(
                getIntAttribute("id", JO),
                getStringAttribute("wikiDataId", JO),
                getStringAttribute("type", JO),
                getStringAttribute("city", JO),
                getStringAttribute("name", JO),
                getStringAttribute("country", JO),
                getStringAttribute("countryCode", JO),
                getStringAttribute("region", JO),
                getStringAttribute("regionCode", JO),
                getIntAttribute("elevationMeters", JO),
                getIntAttribute("latitude", JO),
                getIntAttribute("longitude", JO),
                getIntAttribute("population", JO),
                getStringAttribute("timezone", JO)
        );
        return tempCity;
    }

    private String getStringAttribute(String attributeName, JSONObject JO) {
        String temp = "/";
        try {
            if (JO.getString(attributeName) != "" || !JO.isNull(attributeName)) temp = JO.getString(attributeName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return temp;
    }

    private int getIntAttribute(String attributeName, JSONObject JO) {
        int temp = 0;
        try {
            if (!JO.isNull(attributeName)) temp = JO.getInt(attributeName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
