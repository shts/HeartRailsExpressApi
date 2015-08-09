package jp.shts.android.api.heartrailsexpressapi;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;

public abstract class HeartRailsExpressApiResponseHandler extends AsyncHttpResponseHandler {

    private static final String TAG = HeartRailsExpressApiResponseHandler.class.getSimpleName();
    private static final HandlerThread THREAD = new HandlerThread(TAG + "-Thread");
    static {
        THREAD.start();
    }

    private static final Handler UI_THREAD_HANDLER = new Handler(Looper.getMainLooper());

    public HeartRailsExpressApiResponseHandler() {
        super(THREAD.getLooper());
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        final ArrayList<Station> stations = ResponseParser.parse(responseBody);
        UI_THREAD_HANDLER.post(new Runnable() {
            @Override
            public void run() {
                onFinishRequest(stations);
            }
        });
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        UI_THREAD_HANDLER.post(new Runnable() {
            @Override
            public void run() {
                onFinishRequest(null);
            }
        });
    }

    public abstract void onFinishRequest(ArrayList<Station> stations);
}
