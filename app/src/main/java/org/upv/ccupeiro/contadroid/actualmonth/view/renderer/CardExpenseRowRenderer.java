package org.upv.ccupeiro.contadroid.actualmonth.view.renderer;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.actualmonth.model.CardExpenseItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardExpenseRowRenderer extends CardExpenseRenderer {
    @BindView(R.id.tv_row_description)
    private TextView description;
    @BindView(R.id.checkbox_row_item)
    private CheckBox checkbox;

    private Listener checkboxItemListener;

    public interface Listener{
        void onCheckboxClicked(CardExpenseItem expense);
    }

    public CardExpenseRowRenderer(Listener checkboxItemListener) {
        this.checkboxItemListener = checkboxItemListener;
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
        checkboxItemListener.onCheckboxClicked(getContent());
    }

}
