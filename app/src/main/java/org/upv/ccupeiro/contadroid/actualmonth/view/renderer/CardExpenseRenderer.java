package org.upv.ccupeiro.contadroid.actualmonth.view.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pedrogomez.renderers.Renderer;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.actualmonth.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.common.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ccupeiro on 14/08/2017.
 */

public abstract class CardExpenseRenderer extends Renderer<CardExpenseItem> {
    @BindView(R.id.tv_row_item_name)
    protected TextView name;
    @BindView(R.id.tv_row_item_amount)
    protected TextView amount;


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

    protected void renderName(CardExpenseItem item){
        name.setText(item.getName());
    }

    protected void renderAmount(CardExpenseItem item){
        amount.setText(StringUtils.formatAmount(item.getAmount()));
    }

    @Override
    protected void setUpView(View rootView) {

    }

    @Override
    protected void hookListeners(View rootView) {

    }


}
