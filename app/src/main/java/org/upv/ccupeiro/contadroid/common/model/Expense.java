package org.upv.ccupeiro.contadroid.common.model;

/**
 * Created by Carlos on 01/08/2017.
 */

public class Expense {
    private String name;
    private String description;
    private float amount;
    private boolean isPaid;
    private ExpensesGroup group;

    public Expense(String name, float amount, ExpensesGroup group) {
        this.name = name;
        this.amount = amount;
        this.group = group;
        this.description = "";
        this.isPaid = false;
    }

    public Expense(String name, String description, float amount, ExpensesGroup group) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.isPaid = false;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAmount() {
        return amount;
    }

    public String getAmountEuroString(){
        return String.format("%.2f €",amount);
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public ExpensesGroup getGroup() {
        return group;
    }

    public void setGroup(ExpensesGroup group) {
        this.group = group;
    }
}
