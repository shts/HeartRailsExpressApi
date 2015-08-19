package jp.shts.android.api.heartrailsexpressapi.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import jp.shts.android.api.heartrailsexpressapi.HeartRailsExpressApiClient;
import jp.shts.android.api.heartrailsexpressapi.HeartRailsExpressApiResponseHandler;
import jp.shts.android.api.heartrailsexpressapi.Station;

public class MainActivity extends Activity {

    private ListView listView;
    private EditText nameEditText;
    private EditText lineEditText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_sample);

        listView = (ListView) findViewById(R.id.list);
        nameEditText = (EditText) findViewById(R.id.name_editor);
        lineEditText = (EditText) findViewById(R.id.line_editor);
        button = (Button) findViewById(R.id.search_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchNameText = nameEditText.getText().toString();
                String searchLineText = lineEditText.getText().toString();

                HeartRailsExpressApiClient.Request request
                        = new HeartRailsExpressApiClient.Request(searchLineText, searchNameText, "");
                HeartRailsExpressApiClient.get(
                        getApplicationContext(), request, new HeartRailsExpressApiResponseHandler() {
                    @Override
                    public void onFinishRequest(ArrayList<Station> stations) {
                        if (stations == null || stations.isEmpty()) {
                            return;
                        }
                        setupListAdapter(stations);
                    }
                });
            }
        });
    }

    private void setupListAdapter(ArrayList<Station> stations) {
        listView.setAdapter(new BindableAdapter.StationAdapter(
                getApplicationContext(), stations));
    }
}
