package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class IOData_Favorites {


    private static String FILE_NAME = "Favorites.json";

    public static void save(Context context, List<City> cities) {
        FileOutputStream fos = null;
        String text; // = makeStringOf(cities);

        if (cities.isEmpty())
            text = "";
        else
            text = makeStringOf(cities);

        Log.i("tag", "cities.size() = " + String.valueOf(cities.size()));

        try {
            fos = context.openFileOutput(FILE_NAME, MODE_PRIVATE);

            fos.write(text.getBytes());

            Toast.makeText(context, "Saved to " + context.getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace(); Log.i("tag", "save : FileNotFoundException thrown");
        } catch (IOException e) {
            e.printStackTrace(); Log.i("tag", "save : IOException thrown");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace(); Log.i("tag", "save : IOException thrown");
                }
            }
        }
    }

    private static String makeStringOf(List<City> cities) {
        String text = "{\"data\":" + "[";

        for (int i=0; i < cities.size(); i++) {
            text = text + cities.get(i).getJSONObject().toString();

            if (i == 0 && cities.size() > 1) text += ","; // if loop is on first element and there is more than one elements add ","
            else if (i < cities.size()-1)
                text += ",";
        }

        return text + "]" + "}";
    }

    public static void saveCity(Context context, City city) {
        List<City> cities = load(context);
        if (addCityIfUnique(cities, city))
        {
            Toast.makeText(context, "Saved", Toast.LENGTH_LONG).show();
            save(context, cities);
        }
    }

    public static boolean addCityIfUnique(List<City> cities, City city) {
        for (int i=0; i<cities.size(); i++) {
            if (cities.get(i).get_id() == city.get_id()) {
                Log.i("tag", "City already in the list");
                return false;
            }
        }
        cities.add(city);
        return true;
    }

    public static void emptyFile(Context context) {
        List<City> cities = new ArrayList<>();
        save(context, cities);
    }

    public static List<City> load(Context context) {

        String data = "";
        List<City> cities = new ArrayList();
        try {
            FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                data = data + line;
            }
            Log.i("tag", "data: " + data);

            if(data != "") {
                JSONObject JO = new JSONObject(data);
                JSONArray JA = JO.getJSONArray("data");

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
                            tempJO.getInt("elevationMeters"),
                            tempJO.getDouble("latitude"),
                            tempJO.getDouble("longitude"),
                            tempJO.getInt("population"),
                            tempJO.get("timezone").toString()
                    );
                    cities.add(tempCity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); Log.i("tag", "catched IOException");
        } catch (JSONException e) {
            e.printStackTrace(); Log.i("tag", "catched JSONException");
        } finally {
            return cities;
        }
    }
}
