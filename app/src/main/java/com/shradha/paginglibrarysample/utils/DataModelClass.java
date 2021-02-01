package com.shradha.paginglibrarysample.utils;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

/**
 * Created by ${Shradha} on 30-01-2021.
 */
public class DataModelClass {

    private String avatar, email,first_name,last_name;
    private int id;

    public String getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getId() {
        return id;
    }

    public DataModelClass(int id,String email,String first_name,String last_name, String avatar) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.avatar = avatar;
    }

    public static DiffUtil.ItemCallback<DataModelClass> DIFF_CALLBACK = new DiffUtil.ItemCallback<DataModelClass>() {
        @Override
        public boolean areItemsTheSame(@NonNull DataModelClass oldItem, @NonNull DataModelClass newItem) {
            return oldItem.id==(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull DataModelClass oldItem, @NonNull DataModelClass newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        DataModelClass article = (DataModelClass) obj;
        return article.id==(this.id);
    }

}
