package com.example.joshu.bucketlist;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EditBucketListItem extends AppCompatActivity {


    TextInputEditText mTitle, mDescription;
    String titleString, descriptionString;
    BucketListItem updatedBucketList;
    BucketListViewModel mBucketListViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bucket_list_item);

        final BucketListItem bucketListItem = getIntent().getParcelableExtra(MainActivity.EDIT_ITEM);
        mTitle = findViewById(R.id.edit_item_title_edit);
        mDescription = findViewById(R.id.edit_item_description_edit);

        mTitle.setText(bucketListItem.getBucketTitle());
        mDescription.setText(bucketListItem.getBucketDescription());

        FloatingActionButton fab = findViewById(R.id.edit_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    updatedBucketList = bucketListItem;
                    updatedBucketList.setBucketTitle(mTitle.getText().toString());
                    updatedBucketList.setBucketDescription(mDescription.getText().toString());
                    mBucketListViewModel = new BucketListViewModel(getApplicationContext());
                    mBucketListViewModel.update(updatedBucketList);
                    Intent intent = new Intent(EditBucketListItem.this, MainActivity.class);
                    startActivity(intent);
            }
        });
    }
}
