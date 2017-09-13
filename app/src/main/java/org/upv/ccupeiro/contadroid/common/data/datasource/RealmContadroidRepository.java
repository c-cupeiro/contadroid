package org.upv.ccupeiro.contadroid.common.data.datasource;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.model.Expense;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
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

    private static final int FIRST_DAY = 1;
    private static final int DECEMBER = 13;
    private static final int JANUARY = 1;
    private static final boolean IS_PAID = true;
    private static final boolean IS_NOT_PAID = false;
    private static final boolean IS_TEMPLATE = true;
    private static final boolean IS_NOT_TEMPLATE = false;

    private Realm realm;

    public RealmContadroidRepository(Realm realm) {
        this.realm = realm;
    }

    @Override
    public List<Expense> getPaidExpensesInMonth(int year, int month) {
        return getExpenseinMonthByPaidState(year, month, IS_PAID);
    }
    @Override
    public List<Expense> getNotPaidExpensesInMonth(int year, int month) {
        return getExpenseinMonthByPaidState(year, month, IS_NOT_PAID);
    }

    private List<Expense> getExpenseinMonthByPaidState(int year, int month, boolean isPaid) {
        Date startDate = getDate(year,month,FIRST_DAY);
        Date endDate = getEndDate(year,month);
        RealmResults<Expense> results = realm
                .where(Expense.class)
                .between(EXPENSE_FIELD_CREATION_DATE,startDate,endDate)
                .equalTo(EXPENSE_FIELD_IS_PAID,isPaid)
                .findAll();
        return realm.copyFromRealm(results);
    }

    private Date getEndDate(int year, int month) {
        month++;
        if(month> DECEMBER){
            month = JANUARY;
            year++;
        }
        return getDate(year,month,FIRST_DAY);
    }

    private Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, getMonthForCalendar(month));
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private int getMonthForCalendar(int month){
        return month-1;
    }

    @Override
    public List<Expense> getYearExpenses(int year) {
        Date startDate = getDate(year,JANUARY,FIRST_DAY);
        Date endDate = getEndDate(year,DECEMBER);
        RealmResults<Expense> results = realm
                .where(Expense.class)
                .between(EXPENSE_FIELD_CREATION_DATE,startDate,endDate)
                .equalTo(EXPENSE_FIELD_IS_PAID,IS_PAID)
                .findAll();
        return realm.copyFromRealm(results);

    }

    @Override
    public List<Expense> getTemplate() {
        RealmResults<Expense> results = realm
                .where(Expense.class)
                .equalTo(EXPENSE_FIELD_IS_TEMPLATE,IS_TEMPLATE)
                .findAll();
        return realm.copyFromRealm(results);
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
        /*realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(expense);
            }
        });*/
        return true; //TODO Look how to handle this return
    }

    @Override
    public boolean deleteExpense(final int id) {
        /*realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Expense> results = realm.where(Expense.class)
                        .equalTo(EXPENSE_FIELD_ID,id).findAll();
                results.deleteAllFromRealm();
            }
        });*/
        return true; //TODO Look how to handle this return
    }

    @Override
    public boolean changePaidState(final int id, final boolean paid) {
       /* realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Expense expense = realm.where(Expense.class)
                        .equalTo(EXPENSE_FIELD_ID,id).findFirst();
                expense.setPaid(paid);
                realm.insertOrUpdate(expense);
            }
        });*/
        return true; //TODO Look how to handle this return
    }
}
