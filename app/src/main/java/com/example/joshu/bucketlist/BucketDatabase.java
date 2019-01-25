package com.example.joshu.bucketlist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {BucketListItem.class}, version = 1)
public abstract class BucketDatabase extends RoomDatabase {

    public abstract BucketDao bucketDao();
    private final static String NAME_DATABASE = "bucket_db";

    private static BucketDatabase bucketDatabase;
    public static BucketDatabase getInstance (Context context){

        if (bucketDatabase == null){
            bucketDatabase = Room.databaseBuilder(context, BucketDatabase.class, NAME_DATABASE).build();
        }

        return bucketDatabase;
    }


}
