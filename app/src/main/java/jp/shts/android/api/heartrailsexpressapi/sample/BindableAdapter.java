package jp.shts.android.api.heartrailsexpressapi.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import jp.shts.android.api.heartrailsexpressapi.Station;

/**
 * Abstract adapter for ListView or GridView.
 * @param <T>
 */
public abstract class BindableAdapter<T> extends ArrayAdapter<T> {
    private LayoutInflater inflater;

    public BindableAdapter(Context context, List<T> list) {
        super(context, -1, list);
        setup(context);
    }

    private void setup(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public final View getView(int position, View view, ViewGroup container) {
        if (view == null) {
            view = newView(inflater, position, container);
            if (view == null) {
                throw new IllegalStateException("newView result must not be null.");
            }
        }
        bindView(getItem(position), position, view);
        return view;
    }

    public abstract View newView(LayoutInflater inflater, int position, ViewGroup container);

    public abstract void bindView(T item, int position, View view);

    public static class StationAdapter extends BindableAdapter<Station> {

        private static class ViewHolder {
            TextView nameTextView;
            TextView lineTextView;
            TextView lonTextView;
            TextView latTextView;
            ViewHolder(View view) {
                nameTextView = (TextView) view.findViewById(R.id.station_name);
                lineTextView = (TextView) view.findViewById(R.id.station_line);
                lonTextView = (TextView) view.findViewById(R.id.station_lon);
                latTextView = (TextView) view.findViewById(R.id.station_lat);
            }
        }

        public StationAdapter(Context context, List<Station> list) {
            super(context, list);
        }

        @Override
        public View newView(LayoutInflater inflater, int position, ViewGroup container) {
            View view = inflater.inflate(R.layout.list_item_station, container, false);
            ViewHolder holder = new ViewHolder(view);
            view.setTag(holder);
            return view;
        }

        @Override
        public void bindView(Station item, int position, View view) {
            final ViewHolder holder = (ViewHolder) view.getTag();
            holder.nameTextView.setText(item.name);
            holder.lineTextView.setText(item.line);
            holder.lonTextView.setText(item.x);
            holder.latTextView.setText(item.y);
        }
    }
}