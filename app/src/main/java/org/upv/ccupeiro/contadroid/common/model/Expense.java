package org.upv.ccupeiro.contadroid.common.model;

import android.support.annotation.NonNull;

import org.upv.ccupeiro.contadroid.common.utils.StringUtils;

import java.io.Serializable;

public class Expense implements Serializable,Comparable<Expense>{
    private int id;
    private String name;
    private String description;
    private float amount;
    private boolean isPaid;
    private ExpensesGroup group;

    private Expense(int id, String name, String description, float amount, boolean isPaid, ExpensesGroup group) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.isPaid = isPaid;
        this.group = group;
    }



    public static class Builder{
        private int id = -1;
        private String name = "";
        private String description = "";
        private float amount = 0;
        private boolean isPaid = false;
        private ExpensesGroup group = ExpensesGroup.EMPTY;

        public Builder withId(int id){
            this.id = id;
            return this;
        }

        public Builder withName(String name){
            this.name = name;
            return this;
        }

        public Builder withDescription(String description){
            this.description = description;
            return this;
        }

        public Builder withAmount(float amount){
            this.amount = amount;
            return this;
        }

        public Builder isPaid(){
            this.isPaid = true;
            return this;
        }

        public Builder withGroup(ExpensesGroup group){
            this.group = group;
            return this;
        }

        public Expense build(){
            return new Expense(id, name, description, amount, isPaid, group);
        }
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

    @Override
    public int compareTo(@NonNull Expense expenseToCompare) {
        int groupComp = group.compareTo(expenseToCompare.getGroup());
        if(groupComp!=0)
            return groupComp;
        else{
            return id < expenseToCompare.getId() ? -1
                    : id > expenseToCompare.getId() ? 1
                    : 0;
        }
    }
}
