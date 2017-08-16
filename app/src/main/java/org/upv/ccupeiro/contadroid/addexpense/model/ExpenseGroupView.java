package org.upv.ccupeiro.contadroid.addexpense.model;

import android.support.v7.app.AlertDialog;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.common.model.ExpensesGroup;

import static android.R.attr.id;

public class ExpenseGroupView {
    private ExpensesGroup groupType;
    private int icon;
    private String name;
    private boolean selected;

    private ExpenseGroupView(ExpensesGroup groupType, int icon, String name,boolean selected) {
        this.groupType = groupType;
        this.icon = icon;
        this.name = name;
        this.selected = selected;
    }

    public static class Builder{
        private ExpensesGroup groupType = ExpensesGroup.EMPTY;
        private int icon = R.drawable.icon_other;
        private String name = "";
        private boolean selected = false;

        public Builder withgroupType(ExpensesGroup groupType){
            this.groupType = groupType;
            return this;
        }

        public Builder withIcon(int icon){
            this.icon = icon;
            return this;
        }

        public  Builder withName(String name){
            this.name = name;
            return this;
        }

        public Builder isSelected(){
            this.selected = true;
            return this;
        }

        public ExpenseGroupView build(){
            return new ExpenseGroupView(groupType, icon, name, selected);
        }
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
