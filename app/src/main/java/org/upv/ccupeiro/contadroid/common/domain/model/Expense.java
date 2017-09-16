package org.upv.ccupeiro.contadroid.common.domain.model;

import java.io.Serializable;
import java.util.Date;

public class Expense implements Serializable{
    private long id;
    private String name;
    private String description;
    private float amount;
    private boolean isPaid;
    private boolean isTemplate;
    private Date creationDate;
    private ExpensesGroup group;

    private Expense(long id, String name, String description, float amount, boolean isPaid,
                    boolean isTemplate, ExpensesGroup group, Date creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.isPaid = isPaid;
        this.isTemplate = isTemplate;
        this.group = group;
        this.creationDate = creationDate;
    }



    public static class Builder{
        private long id = -1;
        private String name = "";
        private String description = "";
        private float amount = 0;
        private boolean isPaid = false;
        private boolean isTemplate = false;
        private ExpensesGroup group = ExpensesGroup.EMPTY;
        private Date creationDate = new Date(0);

        public Builder withId(long id){
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

        public Builder isTemplate(){
            this.isTemplate = true;
            return this;
        }

        public Builder withGroup(ExpensesGroup group){
            this.group = group;
            return this;
        }

        public Builder withDate(Date creationDate){
            this.creationDate = creationDate;
            return this;
        }

        public Expense build(){
            return new Expense(id, name, description, amount, isPaid,
                    isTemplate, group, creationDate);
        }
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getAmount() {
        return amount;
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

    public boolean isTemplate() {
        return isTemplate;
    }

    public void setTemplate(boolean template) {
        isTemplate = template;
    }

    public ExpensesGroup getGroup() {
        return group;
    }

    public Date getCreationDate() {
        return creationDate;
    }

}