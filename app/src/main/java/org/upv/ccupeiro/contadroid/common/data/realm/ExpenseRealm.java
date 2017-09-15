package org.upv.ccupeiro.contadroid.common.data.realm;

import org.upv.ccupeiro.contadroid.common.model.Expense;
import org.upv.ccupeiro.contadroid.common.model.ExpensesGroup;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class ExpenseRealm extends RealmObject {
    @PrimaryKey
    @Index
    private long id = -1;
    @Required
    private String name = "";
    private String description = "";
    private float amount = 0;
    private boolean isPaid = false;
    private boolean isTemplate = false;
    @Index
    private Date creationDate = new Date(0);
    private String group = ExpensesGroup.EMPTY.name();

    @Ignore
    public static final String EXPENSE_FIELD_ID = "id";
    @Ignore
    public static final String EXPENSE_FIELD_NAME = "name";
    @Ignore
    public static final String EXPENSE_FIELD_DESCRIPTION = "description";
    @Ignore
    public static final String EXPENSE_FIELD_AMOUNT = "amount";
    @Ignore
    public static final String EXPENSE_FIELD_IS_PAID = "isPaid";
    @Ignore
    public static final String EXPENSE_FIELD_IS_TEMPLATE = "isTemplate";
    @Ignore
    public static final String EXPENSE_FIELD_CREATION_DATE = "creationDate";
    @Ignore
    public static final String EXPENSE_FIELD_GROUP = "group";

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Expense getExpense(){
        Expense.Builder expense =  new Expense.Builder()
                .withId(id)
                .withName(name)
                .withDescription(description)
                .withAmount(amount)
                .withDate(creationDate)
                .withGroup(ExpensesGroup.valueOf(group));
        if(isPaid){
            expense.isPaid();
        }
        if(isTemplate){
            expense.isTemplate();
        }
        return expense.build();
    }
}
