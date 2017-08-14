package org.upv.ccupeiro.contadroid.addexpense.model;

public class ExpenseGroupView {
    private int icon;
    private String name;

    public ExpenseGroupView(int icon, String name) {
        this.icon = icon;
        this.name = name;
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
}
