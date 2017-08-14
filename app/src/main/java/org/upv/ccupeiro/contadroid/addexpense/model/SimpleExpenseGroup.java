package org.upv.ccupeiro.contadroid.addexpense.model;

import org.upv.ccupeiro.contadroid.R;

import java.util.ArrayList;
import java.util.List;

public class SimpleExpenseGroup {
    public static List<ExpenseGroupView> getListExpenseGroup(){
        List<ExpenseGroupView> expenseGroupViewList = new ArrayList<>();
        expenseGroupViewList.add(new ExpenseGroupView(R.drawable.icon_income,"Ingresos"));
        expenseGroupViewList.add(new ExpenseGroupView(R.drawable.icon_home,"Casa"));
        expenseGroupViewList.add(new ExpenseGroupView(R.drawable.icon_transport,"Transporte"));
        expenseGroupViewList.add(new ExpenseGroupView(R.drawable.icon_food,"Comida"));
        expenseGroupViewList.add(new ExpenseGroupView(R.drawable.icon_shoppings,"Compras"));
        expenseGroupViewList.add(new ExpenseGroupView(R.drawable.icon_leisure,"Ocio"));
        expenseGroupViewList.add(new ExpenseGroupView(R.drawable.icon_other,"Otros"));
        return expenseGroupViewList;
    }
}
