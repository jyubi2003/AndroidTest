package com.dennou_lab.ordering;

/**
 * Created by yoshida.hiro-02 on 2015/08/05.
 */
import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    public String id;
    public String name;

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(Parcel in){
        this.id = in.readString();
        this.name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(id);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }//FileDescriptor未使用の場合は0

    // 今は定型文という認識で
    public static final Creator<Item> CREATOR = new Creator<Item>() {
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}

