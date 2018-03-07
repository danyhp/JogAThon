package alexgochi.jogathon;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ASUS on 06-Mar-18.
 */

public class Runner implements Parcelable {
    // Attribute
    int runnerID;
    int lapDonation;
    int lapCount = 0;

    // Constructor

    public Runner(int runnerID, int lapDonation) {
        this.runnerID = runnerID;
        this.lapDonation = lapDonation;
    }

    private Runner(Parcel in) {
        runnerID = in.readInt();
        lapDonation = in.readInt();
        lapCount = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(runnerID);
        parcel.writeInt(lapDonation);
        parcel.writeInt(lapCount);
    }

    public static final Parcelable.Creator<Runner> CREATOR = new Parcelable.Creator<Runner>() {
        public Runner createFromParcel(Parcel in) {
            return new Runner(in);
        }

        @Override
        public Runner[] newArray(int size) {
            return new Runner[size];
        }

    };
}
