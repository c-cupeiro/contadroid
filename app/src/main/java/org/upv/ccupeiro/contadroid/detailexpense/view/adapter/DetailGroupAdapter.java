package org.upv.ccupeiro.contadroid.detailexpense.view.adapter;

import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import org.upv.ccupeiro.contadroid.common.model.ExpensesGroup;
import org.upv.ccupeiro.contadroid.detailexpense.model.ExpenseGroupView;


public class DetailGroupAdapter extends RVRendererAdapter<ExpenseGroupView> {
    public DetailGroupAdapter(RendererBuilder<ExpenseGroupView> rendererBuilder,
                              AdapteeCollection<ExpenseGroupView> collection) {
        super(rendererBuilder, collection);
    }

    public void markGroupInAdapter(ExpensesGroup group) {
        for(int positionGroup = 0; positionGroup<getItemCount();positionGroup++){
            ExpenseGroupView item = getItem(positionGroup);
            if(item.getGroupType() == group) {
                item.setSelected(true);
                break;
            }
        }
    }

    public ExpensesGroup getSelectedGroup(){
        for(int positionGroup = 0; positionGroup<getItemCount();positionGroup++){
            ExpenseGroupView item = getItem(positionGroup);
            if(item.isSelected())
                return item.getGroupType();
        }
        return ExpensesGroup.EMPTY;
    }
}
