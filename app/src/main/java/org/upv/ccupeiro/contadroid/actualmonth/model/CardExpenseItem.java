package org.upv.ccupeiro.contadroid.actualmonth.model;

import static android.R.attr.id;

public class CardExpenseItem {

    private boolean isGroupHeader;
    private boolean isExpenseRow;
    private int expenseId;
    private int icon;
    private String name;
    private float amount;
    private String description;
    private boolean isPaid;

    public boolean isGroupHeader() {
        return isGroupHeader;
    }

    public void setGroupHeader(boolean groupHeader) {
        isGroupHeader = groupHeader;
    }

    public boolean isExpenseRow() {
        return isExpenseRow;
    }

    public void setExpenseRow(boolean expenseRow) {
        isExpenseRow = expenseRow;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CardExpenseItem) {
            CardExpenseItem other = (CardExpenseItem) obj;
            return name.equals(other.name)
                    && amount == other.amount
                    && description.equals(other.description)
                    && expenseId == other.expenseId
                    && icon == other.icon
                    && isPaid == other.isPaid
                    && isGroupHeader == other.isGroupHeader
                    && isExpenseRow == other.isExpenseRow;
        } else {
            return false;
        }
    }
}
