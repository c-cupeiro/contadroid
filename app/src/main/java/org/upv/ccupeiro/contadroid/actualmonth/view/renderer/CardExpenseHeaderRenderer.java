package org.upv.ccupeiro.contadroid.actualmonth.view.renderer;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.actualmonth.model.CardExpenseItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardExpenseHeaderRenderer extends CardExpenseRenderer {
    @BindView(R.id.iv_row_header_icon)
    ImageView icon;

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.card_header_renderer, parent, false);
        ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }

    @Override
    public void render() {
        CardExpenseItem item = getContent();
        renderIcon(item);
        renderName(item);
        renderAmount(item);
    }

    private void renderIcon(CardExpenseItem item) {
        icon.setImageResource(item.getIcon());
    }
}
