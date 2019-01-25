package com.example.joshu.bucketlist;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BucketRepository {

    private BucketDatabase mBucketDatabase;
    private BucketDao mBucketDao;
    private LiveData<List<BucketListItem>> mBucketList;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public BucketRepository(Context context) {
        mBucketDatabase = BucketDatabase.getInstance(context);
        mBucketDao = mBucketDatabase.bucketDao();
        mBucketList = mBucketDao.getAllBucketItems();
    }

    public LiveData<List<BucketListItem>> getAllBucketItems() {
        return mBucketList;
    }


    public void insert (final BucketListItem bucketListItem){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mBucketDao.insertBucketItem(bucketListItem);
            }
        });
    }

    public void delete (final BucketListItem bucketListItem){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mBucketDao.deleteBucketItem(bucketListItem);
            }
        });
    }

    public void update (final BucketListItem bucketListItem){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mBucketDao.updateBucketItem(bucketListItem);
            }
        });
    }


}
