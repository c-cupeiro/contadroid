package org.upv.ccupeiro.contadroid.common.data;

import org.upv.ccupeiro.contadroid.common.model.Expense;

import java.util.List;

public interface ContadroidRepository {
    List<Expense> getPaidExpensesInMonth(int year, int month);
    List<Expense> getNotPaidExpensesInMonth(int year, int month);
    List<Expense> getYearExpenses(int year);
    List<Expense> getTemplate();
    void saveTemplateExpense(Expense expense, RepositoryCallback callback);
    void deleteTemplateExpense(long id, RepositoryCallback callback);
    void saveExpense(Expense expense, RepositoryCallback callback);
    void deleteExpense(long id, RepositoryCallback callback);
    void changePaidState(long id,boolean paid, RepositoryCallback callback);
    void addTemplateToMonth(int year, int month, RepositoryCallback callback);

}
