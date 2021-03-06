package org.upv.ccupeiro.contadroid.summary.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.common.view.activity.BasicActivity;
import org.upv.ccupeiro.contadroid.summary.view.presenter.SummaryPresenter;
import org.upv.ccupeiro.contadroid.summary.view.renderer.SummaryItemRenderer;
import org.upv.ccupeiro.contadroid.template.domain.model.SummaryItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class SummaryActivity extends BasicActivity implements SummaryPresenter.View{

    @Nullable
    @BindView(R.id.rv_summary)
    RecyclerView rvSummary;
    @Nullable
    @BindView(R.id.tv_empty_case)
    TextView emptyCase;

    private RVRendererAdapter<SummaryItem> adapter;

    @Inject
    SummaryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeFrameLayout(R.layout.activity_summary);
        initializeDependencyInjection();
        initToolbar();
        initializeAdapter();
        initializeRecyclerView();
        initializePresenter();
        hideFloatingButton();
    }

    public static void open(Activity activity){
        Intent intent = new Intent(activity, SummaryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finish();
    }

    private void initializeDependencyInjection() {
        getAppComponent().inject(this);
    }

    private void initializePresenter(){
        presenter.setView(this);
        presenter.initialize();
    }


    private void initToolbar() {
        setTitle(R.string.summary_title);
    }

    private void initializeAdapter(){
        AdapteeCollection<SummaryItem> collection = new ListAdapteeCollection<>();
        Renderer<SummaryItem> renderer = new SummaryItemRenderer();
        RendererBuilder<SummaryItem> rendererBuilder = new RendererBuilder<>(renderer);
        adapter = new RVRendererAdapter<>(rendererBuilder,collection);
    }

    private void initializeRecyclerView(){
        rvSummary.setLayoutManager(new LinearLayoutManager(this));
        rvSummary.setAdapter(adapter);
    }

    @Override
    public void showSummaryList(List<SummaryItem> summaryItemList) {
        adapter.clear();
        adapter.addAll(summaryItemList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyCase() {
        emptyCase.setVisibility(View.VISIBLE);
        rvSummary.setVisibility(View.GONE);
    }
}
