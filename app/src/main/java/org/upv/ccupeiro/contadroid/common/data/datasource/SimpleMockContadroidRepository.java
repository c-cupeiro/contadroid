package org.upv.ccupeiro.contadroid.common.data.datasource;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.model.Expense;
import org.upv.ccupeiro.contadroid.common.model.ExpensesGroup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class SimpleMockContadroidRepository implements ContadroidRepository {
    private List<Expense> expenseList;

    private static SimpleMockContadroidRepository instance;

    public static SimpleMockContadroidRepository getInstance(){
        if(instance == null){
            instance = new SimpleMockContadroidRepository();
        }
        return instance;
    }

    private SimpleMockContadroidRepository() {
        this.expenseList = new ArrayList<>();
        fillMockTemplate();
        fillMockPaid();
        fillMockNotPaid();
    }

    private void fillMockTemplate(){
        expenseList.add(createTemplate(0,"Sueldo","ingreso del trabajo",2000,ExpensesGroup.INCOME));
        expenseList.add(createTemplate(1,"Alquiler","",300,ExpensesGroup.HOME));
        expenseList.add(createTemplate(2,"Fijo e Interne","Jazztel",40,ExpensesGroup.HOME));
        expenseList.add(createTemplate(3,"Agua","Canal de Isabel II",15,ExpensesGroup.HOME));
        expenseList.add(createTemplate(4,"Gas","HolaGas",20,ExpensesGroup.HOME));
        expenseList.add(createTemplate(5,"Abono","Metro",56,ExpensesGroup.TRANSPORT));
        expenseList.add(createTemplate(6,"Coche","Letra",250,ExpensesGroup.TRANSPORT));
    }

    private void fillMockPaid(){
        expenseList.add(createExpense(7,"Sueldo","ingreso del trabajo",2000,true,ExpensesGroup.INCOME));
        expenseList.add(createExpense(8,"Alquiler","",300,true,ExpensesGroup.HOME));
        expenseList.add(createExpense(9,"Fijo e Interne","Jazztel",40,true,ExpensesGroup.HOME));
        expenseList.add(createExpense(10,"Agua","Canal de Isabel II",15,true,ExpensesGroup.HOME));
        expenseList.add(createExpense(11,"Gas","HolaGas",20,true,ExpensesGroup.HOME));
        expenseList.add(createExpense(12,"Abono","Metro",56,true,ExpensesGroup.TRANSPORT));
        expenseList.add(createExpense(13,"Mercadona","Primer finde de mes",89,true,ExpensesGroup.FOOD));
        expenseList.add(createExpense(14,"Alcampo","Segundo finde de mes",74,true,ExpensesGroup.FOOD));
        expenseList.add(createExpense(15,"Regalos","Regalo para Javi",50,true,ExpensesGroup.OTHER));
    }

    private void fillMockNotPaid(){
        expenseList.add(createExpense(16,"H&M","camiseta y pantalon",95,false,ExpensesGroup.SHOPPING));
        expenseList.add(createExpense(17,"Game","Ps4",300,false,ExpensesGroup.SHOPPING));
        expenseList.add(createExpense(18,"Cervezas Amigos","",50,false,ExpensesGroup.LEISURE));
        expenseList.add(createExpense(19,"Coche","Letra",250,false,ExpensesGroup.TRANSPORT));
    }

    private Expense createTemplate(int id, String name, String description, float amount,
                                  ExpensesGroup group){
        Expense.Builder expense = new Expense.Builder()
                .withId(id)
                .withName(name)
                .withDescription(description)
                .withAmount(amount)
                .isTemplate()
                .withGroup(group);

        return expense.build();
    }

    private Expense createExpense(int id, String name, String description, float amount,
                                  boolean isPaid, ExpensesGroup group){
        Expense.Builder expense = new Expense.Builder()
                .withId(id)
                .withName(name)
                .withDescription(description)
                .withAmount(amount)
                .withGroup(group);
        if(isPaid)
            expense.isPaid();
        return expense.build();
    }

    @Override
    public List<Expense> getPaidExpensesInMonth(int year, int month) {
        List<Expense> paidList = new ArrayList<>();
        for(Expense expense: expenseList){
            if(expense.isPaid() && !expense.isTemplate())
                paidList.add(expense);
        }
        return paidList;
    }

    @Override
    public List<Expense> getNotPaidExpensesInMonth(int year, int month) {
        List<Expense> notPaidList = new ArrayList<>();
        for(Expense expense: expenseList){
            if(!expense.isPaid() && !expense.isTemplate())
                notPaidList.add(expense);
        }
        return notPaidList;
    }

    @Override
    public List<Expense> getYearExpenses(int year) {
        List<Expense> yearExpense = new ArrayList<>();
        for(Expense expense: expenseList){
            if(expense.isPaid() && !expense.isTemplate())
                yearExpense.add(expense);
        }
        return yearExpense;
    }


    @Override
    public List<Expense> getTemplate() {
        List<Expense> templateList = new ArrayList<>();
        for(Expense expense: expenseList){
            if(!expense.isTemplate())
                templateList.add(expense);
        }
        return templateList;
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
    public boolean saveExpense(Expense expense) {
        if(expense.getId()!=-1){
            return updateEditedExpense(expense);
        }else{
            return saveNewExpense(expense);
        }
    }

    private boolean saveNewExpense(Expense expense) {
        Expense lastExpense = expenseList.get(expenseList.size()-1);
        expense.setId(lastExpense.getId()+1);
        expenseList.add(expense);
        return true;
    }

    private boolean updateEditedExpense(Expense expense) {
        int position = getPosition(expense.getId());
        if(position != -1){
            expenseList.set(position,expense);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteExpense(int id) {
        int position = getPosition(id);
        if(position != -1){
            expenseList.remove(position);
            return true;
        }
        return false;
    }

    @Override
    public boolean changePaidState(int id, boolean paid) {
        int position = getPosition(id);
        if(position != -1){
            Expense expense = expenseList.get(position);
            expense.setPaid(paid);
            expenseList.set(position,expense);
            return true;
        }
        return false;
    }

    private int getPosition(int idExpense){
        for(int pos = 0; pos < expenseList.size();pos++){
            if(expenseList.get(pos).getId()==idExpense){
                return pos;
            }
        }
        return -1;
    }
}
