package org.upv.ccupeiro.contadroid.common.domain.model;

import org.upv.ccupeiro.contadroid.R;

public class CardExpenseItem {

    private boolean isGroupHeader;
    private boolean isExpenseRow;
    private long expenseId;
    private int icon;
    private ExpensesGroup group;
    private String name;
    private int groupName;
    private float amount;
    private String description;
    private boolean isPaid;

    public final static CardExpenseItem CARD_EXPENSE_ITEM_EMPTY = new CardExpenseItem(false,false,
            -1, R.drawable.icon_other,ExpensesGroup.EMPTY,"",R.string.group_empty,0,"",false);

    private CardExpenseItem(boolean isGroupHeader, boolean isExpenseRow, long expenseId,
                            int icon, ExpensesGroup group, String name,int groupName, float amount,
                            String description, boolean isPaid) {
        this.isGroupHeader = isGroupHeader;
        this.isExpenseRow = isExpenseRow;
        this.expenseId = expenseId;
        this.icon = icon;
        this.group = group;
        this.name = name;
        this.groupName = groupName;
        this.amount = amount;
        this.description = description;
        this.isPaid = isPaid;
    }

    public static class Builder{
        private boolean isGroupHeader = false;
        private boolean isExpenseRow = false;
        private long expenseId = -1;
        private int icon = R.drawable.icon_other;
        private ExpensesGroup group = ExpensesGroup.EMPTY;
        private String name = "";
        private int groupName = R.string.group_empty;
        private float amount = 0;
        private String description = "";
        private boolean isPaid = false;

        public Builder isGroupHeader(){
            this.isGroupHeader = true;
            return this;
        }

        public Builder isExpenseRow(){
            this.isExpenseRow = true;
            return this;
        }

        public Builder withExpenseId(long expenseId){
            this.expenseId = expenseId;
            return this;
        }

        public Builder withIcon(int icon){
            this.icon = icon;
            return this;
        }

        public Builder withGroup(ExpensesGroup group){
            this.group = group;
            return this;
        }

        public Builder withGroupName(int groupName){
            this.groupName = groupName;
            return this;
        }

        public Builder withName(String name){
            this.name = name;
            return this;
        }

        public Builder withAmount(float amount){
            this.amount = amount;
            return this;
        }

        public Builder withDescription(String description){
            this.description = description;
            return this;
        }

        public Builder isPaid(){
            this.isPaid = true;
            return this;
        }

        public CardExpenseItem build(){
            return new CardExpenseItem(isGroupHeader, isExpenseRow, expenseId, icon,
                    group, name, groupName,  amount, description, isPaid);
        }

    }


    public boolean isGroupHeader() {
        return isGroupHeader;
    }

    public boolean isExpenseRow() {
        return isExpenseRow;
    }

    public long getExpenseId() {
        return expenseId;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public ExpensesGroup getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public int getGroupName() {
        return groupName;
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

    public boolean isPaid() {
        return isPaid;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CardExpenseItem) {
            CardExpenseItem other = (CardExpenseItem) obj;
            return name.equals(other.name)
                    && amount == other.amount
                    && description.equals(other.description)
                    && expenseId == other.expenseId
                    && group == other.group
                    && icon == other.icon
                    && isPaid == other.isPaid
                    && isGroupHeader == other.isGroupHeader
                    && isExpenseRow == other.isExpenseRow;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "CardExpenseItem{" +
                "isGroupHeader=" + isGroupHeader +
                ", isExpenseRow=" + isExpenseRow +
                ", expenseId=" + expenseId +
                ", icon=" + icon +
                ", group=" + group +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", isPaid=" + isPaid +
                '}';
    }
}
