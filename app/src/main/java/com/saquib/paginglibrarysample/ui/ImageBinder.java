package com.saquib.paginglibrarysample.ui;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by ${Shradha} on 30-01-2021.
 */
public class ImageBinder {

    @BindingAdapter({"imageURL"})
    public static void loadImage(ImageView img, String imageUrl) {
        img.setImageURI(Uri.parse(imageUrl));
    }
}
