package com.example.android.plantlifeapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Botany implements Parcelable {
    private String name;
    private  String description;
    private  String image;
    private  String origin ;
    private  String ScientificName;
    private  String species;
    private String type;

    public Botany(String name, String description, String image, String origin, String scientificName, String species, String type) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.origin = origin;
        this.ScientificName = scientificName;
        this.species = species;
        this.type = type;
    }

    protected Botany(Parcel in) {
        name = in.readString();
        description = in.readString();
        image = in.readString();
        origin = in.readString();
        ScientificName = in.readString();
        species = in.readString();
        type = in.readString();
    }

    public static final Creator<Botany> CREATOR = new Creator<Botany>() {
        @Override
        public Botany createFromParcel(Parcel in) {
            return new Botany(in);
        }

        @Override
        public Botany[] newArray(int size) {
            return new Botany[size];
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getScientificName() {
        return ScientificName;
    }

    public void setScientificName(String scientificName) {
        ScientificName = scientificName;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        dest.writeString(origin);
        dest.writeString(ScientificName);
        dest.writeString(species);
        dest.writeString(type);
    }
}
