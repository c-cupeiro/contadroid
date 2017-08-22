package org.upv.ccupeiro.contadroid.common.data.datasource;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.model.Expense;
import org.upv.ccupeiro.contadroid.common.model.ExpensesGroup;

import java.util.LinkedList;
import java.util.List;


public class SimpleContadroidRepository implements ContadroidRepository {
    private final List<Expense> expenseList;

    private static SimpleContadroidRepository instance;

    public static SimpleContadroidRepository getInstance(){
        if(instance == null){
            instance = new SimpleContadroidRepository();
        }
        return instance;
    }

    private SimpleContadroidRepository() {
        this.expenseList = new LinkedList<>();
        fillPaid();
        fillNotPaid();
    }

    private void fillPaid(){
        expenseList.add(createExpense(0,"Sueldo","ingreso del trabajo",2000,true,ExpensesGroup.INCOME));
        expenseList.add(createExpense(1,"Alquiler","",300,true,ExpensesGroup.HOME));
        expenseList.add(createExpense(2,"Fijo e Interne","Jazztel",40,true,ExpensesGroup.HOME));
        expenseList.add(createExpense(3,"Agua","Canal de Isabel II",15,true,ExpensesGroup.HOME));
        expenseList.add(createExpense(4,"Gas","HolaGas",20,true,ExpensesGroup.HOME));
        expenseList.add(createExpense(5,"Abono","Metro",56,true,ExpensesGroup.TRANSPORT));
        expenseList.add(createExpense(6,"Mercadona","Primer finde de mes",89,true,ExpensesGroup.FOOD));
        expenseList.add(createExpense(7,"Alcampo","Segundo finde de mes",74,true,ExpensesGroup.FOOD));
        expenseList.add(createExpense(8,"Regalos","Regalo para Javi",50,true,ExpensesGroup.OTHER));
    }

    private void fillNotPaid(){
        expenseList.add(createExpense(9,"H&M","camiseta y pantalon",95,false,ExpensesGroup.SHOPPING));
        expenseList.add(createExpense(10,"Game","Ps4",300,false,ExpensesGroup.SHOPPING));
        expenseList.add(createExpense(11,"Cervezas Amigos","",50,false,ExpensesGroup.LEISURE));
        expenseList.add(createExpense(12,"Coche","Letra",250,false,ExpensesGroup.TRANSPORT));
    }

    private Expense createExpense(int id, String name, String description, float amount, boolean isPaid, ExpensesGroup group){
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
        List<Expense> paidList = new LinkedList<>();
        for(Expense expense: expenseList){
            if(expense.isPaid())
                paidList.add(expense);
        }
        return paidList;
    }

    @Override
    public List<Expense> getNotPaidExpensesInMonth(int year, int month) {
        List<Expense> notPaidList = new LinkedList<>();
        for(Expense expense: expenseList){
            if(!expense.isPaid())
                notPaidList.add(expense);
        }
        return notPaidList;
    }

    @Override
    public List<Expense> getYearExpenses(int year) {
        return expenseList;
    }


    @Override
    public List<Expense> getTemplate() {
        return null;
    }

    @Override
    public boolean saveTemplate(List<Expense> template) {
        return false;
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