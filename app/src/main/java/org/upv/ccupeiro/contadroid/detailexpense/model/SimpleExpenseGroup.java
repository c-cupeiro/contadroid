package org.upv.ccupeiro.contadroid.detailexpense.model;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.common.model.ExpensesGroup;

import java.util.ArrayList;
import java.util.List;

public class SimpleExpenseGroup {
    public static List<ExpenseGroupView> getListExpenseGroup(){
        List<ExpenseGroupView> expenseGroupViewList = new ArrayList<>();
        expenseGroupViewList.add(generateExpenseGroupView(ExpensesGroup.INCOME,R.drawable.icon_income,"Ingresos"));
        expenseGroupViewList.add(generateExpenseGroupView(ExpensesGroup.HOME,R.drawable.icon_home,"Casa"));
        expenseGroupViewList.add(generateExpenseGroupView(ExpensesGroup.TRANSPORT,R.drawable.icon_transport,"Transporte"));
        expenseGroupViewList.add(generateExpenseGroupView(ExpensesGroup.FOOD,R.drawable.icon_food,"Comida"));
        expenseGroupViewList.add(generateExpenseGroupView(ExpensesGroup.SHOPPING,R.drawable.icon_shoppings,"Compras"));
        expenseGroupViewList.add(generateExpenseGroupView(ExpensesGroup.LEISURE,R.drawable.icon_leisure,"Ocio"));
        expenseGroupViewList.add(generateExpenseGroupView(ExpensesGroup.OTHER,R.drawable.icon_other,"Otros"));
        return expenseGroupViewList;
    }
    
    private static ExpenseGroupView generateExpenseGroupView(ExpensesGroup groupType,
                                                             int icon, String name){
        return new ExpenseGroupView.Builder()
                .withgroupType(groupType)
                .withIcon(icon)
                .withName(name)
                .build();
    }
}
