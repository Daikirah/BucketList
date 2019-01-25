package com.example.joshu.bucketlist;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;


@Entity (tableName = "bucketlist")
public class BucketListItem implements Parcelable {

    @PrimaryKey (autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "bucket_title")
    private String mBucketTitle;

    @ColumnInfo(name = "bucket_description")
    private String mBucketDescription;

    @ColumnInfo(name = "striked")
    private boolean isStriked = false;

    public BucketListItem(){
    }

    public BucketListItem (String bucketTitle, String bucketDescription){
        this.mBucketTitle = bucketTitle;
        this.mBucketDescription = bucketDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStriked() {
        return isStriked;
    }

    public void setStriked(boolean striked) {
        isStriked = striked;
    }

    public String getBucketTitle() {
        return mBucketTitle;
    }

    public void setBucketTitle(String mBucketTitle) {
        this.mBucketTitle = mBucketTitle;
    }

    public String getBucketDescription() {
        return mBucketDescription;
    }

    public void setBucketDescription(String mBucketDescription) {
        this.mBucketDescription = mBucketDescription;
    }

    protected BucketListItem(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.mBucketTitle = in.readString();
        this.mBucketDescription = in.readString();
        this.isStriked = in.readByte() != 0;
    }

    public static final Creator<BucketListItem> CREATOR = new Creator<BucketListItem>() {
        @Override
        public BucketListItem createFromParcel(Parcel in) {
            return new BucketListItem(in);
        }

        @Override
        public BucketListItem[] newArray(int size) {
            return new BucketListItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.mBucketTitle);
        dest.writeString(this.mBucketDescription);
        dest.writeByte((byte) (this.isStriked ? 1:0));
    }
}
