package org.upv.ccupeiro.contadroid.actualmonth.model;

import org.upv.ccupeiro.contadroid.R;

import java.util.ArrayList;
import java.util.List;

public class SimpleCardExpenseItemCollection {

    public static CardExpenseCollection getPaidCollection(){
        List<CardExpenseItem> cardExpenseItemsList = new ArrayList<>();
        cardExpenseItemsList.add(generateGroupHeader(R.drawable.icon_income,"Ingresos",2000));
        cardExpenseItemsList.add(generateExpenseRow("Sueldo","ingreso del trabajo",2000,true));
        cardExpenseItemsList.add(generateGroupHeader(R.drawable.icon_home,"Casa",415));
        cardExpenseItemsList.add(generateExpenseRow("Alquiler","",300,true));
        cardExpenseItemsList.add(generateExpenseRow("Fijo e Internet","Jazztel",40,true));
        cardExpenseItemsList.add(generateExpenseRow("Agua","Canal de Isabel II",15,true));
        cardExpenseItemsList.add(generateExpenseRow("Gas","HolaGas",20,true));
        cardExpenseItemsList.add(generateGroupHeader(R.drawable.icon_transport,"Transporte",56));
        cardExpenseItemsList.add(generateExpenseRow("Abono","Metro",56,true));
        cardExpenseItemsList.add(generateGroupHeader(R.drawable.icon_food,"Comida",163));
        cardExpenseItemsList.add(generateExpenseRow("Mercadona","Primer finde del mes",89,true));
        cardExpenseItemsList.add(generateExpenseRow("Alcampo","Segundo finde del mes",74,true));
        cardExpenseItemsList.add(generateGroupHeader(R.drawable.icon_other,"Otros",50));
        cardExpenseItemsList.add(generateExpenseRow("Regalos","Regalo para Javi",50,true));

        return new CardExpenseCollection(cardExpenseItemsList);
    }

    public static CardExpenseCollection getNotPaidCollection(){
        List<CardExpenseItem> cardExpenseItemsList = new ArrayList<>();
        cardExpenseItemsList.add(generateGroupHeader(R.drawable.icon_transport,"Transporte",250));
        cardExpenseItemsList.add(generateExpenseRow("Coche","Letra mensual",250,false));
        cardExpenseItemsList.add(generateGroupHeader(R.drawable.icon_shoppings,"Compras",395));
        cardExpenseItemsList.add(generateExpenseRow("H&M","camiseta y pantalon",95,false));
        cardExpenseItemsList.add(generateExpenseRow("Game","Ps4",300,false));
        cardExpenseItemsList.add(generateGroupHeader(R.drawable.icon_leisure,"Ocio",50));
        cardExpenseItemsList.add(generateExpenseRow("Cervezas Amigos","",50,false));

        return new CardExpenseCollection(cardExpenseItemsList);
    }


    private static CardExpenseItem generateGroupHeader(int icon,String name,float amount){
        CardExpenseItem groupHeader = new CardExpenseItem();
        groupHeader.setGroupHeader(true);
        groupHeader.setIcon(icon);
        groupHeader.setName(name);
        groupHeader.setAmount(amount);
        return groupHeader;
    }
    private static CardExpenseItem generateExpenseRow(String name,String description,
                                               float amount, boolean paid){
        CardExpenseItem expenseRow = new CardExpenseItem();
        expenseRow.setExpenseRow(true);
        expenseRow.setName(name);
        expenseRow.setDescription(description);
        expenseRow.setAmount(amount);
        expenseRow.setPaid(paid);
        return expenseRow;
    }

}
