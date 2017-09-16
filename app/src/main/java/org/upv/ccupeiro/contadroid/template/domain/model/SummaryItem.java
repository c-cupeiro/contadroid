package org.upv.ccupeiro.contadroid.template.view.model;


public class SummaryItem {
    private String name;
    private float amount;
    private SummaryItemStatus status;

    private SummaryItem(String name, float amount, SummaryItemStatus status) {
        this.name = name;
        this.amount = amount;
        this.status = status;
    }

    public static class Builder{
        private String name = "";
        private float amount = 0;
        private SummaryItemStatus status = SummaryItemStatus.EMPTY;

        public Builder withName(String name){
            this.name = name;
            return this;
        }
        public Builder withAmount(float amount){
            this.amount = amount;
            return this;
        }
        public Builder withSummaryStatus(SummaryItemStatus status){
            this.status = status;
            return this;
        }

        public SummaryItem build(){
            return new SummaryItem(name,amount,status);
        }
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

    public void addAmount(float amount) {
        this.amount += amount;
    }

    public SummaryItemStatus getStatus() {
        return status;
    }

    public void setStatus(SummaryItemStatus status) {
        this.status = status;
    }
}
