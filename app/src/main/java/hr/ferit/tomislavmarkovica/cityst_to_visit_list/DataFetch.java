package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

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
                .url("https://wft-geo-db.p.rapidapi.com/v1/geo/cities")
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
                            //result.setText(response.body().string());
                            setResponse(response);
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
            JSONArray JA = JO.getJSONArray("data");

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

                City tempCity = new City(
                        tempJO.get("id").toString(),
                        tempJO.get("wikiDataId").toString(),
                        tempJO.get("type").toString(),
                        tempJO.get("city").toString(),
                        tempJO.get("name").toString(),
                        tempJO.get("country").toString(),
                        tempJO.get("countryCode").toString(),
                        tempJO.get("region").toString(),
                        tempJO.get("regionCode").toString(),
                        tempJO.get("elevationMeters"),
                        tempJO.get("latitude"),
                        tempJO.get("longitude"),
                        tempJO.get("population"),
                        tempJO.get("timezone").toString(),
                        tempJO.get("deleted").toString()
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
        //result.setText(cities.get(0).toString());
        result.setText(cities.toString());
        //result.setText(data);
    }
}
