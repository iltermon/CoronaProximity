package com.scs.coronaproximity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.scs.coronaproximity.api.APIClient;
import com.scs.coronaproximity.api.APIInterface;
import com.scs.coronaproximity.api.CoronaData;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.scs.coronaproximity.api.APIClient.retrofit;

public class SearchActivity extends AppCompatActivity {
    private APIInterface apiInterface;

    // String json = "{\"data\":{\"02000\":{\"ags\":\"02000\",\"name\":\"Hamburg\",\"county\":\"SK Hamburg\",\"state\":\"Hamburg\",\"population\":1847253,\"cases\":77455,\"deaths\":1597,\"casesPerWeek\":163,\"deathsPerWeek\":0,\"stateAbbreviation\":\"HH\",\"recovered\":75349,\"weekIncidence\":8.823913129387257,\"casesPer100k\":4192.982769550245,\"delta\":{\"cases\":22,\"deaths\":0,\"recovered\":20}}},\"meta\":{\"source\":\"Robert Koch-Institut\",\"contact\":\"Marlon Lueckert (m.lueckert@me.com)\",\"info\":\"https://github.com/marlon360/rki-covid-api\",\"lastUpdate\":\"2021-07-04T00:00:00.000Z\",\"lastCheckedForUpdate\":\"2021-07-04T13:32:35.485Z\"}" ;
    //String json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView nameText;
        apiInterface = APIClient.getClient().create(APIInterface.class);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        nameText = findViewById(R.id.name);
        APIInterface scalarService = retrofit.create(APIInterface.class);
        Call<String> call = apiInterface.getHamburg();
        nameText.setText("karaca");

        // int thirdOpening = StringUtils.ordinalIndexOf(json, "{", 3);
        // int firstClosing = StringUtils.ordinalIndexOf(json, "}", 2) +1;
        // json = json.substring(thirdOpening, firstClosing);
        //nameText.setText(getObject().getName());

        call.enqueue(new Callback<String>() {
            @Override
            public void onFailure(Call<String> call, Throwable t) {

                t.printStackTrace();
                nameText.setText("kaan");
                call.cancel();
            }

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String responseString = response.body();
                    Log.d("TAG", responseString);
                }
            }

        });
    }
}