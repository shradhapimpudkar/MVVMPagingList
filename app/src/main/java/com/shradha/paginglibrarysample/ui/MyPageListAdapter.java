package com.shradha.paginglibrarysample.ui;

import android.arch.paging.PagedListAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.shradha.paginglibrarysample.R;
import com.shradha.paginglibrarysample.databinding.RowPagingLayoutBinding;
import com.shradha.paginglibrarysample.utils.DataModelClass;

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

        RowPagingLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_paging_layout, parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.rowPagingLayoutBinding.setModel(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        // RowLayoutBinding binding;

        RowPagingLayoutBinding rowPagingLayoutBinding;

        MyViewHolder(RowPagingLayoutBinding itemView) {
            super(itemView.getRoot());
            rowPagingLayoutBinding = itemView;
        }

    }
}
