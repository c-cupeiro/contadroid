package org.upv.ccupeiro.contadroid.actualmonth.view.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pedrogomez.renderers.Renderer;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.common.domain.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.common.domain.model.ExpensesGroup;
import org.upv.ccupeiro.contadroid.common.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ccupeiro on 14/08/2017.
 */

public abstract class CardExpenseRenderer extends Renderer<CardExpenseItem> {
    public static final String NEGATIVE_SYMBOL = "-";
    public static final String POSITIVE_SYMBOL = "+";
    @BindView(R.id.tv_row_item_name)
    TextView name;
    @BindView(R.id.tv_row_item_amount)
    TextView amount;


    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.card_item_renderer, parent, false);

        ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }

    @Override
    public void render() {
        CardExpenseItem item = getContent();
        renderName(item);
        renderAmount(item);
    }

    protected void renderName(CardExpenseItem item) {
        if (item.isGroupHeader()) {
            name.setText(getContext().getText(item.getGroupName()));
        } else {
            name.setText(item.getName());
        }

    }

    protected void renderAmount(CardExpenseItem item) {
        String amountFormated = StringUtils.formatAmount(item.getAmount());
        if(item.isGroupHeader()){
            if (item.getGroup() == ExpensesGroup.INCOME) {
                amount.setText(POSITIVE_SYMBOL + amountFormated);
            } else {
                amount.setText(NEGATIVE_SYMBOL + amountFormated);
            }
        }else{
            amount.setText(amountFormated);
        }
    }

    @Override
    protected void setUpView(View rootView) {

    }

    @Override
    protected void hookListeners(View rootView) {

    }


}
