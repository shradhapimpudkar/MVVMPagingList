package com.saquib.paginglibrarysample.ui;

import android.arch.paging.PagedListAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.saquib.paginglibrarysample.R;
import com.saquib.paginglibrarysample.databinding.RowLayoutBinding;
import com.saquib.paginglibrarysample.utils.DataModelClass;

/**
 * Created by ${Shradha} on 30-01-2021.
 */

public class MyPageListAdapter extends PagedListAdapter<DataModelClass, MyPageListAdapter.MyViewHolder> {

    MyPageListAdapter() {
        super(DataModelClass.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RowLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_layout, parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.binding.setModel(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        RowLayoutBinding binding;

        MyViewHolder(RowLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

    }
}
