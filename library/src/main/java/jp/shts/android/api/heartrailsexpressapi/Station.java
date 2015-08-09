package jp.shts.android.api.heartrailsexpressapi;

import android.os.Parcel;
import android.os.Parcelable;

public class Station implements Parcelable {
    public String name;

    public String prev;

    public String next;

    /** longitude */
    public String x;

    /** latitude */
    public String y;

    public String postal;

    public String prefecture;

    public String line;

    public Station(String name, String prev, String next,
                   String x, String y, String postal,
                   String prefecture, String line) {
        this.name = name;
        this.prev = prev;
        this.next = next;
        this.x = x;
        this.y = y;
        this.postal = postal;
        this.prefecture = prefecture;
        this.line = line;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Station { ");
        sb.append("name(").append(name).append(") ");
        sb.append("prev(").append(prev).append(") ");
        sb.append("next(").append(next).append(") ");
        sb.append("x(").append(x).append(") ");
        sb.append("y(").append(y).append(") ");
        sb.append("postal(").append(postal).append(") ");
        sb.append("prefecture(").append(prefecture).append(") ");
        sb.append("line(").append(line).append(") ");
        sb.append(" }");
        return sb.toString();
    }

    protected Station(Parcel in) {
        name = in.readString();
        prev = in.readString();
        next = in.readString();
        x = in.readString();
        y = in.readString();
        postal = in.readString();
        prefecture = in.readString();
        line = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(prev);
        dest.writeString(next);
        dest.writeString(x);
        dest.writeString(y);
        dest.writeString(postal);
        dest.writeString(prefecture);
        dest.writeString(line);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Station> CREATOR = new Parcelable.Creator<Station>() {
        @Override
        public Station createFromParcel(Parcel in) {
            return new Station(in);
        }

        @Override
        public Station[] newArray(int size) {
            return new Station[size];
        }
    };
}