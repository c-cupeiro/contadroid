package org.upv.ccupeiro.contadroid.detailexpense.view.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pedrogomez.renderers.Renderer;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.detailexpense.model.ExpenseGroupView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExpenseGroupViewRenderer extends Renderer<ExpenseGroupView> {
    @BindView(R.id.iv_category_row_icon)
    ImageView iv_row_icon;
    @BindView(R.id.tv_category_row_name)
    TextView tv_row_name;
    @BindView(R.id.ll_category_row)
    LinearLayout ll_category;

    private Listener rowItemListener;

    public interface Listener{
        void onRowClicked(ExpenseGroupView group, View viewClicked);
    }

    public ExpenseGroupViewRenderer(Listener rowItemListener) {
        this.rowItemListener = rowItemListener;
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.category_renderer, parent, false);
        ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }

    @Override
    public void render() {
        ExpenseGroupView item = getContent();
        renderIcon(item);
        renderName(item);
        if(item.isSelected())
            renderBorder();
        else
            clearBackground();
    }

    private void renderName(ExpenseGroupView item) {
        tv_row_name.setText(item.getName());
    }

    private void renderIcon(ExpenseGroupView item) {
        iv_row_icon.setImageResource(item.getIcon());
    }

    private void renderBorder() {
        ll_category.setBackgroundResource(R.drawable.category_selected);
    }
    private void clearBackground(){
        ll_category.setBackground(null);
    }

    @Override
    protected void setUpView(View rootView) {

    }

    @Override
    protected void hookListeners(View rootView) {

    }

    @OnClick(R.id.ll_category_row) void itemClicked(View view){
        rowItemListener.onRowClicked(getContent(),view);
    }

}
