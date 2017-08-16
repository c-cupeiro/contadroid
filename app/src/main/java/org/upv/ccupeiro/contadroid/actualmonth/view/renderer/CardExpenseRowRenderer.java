package org.upv.ccupeiro.contadroid.actualmonth.view.renderer;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.actualmonth.model.CardExpenseItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class CardExpenseRowRenderer extends CardExpenseRenderer {
    @BindView(R.id.tv_row_description)
    protected TextView description;
    @BindView(R.id.checkbox_row_item)
    protected CheckBox checkbox;
    @BindView(R.id.ll_row_item)
    protected LinearLayout row_item;

    private Listener itemListener;

    public interface Listener{
        void onCheckboxClicked(CardExpenseItem expense);
        void OnLongPressRow(CardExpenseItem expense);
    }

    public CardExpenseRowRenderer(Listener itemListener) {
        this.itemListener = itemListener;
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.card_row_renderer, parent, false);
        ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }

    @Override
    public void render() {
        CardExpenseItem item = getContent();
        renderName(item);
        renderAmount(item);
        renderCheckBox(item);
        renderDescription(item);
    }

    private void renderDescription(CardExpenseItem item) {
        if(item.getDescription().isEmpty()){
            description.setVisibility(View.GONE);
        }else{
            description.setText(item.getDescription());
        }
    }

    private void renderCheckBox(CardExpenseItem item) {
        checkbox.setChecked(item.isPaid());
    }

    @OnClick(R.id.checkbox_row_item) void clickCheckbox(){
        itemListener.onCheckboxClicked(getContent());
    }

    @OnLongClick(R.id.ll_row_item) boolean longClickOnItem(){
        itemListener.OnLongPressRow(getContent());
        return true;
    }

}
