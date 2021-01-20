package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static hr.ferit.tomislavmarkovica.cityst_to_visit_list.MainActivity.DATA_FETCH;

public class ExploreActivity extends AppCompatActivity {

    private Button buttonFavorites;
    private Button buttonLike;
    private Button buttonRandom;

    //
    private DataFetch dataFetch;
    private TextView textView;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        // fix this
        textView = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();

        MyParcelable myParcelableObject = (MyParcelable) intent.getParcelableExtra(MainActivity.DATA_FETCH);

        //this.dataFetch = myParcelableObject.getDataFetch();
        //int n = this.dataFetch.getNumberOfElements();

        int n = myParcelableObject.getNumberOfElements();


        textView.setText(String.valueOf(n));
        // ******

        setButtonFavoirtes();
        setButtonLike();
        setButtonRandom();
    }

    private void setButtonFavoirtes() {
        buttonFavorites = (Button) findViewById(R.id.buttonFavorites);

        buttonFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExploreActivity.this, FavoritesActivity.class));
            }
        });
    }

    private void setButtonLike() {
        buttonLike = (Button) findViewById(R.id.buttonLike);

        buttonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // not implemented
                textView.setText(String.valueOf(dataFetch.getNumberOfElements())); // remove this
            }
        });
    }

    private void setButtonRandom() {
        buttonRandom = (Button) findViewById(R.id.buttonRandom);

        buttonRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomNumber = getRandomNumberFrom1To3();
                setN(randomNumber);
            }
        });
    }

    private int getRandomNumberFrom1To3() {
        Random rn = new Random();
        int randomNumber = rn.nextInt(3) + 1;
        return randomNumber;
    }

    int n=0;
    private void showN() {
        Toast.makeText(getBaseContext(), String.valueOf(n) , Toast.LENGTH_SHORT ).show();
    }

    private void setN(int n) {
        this.n = n;
        showN();
    }
}