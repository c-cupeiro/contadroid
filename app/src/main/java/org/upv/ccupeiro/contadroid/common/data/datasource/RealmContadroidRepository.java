package org.upv.ccupeiro.contadroid.common.data.datasource;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.model.Expense;

import java.util.List;

import io.realm.Realm;


public class RealmContadroidRepository implements ContadroidRepository {
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
        return false;
    }

    @Override
    public boolean deleteTemplateExpense(int id) {
        return false;
    }

    @Override
    public boolean saveExpense(Expense expense) {
        return false;
    }

    @Override
    public boolean deleteExpense(int id) {
        return false;
    }

    @Override
    public boolean changePaidState(int id, boolean paid) {
        return false;
    }
}
