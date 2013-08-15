package com.kurzandroidu.zakladyandroidu;

public class ViewType {

    private String  mName;
    private String  mDescription;
    private boolean mAdvanced;
    private int     mIconId;

    public ViewType(String name, String desc, boolean advanced, int iconId) {
        mName = name;
        mDescription = desc;
        mAdvanced = advanced;
        mIconId = iconId;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public boolean isAdvanced() {
        return mAdvanced;
    }

    public void setAdvanced(boolean mAdvanced) {
        this.mAdvanced = mAdvanced;
    }

    public int getIconId() {
        return mIconId;
    }

    public void setIconId(int mIconId) {
        this.mIconId = mIconId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }
}
