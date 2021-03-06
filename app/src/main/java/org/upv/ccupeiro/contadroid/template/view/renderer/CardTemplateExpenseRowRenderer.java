package org.upv.ccupeiro.contadroid.template.view.renderer;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.actualmonth.view.renderer.CardExpenseRenderer;
import org.upv.ccupeiro.contadroid.common.domain.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.common.view.listener.CardExpenseListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class CardTemplateExpenseRowRenderer extends CardExpenseRenderer {
    @BindView(R.id.tv_row_description)
    TextView description;
    @BindView(R.id.checkbox_row_item)
    CheckBox checkbox;

    private CardExpenseListener itemListener;

    public CardTemplateExpenseRowRenderer(CardExpenseListener itemListener) {
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
        checkbox.setVisibility(View.GONE);
    }

    @OnClick(R.id.checkbox_row_item) void clickCheckbox(){
        itemListener.onCheckboxClicked(getContent());
    }

    @OnLongClick(R.id.ll_row_item) boolean longClickOnItem(){
        itemListener.OnLongPressRow(getContent());
        return true;
    }

}
