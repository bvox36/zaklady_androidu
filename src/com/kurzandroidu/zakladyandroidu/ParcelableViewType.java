package com.kurzandroidu.zakladyandroidu;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableViewType implements Parcelable {

    public String  mName;
    public String  mDescription;
    public boolean mAdvanced;
    public int     mIconId;

    public ParcelableViewType(String name, String desc, boolean advanced,
            int iconId) {
        mName = name;
        mDescription = desc;
        mAdvanced = advanced;
        mIconId = iconId;
    }

    public ParcelableViewType(Parcel in) {
        String[] strings = new String[2];
        boolean[] bools = new boolean[1];
        in.readStringArray(strings);
        in.readBooleanArray(bools);
        mName = strings[0];
        mDescription = strings[1];
        mAdvanced = bools[0];
        mIconId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] { mName, mDescription });
        dest.writeBooleanArray(new boolean[] { mAdvanced });
        dest.writeInt(mIconId);
    }

    public static final Creator<ParcelableViewType> CREATOR = new Creator<ParcelableViewType>() {
                                                                public ParcelableViewType createFromParcel(
                                                                        Parcel in) {
                                                                    return new ParcelableViewType(
                                                                            in);
                                                                }

                                                                public ParcelableViewType[] newArray(
                                                                        int size) {
                                                                    return new ParcelableViewType[size];
                                                                }
                                                            };
}
