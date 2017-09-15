package org.upv.ccupeiro.contadroid.common.data.datasource;

import android.util.Log;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.data.realm.ExpenseRealm;
import org.upv.ccupeiro.contadroid.common.model.Expense;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

import static android.content.ContentValues.TAG;
import static org.upv.ccupeiro.contadroid.common.data.realm.ExpenseRealm.EXPENSE_FIELD_CREATION_DATE;
import static org.upv.ccupeiro.contadroid.common.data.realm.ExpenseRealm.EXPENSE_FIELD_ID;
import static org.upv.ccupeiro.contadroid.common.data.realm.ExpenseRealm.EXPENSE_FIELD_IS_PAID;
import static org.upv.ccupeiro.contadroid.common.data.realm.ExpenseRealm.EXPENSE_FIELD_IS_TEMPLATE;


public class RealmContadroidRepository implements ContadroidRepository {

    private static final int FIRST_DAY = 1;
    private static final int DECEMBER = 13;
    private static final int JANUARY = 1;
    private static final boolean IS_PAID = true;
    private static final boolean IS_NOT_PAID = false;
    private static final boolean IS_TEMPLATE = true;
    private static final boolean IS_NOT_TEMPLATE = false;
    private static final String TAG_REALM_REPOSITORY = "Realm_REPO";

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
        RealmResults<ExpenseRealm> results = realm
                .where(ExpenseRealm.class)
                .between(EXPENSE_FIELD_CREATION_DATE,startDate,endDate)
                .equalTo(EXPENSE_FIELD_IS_PAID,isPaid)
                .findAll();
        return getExpenseListFromRealmList(realm.copyFromRealm(results));
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
        RealmResults<ExpenseRealm> results = realm
                .where(ExpenseRealm.class)
                .between(EXPENSE_FIELD_CREATION_DATE,startDate,endDate)
                .equalTo(EXPENSE_FIELD_IS_PAID,IS_PAID)
                .findAll();
        return getExpenseListFromRealmList(realm.copyFromRealm(results));

    }

    @Override
    public List<Expense> getTemplate() {
        RealmResults<ExpenseRealm> results = realm
                .where(ExpenseRealm.class)
                .equalTo(EXPENSE_FIELD_IS_TEMPLATE,IS_TEMPLATE)
                .findAll();
        return getExpenseListFromRealmList(realm.copyFromRealm(results));
    }

    @Override
    public boolean saveTemplateExpense(Expense expense) {
        expense.setTemplate(true);
        return saveExpense(expense);
    }

    @Override
    public boolean deleteTemplateExpense(long id) {
        return deleteExpense(id);
    }

    @Override
    public boolean saveExpense(Expense expense) {
        try{
            realm.beginTransaction();
            realm.insertOrUpdate(getExpenseRealmFromExpense(expense));
            realm.commitTransaction();
            return true;
        }catch (Exception e){
            Log.e(TAG_REALM_REPOSITORY, "saveExpense: ", e);
            return false;
        }
    }

    @Override
    public boolean deleteExpense(long id) {
        try{
            realm.beginTransaction();
            RealmResults<ExpenseRealm> results = realm.where(ExpenseRealm.class)
                    .equalTo(EXPENSE_FIELD_ID,id).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
            return true;
        }catch (Exception e){
            Log.e(TAG_REALM_REPOSITORY, "deleteExpense: ", e);
            return false;
        }
    }

    @Override
    public boolean changePaidState(long id, final boolean paid) {
        try{
            realm.beginTransaction();
            ExpenseRealm expenseRealm = realm.where(ExpenseRealm.class)
                    .equalTo(EXPENSE_FIELD_ID,id).findFirst();
            expenseRealm.setPaid(paid);
            realm.insertOrUpdate(expenseRealm);
            realm.commitTransaction();
            return true;
        }catch (Exception e){
            Log.e(TAG_REALM_REPOSITORY, "changePaidState: ", e);
            return false;
        }
    }

    private List<Expense> getExpenseListFromRealmList(List<ExpenseRealm> expenseRealmList){
        List<Expense> expenseList = new ArrayList<>();
        for(ExpenseRealm expenseRealm:expenseRealmList){
            expenseList.add(expenseRealm.getExpense());
        }
        return expenseList;
    }

    private ExpenseRealm getExpenseRealmFromExpense(Expense expense){
        ExpenseRealm expenseRealm = new ExpenseRealm();
        expenseRealm.setId(getIdFromExpense(expense));
        expenseRealm.setName(expense.getName());
        expenseRealm.setDescription(expense.getDescription());
        expenseRealm.setAmount(expense.getAmount());
        expenseRealm.setPaid(expense.isPaid());
        expenseRealm.setTemplate(expense.isTemplate());
        expenseRealm.setCreationDate(expense.getCreationDate());
        expenseRealm.setGroup(expense.getGroup().name().toUpperCase());
        return expenseRealm;
    }
    private long getIdFromExpense(Expense expense){
        if(expense.getId() == -1){
            if ( realm.where(ExpenseRealm.class).findAll().size()>0){
                long lastId = (long) realm.where(ExpenseRealm.class).max(EXPENSE_FIELD_ID);
                return lastId+1;
            }else{
                return 0;
            }
        }else{
            return expense.getId();
        }
    }
}
