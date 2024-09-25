// CountEntry.java
package com.example.tasbeeh30;

import android.os.Parcel;
import android.os.Parcelable;

public class CountEntry implements Parcelable {
    private int count;
    private long timestamp;

    public CountEntry(int count, long timestamp) {
        this.count = count;
        this.timestamp = timestamp;
    }

    protected CountEntry(Parcel in) {
        count = in.readInt();
        timestamp = in.readLong();
    }

    public static final Creator<CountEntry> CREATOR = new Creator<CountEntry>() {
        @Override
        public CountEntry createFromParcel(Parcel in) {
            return new CountEntry(in);
        }

        @Override
        public CountEntry[] newArray(int size) {
            return new CountEntry[size];
        }
    };

    public int getCount() {
        return count;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Count: " + count + ", Timestamp: " + timestamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(count);
        dest.writeLong(timestamp);
    }
}
