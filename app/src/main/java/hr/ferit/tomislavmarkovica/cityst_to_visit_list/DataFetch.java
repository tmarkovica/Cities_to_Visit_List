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

public class DataFetch extends AsyncTask<Void,Void,Void> implements Parcelable {
    private String data = "";
    private List<City> cities = new ArrayList<>();
    private Response response;

    private OkHttpClient client;
    private Activity mActivity;
    private TextView result;

    public DataFetch(Activity activity) {
        this.mActivity = activity;
        this.result = (TextView) mActivity.findViewById(R.id.result);

        connectAPI();
    }

    public void connectAPI() {
        client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://wft-geo-db.p.rapidapi.com/v1/geo/cities") // https://wft-geo-db.p.rapidapi.com/v1/geo/cities/Q60
                .get()
                .addHeader("x-rapidapi-key", "11ff87a06emshe34071c564f77fdp104cbcjsncd878d29d813")
                .addHeader("x-rapidapi-host", "wft-geo-db.p.rapidapi.com")
                .build();

        //Response response = client.newCall(request).execute();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result.setText("Failure");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setResponse(response);
                        doInBackground(); // on successful response calls doInBackground() method
                        /*
                        try {
                            result.setText(response.body().string());
                            //setResponse(response);
                        }
                        catch (IOException ioError) {
                            result.setText("Error during get body");
                        }*/
                    }
                });
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
                /*
                City tempCity = new City("","","","");

                if(tempJO.isNull("id")  || tempJO.get("id") == "") tempCity.set_id("id: 0000");
                else tempCity.set_id("id: " + tempJO.get("id"));

                if(tempJO.isNull("wikiDataId")  || tempJO.get("wikiDataId") == "") tempCity.set_wikiDataId("Unknown");
                else tempCity.set_wikiDataId((String) tempJO.get("wikiDataId"));

                String singleParsed = tempJO.get("id") + "\n"
                                    + tempJO.get("wikiDataId") + "\n"
                                    + tempJO.get("name") + "\n"
                                    + tempJO.get("country") + "\n";
                 */
                /*
                result.setText(
                        tempJO.getInt("id")+ "\n" +
                        tempJO.get("wikiDataId").toString()+ "\n" +
                        tempJO.get("type").toString()+ "\n" +
                        tempJO.get("city").toString()+ "\n" +
                        tempJO.get("name").toString()+ "\n" +
                        tempJO.get("country").toString()+ "\n" +
                        tempJO.get("countryCode").toString()+ "\n" +
                        tempJO.get("region").toString()+ "\n" +
                        tempJO.get("regionCode").toString()+ "\n" +
                        tempJO.getDouble("latitude")+ "\n" +
                        tempJO.getDouble("longitude")+ "\n"
                );*/

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

    public void showData() {
        //result.setText(cities.toString()); // show all

        int n = cities.size();
        //result.setText(String.valueOf(n));

        //int randomNumber = getRandomNumberFrom1To3();
    }

    public int getNumberOfElements() { return 0; } //cities.size()

/*
    private int getRandomNumberFrom1To3() {
        Random rn = new Random();
        int randomNumber = rn.nextInt(3) + 1;
        return randomNumber;
    }
*/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
