package com.socialsearch.search.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.socialsearch.R;
import com.socialsearch.core.view.ImageLoader;
import com.socialsearch.entity.SocialData;
import java.util.ArrayList;
import java.util.List;

/**
 * SocialSearchDemo
 * com.socialsearch.search.view.adapter
 * SocialDataAdapter
 */

public class SocialDataAdapter extends RecyclerView.Adapter<SocialDataAdapter.ViewHolder> {

  private final List<SocialData> items;
  private final ImageLoader imageLoader;

  public SocialDataAdapter(ImageLoader imageLoader) {
    items = new ArrayList<>();
    this.imageLoader = imageLoader;

    setHasStableIds(true);
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_social_data, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    SocialData socialData = items.get(position);
    imageLoader.loadImage(holder.image, socialData.getImageUrl());
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public int getItemCount() {
    return items.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    private final ImageView image;

    public ViewHolder(View itemView) {
      super(itemView);

      image = (ImageView) itemView.findViewById(R.id.image);
    }
  }
}
