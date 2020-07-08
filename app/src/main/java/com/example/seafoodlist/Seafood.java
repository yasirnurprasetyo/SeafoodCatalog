package com.example.seafoodlist;
import android.os.Parcel;
import android.os.Parcelable;
public class Seafood implements Parcelable {
    private String id,title, instruction, image;
    protected Seafood(Parcel in) {
        id = in.readString();
        title = in.readString();
        instruction = in.readString();
        image = in.readString();
    }
    public static final Creator<Seafood> CREATOR = new Creator<Seafood>() {
        @Override
        public Seafood createFromParcel(Parcel in) {
            return new Seafood(in);
        }
        @Override
        public Seafood[] newArray(int size) {
            return new Seafood[size];
        }
    };
    public Seafood() { }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitlee(String title) {
        this.title = title;
    }
    public String setTitle(String title) {
        return title;
    }
    public String getInstruction() {
        return instruction;
    }
    public String setInstruction(String instruction) {
        return instruction;
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
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(instruction);
        dest.writeString(image);
    }
}
