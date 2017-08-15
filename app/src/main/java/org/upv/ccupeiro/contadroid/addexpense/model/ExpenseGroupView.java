package org.upv.ccupeiro.contadroid.addexpense.model;

import org.upv.ccupeiro.contadroid.common.model.ExpensesGroup;

import static android.R.attr.id;

public class ExpenseGroupView {
    private ExpensesGroup groupType;
    private int icon;
    private String name;
    private boolean selected;

    public ExpenseGroupView(ExpensesGroup groupType, int icon, String name,boolean selected) {
        this.groupType = groupType;
        this.icon = icon;
        this.name = name;
        this.selected = selected;
    }

    public ExpensesGroup getGroupType() {
        return groupType;
    }

    public void setGroupType(ExpensesGroup groupType) {
        this.groupType = groupType;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
