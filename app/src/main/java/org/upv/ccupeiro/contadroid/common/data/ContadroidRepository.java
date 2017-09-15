package org.upv.ccupeiro.contadroid.common.data;

import org.upv.ccupeiro.contadroid.common.model.Expense;

import java.util.List;

public interface ContadroidRepository {
    List<Expense> getPaidExpensesInMonth(int year, int month);
    List<Expense> getNotPaidExpensesInMonth(int year, int month);
    List<Expense> getYearExpenses(int year);
    List<Expense> getTemplate();
    boolean saveTemplateExpense(Expense expense);
    boolean deleteTemplateExpense(long id);
    boolean saveExpense(Expense expense);
    boolean deleteExpense(long id);
    boolean changePaidState(long id,boolean paid);
    boolean addTemplateToMonth(int year, int month);

}
