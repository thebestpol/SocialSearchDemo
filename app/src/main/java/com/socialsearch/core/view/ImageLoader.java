package com.socialsearch.core.view;

import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/**
 * SocialSearchDemo
 * com.socialsearch.core.view
 * ImageLoader
 */

public class ImageLoader {

  public void loadImage(ImageView view, String url) {
    Picasso.with(view.getContext()).load(url).into(view);
  }
}
