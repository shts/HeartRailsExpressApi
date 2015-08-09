package jp.shts.android.api.heartrailsexpressapi;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

class ResponseParser {

    private static final String TAG = ResponseParser.class.getSimpleName();

    private ResponseParser() {}

    public static final ArrayList<Station> parse(byte[] responseBody) {
        try {
            return parse(new JSONObject(new String(responseBody, "UTF-8")));
        } catch (JSONException e) {
            Log.e(TAG, "failed to parse JSONObject", e);
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "failed to encode String from byte array", e);
        }
        return null;
    }

    private static final ArrayList<Station> parse(JSONObject jsonObject) throws JSONException {
        ArrayList<Station> stations = new ArrayList<Station>();

        JSONObject responseObject = jsonObject.getJSONObject("response");
        JSONArray stationArray = responseObject.getJSONArray("station");
        if (stationArray == null) {
            return null;
        }
        final int N = stationArray.length();
        for (int i = 0; i < N; i++) {
            JSONObject stationObject = (JSONObject) stationArray.get(i);
            String name = stationObject.getString("name");
            String prev = stationObject.getString("prev");
            String next = stationObject.getString("next");
            String x = stationObject.getString("x");
            String y = stationObject.getString("y");
            String postal = stationObject.getString("postal");
            String prefecture = stationObject.getString("prefecture");
            String line = stationObject.getString("line");
            stations.add(new Station(name, prev, next, x, y, postal, prefecture, line));
        }

        return stations;
    }
}
