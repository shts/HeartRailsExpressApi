package jp.shts.android.api.heartrailsexpressapi;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;

public class HeartRailsExpressApiClient {

    private HeartRailsExpressApiClient() {}

    private static final String TAG = HeartRailsExpressApiClient.class.getSimpleName();
    private static final AsyncHttpClient CLIENT = new AsyncHttpClient();

    public static final boolean get(final Context context, final Request request,
                                    final HeartRailsExpressApiResponseHandler responseHandler) {
        if (responseHandler == null) {
            Log.e(TAG, "cannot execute because of responseHandler is null.");
            return false;
        }

        if (request == null || request.isEmpty()) {
            Log.e(TAG, "cannot execute because of request is empty");
            return false;
        }

        if (!NetworkUtils.isConnected(context) || NetworkUtils.isAirplaneModeOn(context)) {
            Log.e(TAG, "cannot execute because of network disconnected.");
            return false;
        }

        CLIENT.get(request.getUrl(), responseHandler);

        return true;
    }

    public static class Request implements Parcelable {

        private static final String GENERAL_URL = "http://express.heartrails.com/api/json?method=getStations";
        private static final String PARAM_URL_LINE = "&line=";
        private static final String PARAM_URL_NAME = "&name=";
        private static final String PARAM_URL_PREFECTURE = "&prefecture=";

        public String line;
        public String name;
        public String prefecture;

        public Request() {}

        public Request(String line, String name, String prefecture) {
            this.line = line;
            this.name = name;
            this.prefecture = prefecture;
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(line)
                    && TextUtils.isEmpty(name)
                    && TextUtils.isEmpty(prefecture);
        }

        public String getUrl() {
            StringBuilder sb = new StringBuilder();
            sb.append(GENERAL_URL);

            if (!TextUtils.isEmpty(name)) {
                sb.append(PARAM_URL_NAME).append(name);
            }
            if (!TextUtils.isEmpty(line)) {
                sb.append(PARAM_URL_LINE).append(line);
            }
            if (!TextUtils.isEmpty(prefecture)) {
                sb.append(PARAM_URL_PREFECTURE).append(prefecture);
            }
            final String url = sb.toString();
            Log.i(TAG, "url(" + url + ")");
            return url;
        }


        protected Request(Parcel in) {
            line = in.readString();
            name = in.readString();
            prefecture = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(line);
            dest.writeString(name);
            dest.writeString(prefecture);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<Request> CREATOR = new Parcelable.Creator<Request>() {
            @Override
            public Request createFromParcel(Parcel in) {
                return new Request(in);
            }

            @Override
            public Request[] newArray(int size) {
                return new Request[size];
            }
        };
    }
}
