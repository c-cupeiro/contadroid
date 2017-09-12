package org.upv.ccupeiro.contadroid.common.data.datasource;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.model.Expense;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class RealmContadroidRepository implements ContadroidRepository {
    public static final String EXPENSE_FIELD_ID = "id";
    public static final String EXPENSE_FIELD_NAME = "id";
    public static final String EXPENSE_FIELD_DESCRIPTION = "id";
    public static final String EXPENSE_FIELD_AMOUNT = "id";
    public static final String EXPENSE_FIELD_IS_PAID = "id";
    public static final String EXPENSE_FIELD_IS_TEMPLATE = "id";
    public static final String EXPENSE_FIELD_CREATION_DATE = "id";
    public static final String EXPENSE_FIELD_GROUP = "id";

    private Realm realm;

    public RealmContadroidRepository(Realm realm) {
        this.realm = realm;
    }

    @Override
    public List<Expense> getPaidExpensesInMonth(int year, int month) {
        return null;
    }

    @Override
    public List<Expense> getNotPaidExpensesInMonth(int year, int month) {
        return null;
    }

    @Override
    public List<Expense> getYearExpenses(int year) {
        return null;
    }

    @Override
    public List<Expense> getTemplate() {
        return null;
    }

    @Override
    public boolean saveTemplateExpense(Expense expense) {
        expense.setTemplate(true);
        return saveExpense(expense);
    }

    @Override
    public boolean deleteTemplateExpense(int id) {
        return deleteExpense(id);
    }

    @Override
    public boolean saveExpense(final Expense expense) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(expense);
            }
        });
        return true; //TODO Look how to handle this return
    }

    @Override
    public boolean deleteExpense(final int id) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Expense> results = realm.where(Expense.class)
                        .equalTo(EXPENSE_FIELD_ID,id).findAll();
                results.deleteAllFromRealm();
            }
        });
        return true; //TODO Look how to handle this return
    }

    @Override
    public boolean changePaidState(int id, boolean paid) {
        return false;
    }
}
