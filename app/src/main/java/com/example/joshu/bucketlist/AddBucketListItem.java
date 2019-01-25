package com.example.joshu.bucketlist;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

public class AddBucketListItem extends AppCompatActivity {

    TextInputEditText mTitle, mDescription;
    String titleString, descriptionString;
    BucketListViewModel mBucketListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bucket_list_item);

        mTitle = findViewById(R.id.add_item_title_edit);
        mDescription = findViewById(R.id.add_item_description_edit);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BucketListItem bucketListItem;
                titleString = mTitle.getText().toString();
                descriptionString = mDescription.getText().toString();
                if (titleString.isEmpty()){
                    Snackbar.make(v, "Fill in a title!", Snackbar.LENGTH_SHORT).show();
                }
                else if (descriptionString.isEmpty()){
                    Snackbar.make(v, "Fill in a title!", Snackbar.LENGTH_SHORT).show();
                }
                else {
                    bucketListItem = new BucketListItem(titleString, descriptionString);
                    mBucketListViewModel = new BucketListViewModel(getApplicationContext());
                    mBucketListViewModel.insert(bucketListItem);
                    Intent intent = new Intent(AddBucketListItem.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });




    }

}
