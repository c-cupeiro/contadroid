package org.upv.ccupeiro.contadroid.common.view.listener;

import org.upv.ccupeiro.contadroid.common.domain.model.CardExpenseItem;

public interface CardExpenseListener {
    void onCheckboxClicked(CardExpenseItem expense);
    void OnLongPressRow(CardExpenseItem expense);
}
