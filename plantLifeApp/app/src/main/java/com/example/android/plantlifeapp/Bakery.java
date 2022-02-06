package com.example.android.plantlifeapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Bakery implements Parcelable {
    private String name;
    private String description;
    private String image;

    public Bakery(String name, String description, String image){
       this.name=name;
       this.description=description;
       this.image=image;
    }


    protected Bakery(Parcel in) {
        name = in.readString();
        description = in.readString();
        image = in.readString();
    }

    public static final Creator<Bakery> CREATOR = new Creator<Bakery>() {
        @Override
        public Bakery createFromParcel(Parcel in) {
            return new Bakery(in);
        }

        @Override
        public Bakery[] newArray(int size) {
            return new Bakery[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(description);
            dest.writeString(image);
    }
}
