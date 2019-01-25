package com.example.joshu.bucketlist;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class BucketListAdapter extends RecyclerView.Adapter<BucketListAdapter.ViewHolder> {

    private List<BucketListItem> bucketList;
    private final OnItemClickListener listener;
    private BucketListViewModel bucketListViewModel;
    private Context context;

    public BucketListAdapter(List<BucketListItem> bucketListItems, OnItemClickListener listener) {
        this.bucketList = bucketListItems;
        this.listener = listener;
        bucketListViewModel = new BucketListViewModel(context);
    }

    public interface OnItemClickListener {
        void onItemClick(BucketListItem bucketListItem);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bucket_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final BucketListItem bucketListItem = bucketList.get(i);
        viewHolder.title.setText(bucketListItem.getBucketTitle());
        viewHolder.description.setText(bucketListItem.getBucketDescription());
        viewHolder.bind(bucketListItem, listener);
        if (bucketListItem.isStriked()) {
            viewHolder.checkBox.setChecked(true);
            viewHolder.title.setPaintFlags(viewHolder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.description.setPaintFlags(viewHolder.description.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            viewHolder.checkBox.setChecked(false);
            viewHolder.title.setPaintFlags(viewHolder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            viewHolder.description.setPaintFlags(viewHolder.description.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.checkBox.isChecked()) {
                    bucketListItem.setStriked(true);
                } else {
                    bucketListItem.setStriked(false);
                }
                bucketListViewModel.update(bucketListItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bucketList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CheckBox checkBox;
        public TextView title;
        public TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.bucket_checkbox);
            title = itemView.findViewById(R.id.bucket_title);
            description = itemView.findViewById(R.id.bucket_description);
        }

        public void bind(final BucketListItem bucketListItem, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(bucketListItem);
                }
            });
        }

    }

    public void swapList(List<BucketListItem> newListItems) {
        bucketList = newListItems;

        if (newListItems != null) {
            this.notifyDataSetChanged();
        }

    }
}
