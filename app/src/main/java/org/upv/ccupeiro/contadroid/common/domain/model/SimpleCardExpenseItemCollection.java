package org.upv.ccupeiro.contadroid.common.domain.model;

import org.upv.ccupeiro.contadroid.R;

import java.util.ArrayList;
import java.util.List;

public class SimpleCardExpenseItemCollection {

    public static CardExpenseCollection getPaidCollection(){

        return new CardExpenseCollection(getPaidExpenseList());
    }

    public static List<CardExpenseItem> getPaidExpenseList(){

        List<CardExpenseItem> cardExpenseItemsList = new ArrayList<>();
        cardExpenseItemsList.add(generateGroupHeader(R.drawable.icon_income,"Ingresos",2000));
        cardExpenseItemsList.add(generateExpenseRow(ExpensesGroup.INCOME,"Sueldo","ingreso del trabajo",2000,true));
        cardExpenseItemsList.add(generateGroupHeader(R.drawable.icon_home,"Casa",415));
        cardExpenseItemsList.add(generateExpenseRow(ExpensesGroup.HOME,"Alquiler","",300,true));
        cardExpenseItemsList.add(generateExpenseRow(ExpensesGroup.HOME,"Fijo e Internet","Jazztel",40,true));
        cardExpenseItemsList.add(generateExpenseRow(ExpensesGroup.HOME,"Agua","Canal de Isabel II",15,true));
        cardExpenseItemsList.add(generateExpenseRow(ExpensesGroup.HOME,"Gas","HolaGas",20,true));
        cardExpenseItemsList.add(generateGroupHeader(R.drawable.icon_transport,"Transporte",56));
        cardExpenseItemsList.add(generateExpenseRow(ExpensesGroup.TRANSPORT,"Abono","Metro",56,true));
        cardExpenseItemsList.add(generateGroupHeader(R.drawable.icon_food,"Comida",163));
        cardExpenseItemsList.add(generateExpenseRow(ExpensesGroup.FOOD,"Mercadona","Primer finde del mes",89,true));
        cardExpenseItemsList.add(generateExpenseRow(ExpensesGroup.FOOD,"Alcampo","Segundo finde del mes",74,true));
        cardExpenseItemsList.add(generateGroupHeader(R.drawable.icon_other,"Otros",50));
        cardExpenseItemsList.add(generateExpenseRow(ExpensesGroup.OTHER,"Regalos","Regalo para Javi",50,true));

        return cardExpenseItemsList;
    }

    public static CardExpenseCollection getNotPaidCollection(){

        return new CardExpenseCollection(getNotPaidExpenseList());
    }

    public static List<CardExpenseItem> getNotPaidExpenseList(){
        List<CardExpenseItem> cardExpenseItemsList = new ArrayList<>();
        cardExpenseItemsList.add(generateGroupHeader(R.drawable.icon_transport,"Transporte",250));
        cardExpenseItemsList.add(generateExpenseRow(ExpensesGroup.TRANSPORT,"Coche","Letra mensual",250,false));
        cardExpenseItemsList.add(generateGroupHeader(R.drawable.icon_shoppings,"Compras",395));
        cardExpenseItemsList.add(generateExpenseRow(ExpensesGroup.SHOPPING,"H&M","camiseta y pantalon",95,false));
        cardExpenseItemsList.add(generateExpenseRow(ExpensesGroup.SHOPPING,"Game","Ps4",300,false));
        cardExpenseItemsList.add(generateGroupHeader(R.drawable.icon_leisure,"Ocio",50));
        cardExpenseItemsList.add(generateExpenseRow(ExpensesGroup.LEISURE,"Cervezas Amigos","",50,false));

        return cardExpenseItemsList;
    }

    private static CardExpenseItem generateGroupHeader(int icon,String name,float amount){
        return new CardExpenseItem.Builder()
                .isGroupHeader()
                .withIcon(icon)
                .withName(name)
                .withAmount(amount)
                .build();
    }
    private static CardExpenseItem generateExpenseRow(ExpensesGroup group, String name, String description,
                                                      float amount, boolean paid){
        CardExpenseItem.Builder expenseRow = new CardExpenseItem.Builder()
                .isExpenseRow()
                .withGroup(group)
                .withName(name)
                .withDescription(description)
                .withAmount(amount);
        if(paid)
            expenseRow.isPaid();
        return expenseRow.build();
    }

}
