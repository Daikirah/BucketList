package com.example.joshu.bucketlist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BucketDao {

    @Query("SELECT * FROM bucketlist")
    public LiveData<List<BucketListItem>> getAllBucketItems();

    @Insert
    public void insertBucketItem (BucketListItem bucketListItem);

    @Delete
    public void deleteBucketItem (BucketListItem bucketListItem);

    @Update
    public void updateBucketItem (BucketListItem bucketListItem);


}
