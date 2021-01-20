package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class MyParcelable implements Parcelable {

    /* everything below here is for implementing Parcelable */

    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        //out.writeInt(mData);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<MyParcelable> CREATOR = new Parcelable.Creator<MyParcelable>() {
        public MyParcelable createFromParcel(Parcel in) {
            return new MyParcelable(in);
        }

        public MyParcelable[] newArray(int size) {
            return new MyParcelable[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    public MyParcelable(Parcel in) {
        //mData = in.readInt();
    }

    // moj kod
    private DataFetch dataFetch = null;

    public MyParcelable() { }

    @SuppressLint("LongLogTag")
    public void setDataFetch(DataFetch dataFetch) {
        this.dataFetch = dataFetch;
        Log.i("MyParcealable", String.valueOf(this.dataFetch instanceof DataFetch));
    }

    public DataFetch getDataFetch() {
        Log.i("MyParcealable", String.valueOf(this.dataFetch instanceof DataFetch));
        return this.dataFetch;
    }

    int n=0;
    public void setN(int n) {
        this.n = n;
        Log.i("MyParcealable", String.valueOf(n));
    }

    public int getNumberOfElements() {
        Log.i("MyParcealable", String.valueOf(n));
        return n;
    }
}
