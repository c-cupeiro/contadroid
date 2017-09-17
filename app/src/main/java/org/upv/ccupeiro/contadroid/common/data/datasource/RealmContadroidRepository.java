package org.upv.ccupeiro.contadroid.common.data.datasource;

import android.content.Context;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.data.RepositoryCallback;
import org.upv.ccupeiro.contadroid.common.data.realm.ExpenseRealm;
import org.upv.ccupeiro.contadroid.common.domain.model.Expense;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

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

    public RealmContadroidRepository(Context context) {
        Realm.init(context);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
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
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            Date startDate = getDate(year, month, FIRST_DAY);
            Date endDate = getEndDate(year, month);
            RealmResults<ExpenseRealm> results = realm
                    .where(ExpenseRealm.class)
                    .between(EXPENSE_FIELD_CREATION_DATE, startDate, endDate)
                    .equalTo(EXPENSE_FIELD_IS_PAID, isPaid)
                    .equalTo(EXPENSE_FIELD_IS_TEMPLATE, IS_NOT_TEMPLATE)
                    .findAll();
            return getExpenseListFromRealmList(realm.copyFromRealm(results));
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    private Date getEndDate(int year, int month) {
        month++;
        if (month > DECEMBER) {
            month = JANUARY;
            year++;
        }
        return getDate(year, month, FIRST_DAY);
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

    private int getMonthForCalendar(int month) {
        return month - 1;
    }

    @Override
    public List<Expense> getYearExpenses(int year) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            Date startDate = getDate(year, JANUARY, FIRST_DAY);
            Date endDate = getEndDate(year, DECEMBER);
            RealmResults<ExpenseRealm> results = realm
                    .where(ExpenseRealm.class)
                    .between(EXPENSE_FIELD_CREATION_DATE, startDate, endDate)
                    .equalTo(EXPENSE_FIELD_IS_PAID, IS_PAID)
                    .findAll();
            return getExpenseListFromRealmList(realm.copyFromRealm(results));
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    @Override
    public List<Expense> getTemplate() {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            RealmResults<ExpenseRealm> results = realm
                    .where(ExpenseRealm.class)
                    .equalTo(EXPENSE_FIELD_IS_TEMPLATE, IS_TEMPLATE)
                    .findAll();
            return getExpenseListFromRealmList(realm.copyFromRealm(results));
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    @Override
    public void saveTemplateExpense(Expense expense, RepositoryCallback callback) {
        expense.setTemplate(true);
        saveExpense(expense, callback);
    }

    @Override
    public void deleteTemplateExpense(long id, RepositoryCallback callback) {
        deleteExpense(id, callback);
    }

    @Override
    public void saveExpense(final Expense expense, final RepositoryCallback callback) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(getExpenseRealmFromExpense(expense,realm));
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    callback.onSuccess();
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    callback.onError();
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    @Override
    public void deleteExpense(final long id, final RepositoryCallback callback) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<ExpenseRealm> results = realm.where(ExpenseRealm.class)
                            .equalTo(EXPENSE_FIELD_ID, id).findAll();
                    results.deleteAllFromRealm();
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    callback.onSuccess();
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    callback.onError();
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    @Override
    public void changePaidState(final long id, final boolean paid,
                                final RepositoryCallback callback) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    ExpenseRealm expenseRealm = realm.where(ExpenseRealm.class)
                            .equalTo(EXPENSE_FIELD_ID, id).findFirst();
                    expenseRealm.setPaid(paid);
                    realm.insertOrUpdate(expenseRealm);
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    callback.onSuccess();
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    callback.onError();
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    @Override
    public void addTemplateToMonth(int year, int month, final RepositoryCallback callback) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            final Date templateDateOnMonthYear = getDate(year, month, FIRST_DAY);
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    List<Expense> templateExpenseList = getTemplate();
                    for (Expense templateExpense : templateExpenseList) {
                        Expense newExpense = new Expense.Builder()
                                .withName(templateExpense.getName())
                                .withDescription(templateExpense.getDescription())
                                .withAmount(templateExpense.getAmount())
                                .withGroup(templateExpense.getGroup())
                                .withDate(templateDateOnMonthYear)
                                .build();
                        realm.insertOrUpdate(getExpenseRealmFromExpense(newExpense,realm));
                    }
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    callback.onSuccess();
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    callback.onError();
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    private List<Expense> getExpenseListFromRealmList(List<ExpenseRealm> expenseRealmList) {
        List<Expense> expenseList = new ArrayList<>();
        for (ExpenseRealm expenseRealm : expenseRealmList) {
            expenseList.add(expenseRealm.getExpense());
        }
        return expenseList;
    }

    private ExpenseRealm getExpenseRealmFromExpense(Expense expense, Realm realm) {
        ExpenseRealm expenseRealm = new ExpenseRealm();
        expenseRealm.setId(getIdFromExpense(expense,realm));
        expenseRealm.setName(expense.getName());
        expenseRealm.setDescription(expense.getDescription());
        expenseRealm.setAmount(expense.getAmount());
        expenseRealm.setPaid(expense.isPaid());
        expenseRealm.setTemplate(expense.isTemplate());
        expenseRealm.setCreationDate(expense.getCreationDate());
        expenseRealm.setGroup(expense.getGroup().name().toUpperCase());
        return expenseRealm;
    }

    private long getIdFromExpense(Expense expense, Realm realm) {
        if (expense.getId() == -1) {
            if (getSizeExpenseTable(realm) > 0) {
                return getMaxId(realm) + 1;
            } else {
                return 0;
            }
        } else {
            return expense.getId();
        }
    }

    private long getMaxId(Realm realm) {

        return (long) realm.where(ExpenseRealm.class).max(EXPENSE_FIELD_ID);
    }

    private int getSizeExpenseTable(Realm realm) {
        return realm.where(ExpenseRealm.class).findAll().size();
    }
}
