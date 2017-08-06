package com.socialsearch.history.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.socialsearch.R;
import com.socialsearch.entity.HistoryData;
import java.util.ArrayList;
import java.util.List;

/**
 * SocialSearchDemo
 * com.socialsearch.history.view.adapter
 * HistoryDataAdapter
 */

public class HistoryDataAdapter extends RecyclerView.Adapter<HistoryDataAdapter.ViewHolder> {

  private List<HistoryData> items;
  private QuerySelectionListener listener;

  public HistoryDataAdapter() {
    items = new ArrayList<>();
  }

  public void setItems(List<HistoryData> items) {
    this.items = items;

    notifyDataSetChanged();
  }

  @Override public int getItemViewType(int position) {
    return R.layout.item_history;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    View view = LayoutInflater.from(context).inflate(viewType, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    HistoryData historyData = items.get(position);
    String query = historyData.getQuery();
    holder.textView.setText(query);
    holder.itemView.setTag(query);
    holder.itemView.setOnClickListener(v -> listener.onQuerySelected(((String) v.getTag())));
  }

  @Override public int getItemCount() {
    return items.size();
  }

  public void setSelectionListener(QuerySelectionListener listener) {
    this.listener = listener;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    public final TextView textView;

    public ViewHolder(View itemView) {
      super(itemView);

      textView = ((TextView) itemView.findViewById(R.id.textView));
    }
  }
}
