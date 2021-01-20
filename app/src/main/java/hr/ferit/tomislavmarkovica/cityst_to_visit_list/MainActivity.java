package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOError;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button getBtn;
    private TextView result;
    private OkHttpClient client;

    private DataFetch dataFetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.result);


        setBtn();

        this.dataFetch = new DataFetch(this);
    }

    private void setBtn() {
        getBtn = (Button) findViewById(R.id.getBtn);

        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataFetch.showData();
            }
        });
    }
}