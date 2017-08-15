package org.upv.ccupeiro.contadroid.addexpense.model;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.common.model.ExpensesGroup;

import java.util.ArrayList;
import java.util.List;

public class SimpleExpenseGroup {
    public static List<ExpenseGroupView> getListExpenseGroup(){
        List<ExpenseGroupView> expenseGroupViewList = new ArrayList<>();
        expenseGroupViewList.add(new ExpenseGroupView(ExpensesGroup.INCOME,R.drawable.icon_income,"Ingresos",false));
        expenseGroupViewList.add(new ExpenseGroupView(ExpensesGroup.HOME,R.drawable.icon_home,"Casa",false));
        expenseGroupViewList.add(new ExpenseGroupView(ExpensesGroup.TRANSPORT,R.drawable.icon_transport,"Transporte",false));
        expenseGroupViewList.add(new ExpenseGroupView(ExpensesGroup.FOOD,R.drawable.icon_food,"Comida",false));
        expenseGroupViewList.add(new ExpenseGroupView(ExpensesGroup.SHOPPING,R.drawable.icon_shoppings,"Compras",false));
        expenseGroupViewList.add(new ExpenseGroupView(ExpensesGroup.LEISURE,R.drawable.icon_leisure,"Ocio",false));
        expenseGroupViewList.add(new ExpenseGroupView(ExpensesGroup.OTHER,R.drawable.icon_other,"Otros",false));
        return expenseGroupViewList;
    }
}
