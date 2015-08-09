package jp.shts.android.api.heartrailsexpressapi.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import jp.shts.android.api.heartrailsexpressapi.HeartRailsExpressApiClient;
import jp.shts.android.api.heartrailsexpressapi.HeartRailsExpressApiResponseHandler;
import jp.shts.android.api.heartrailsexpressapi.Station;

public class SampleActivity extends ActionBarActivity {

    private static final String TAG = SampleActivity.class.getSimpleName();

    private ListView listView;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_sample);

        listView = (ListView) findViewById(R.id.list);
        editText = (EditText) findViewById(R.id.editor);
        button = (Button) findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupListView(editText.getText().toString());
            }
        });
    }

    private void setupListView(String stationName) {

        HeartRailsExpressApiClient.Request request = new HeartRailsExpressApiClient.Request();
        request.name = TextUtils.isEmpty(stationName) ? "新宿" : stationName;
        HeartRailsExpressApiClient.get(getApplicationContext(), request, new HeartRailsExpressApiResponseHandler() {
            @Override
            public void onFinishRequest(ArrayList<Station> stations) {
                setupAdapter(stations);
            }
        });
    }

    private void setupAdapter(ArrayList<Station> stations) {
        if (stations == null || stations.isEmpty()) {
            Log.w(TAG, "stations is null");
            return;
        }
        for (Station s : stations) {
            if (s != null) {
                Log.d(TAG, s.toString());
            }
        }
        listView.setAdapter(
                new BindableAdapter.StationAdapter(getApplicationContext(), stations));
    }
}
