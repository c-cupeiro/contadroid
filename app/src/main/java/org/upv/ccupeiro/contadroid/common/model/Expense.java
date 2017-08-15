package org.upv.ccupeiro.contadroid.common.model;

import org.upv.ccupeiro.contadroid.common.utils.StringUtils;

public class Expense {
    private int id;
    private String name;
    private String description;
    private float amount;
    private boolean isPaid;
    private ExpensesGroup group;

    public Expense(int id, String name, String description, float amount, boolean isPaid, ExpensesGroup group) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.isPaid = isPaid;
        this.group = group;
    }
    public Expense(String name, String description, float amount, ExpensesGroup group) {
        this.id = -1;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.isPaid = false;
        this.group = group;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return StringUtils.formatAmount(amount);
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
