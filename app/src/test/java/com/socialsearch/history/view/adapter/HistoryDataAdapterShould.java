package com.socialsearch.history.view.adapter;

import android.app.Application;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.socialsearch.entity.HistoryData;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * SocialSearchDemo
 * com.socialsearch.history.view.adapter
 * HistoryDataAdapterShould
 */
@RunWith(RobolectricTestRunner.class) public class HistoryDataAdapterShould {

  private Application context;
  private RecyclerView recyclerView;
  private HistoryDataAdapter adapter;

  @Before public void setUp() {
    context = RuntimeEnvironment.application;

    recyclerView = new RecyclerView(context);
    recyclerView.setLayoutManager(new LinearLayoutManager(context));

    adapter = new HistoryDataAdapter();
  }

  @Test public void show_history_query_in_text() {
    adapter.setItems(Arrays.asList(new HistoryData("Fake query")));

    int fakePosition = 0;
    HistoryDataAdapter.ViewHolder viewHolder =
        adapter.onCreateViewHolder(recyclerView, adapter.getItemViewType(fakePosition));

    adapter.bindViewHolder(viewHolder, fakePosition);

    assertThat(viewHolder.textView, notNullValue());
    assertThat(viewHolder.textView.getText(), is(equalTo("Fake query")));
  }

}