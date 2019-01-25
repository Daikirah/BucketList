package com.example.joshu.bucketlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

public class BucketListViewModel extends ViewModel {

    private BucketRepository mRepository;
    private LiveData<List<BucketListItem>> mBucketList;

    public BucketListViewModel (Context context){
        mRepository = new BucketRepository(context);
        mBucketList = mRepository.getAllBucketItems();
    }

    public LiveData<List<BucketListItem>> getBucketList(){
        return mBucketList;
    }

    public void insert (BucketListItem bucketListItem){
        mRepository.insert(bucketListItem);
    }
    public void update (BucketListItem bucketListItem){
        mRepository.update(bucketListItem);
    }
    public void delete (BucketListItem bucketListItem){
        mRepository.delete(bucketListItem);
    }




}
