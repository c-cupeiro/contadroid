package org.upv.ccupeiro.contadroid.actualmonth.view.builder;

import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import org.upv.ccupeiro.contadroid.common.domain.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.actualmonth.view.renderer.CardExpenseHeaderRenderer;
import org.upv.ccupeiro.contadroid.actualmonth.view.renderer.CardExpenseRowRenderer;
import org.upv.ccupeiro.contadroid.common.view.listener.CardExpenseListener;

import java.util.LinkedList;
import java.util.List;


public class CardExpenseItemBuilder extends RendererBuilder<CardExpenseItem> {

    private CardExpenseListener rowListener;

    public CardExpenseItemBuilder(CardExpenseListener rowListener) {
        this.rowListener = rowListener;
        List<Renderer<CardExpenseItem>> prototypes = getRendererCardExpenseItemPrototypes();
        setPrototypes(prototypes);
    }

    @Override
    protected Class getPrototypeClass(CardExpenseItem content) {
        Class prototypeClass;
        if(content.isGroupHeader()){
            prototypeClass = CardExpenseHeaderRenderer.class;
        }else{
            prototypeClass = CardExpenseRowRenderer.class;
        }
        return prototypeClass;
    }

    public List<Renderer<CardExpenseItem>> getRendererCardExpenseItemPrototypes() {
        List<Renderer<CardExpenseItem>> prototypes = new LinkedList<>();
        CardExpenseHeaderRenderer headerRenderer = new CardExpenseHeaderRenderer();
        prototypes.add(headerRenderer);

        CardExpenseRowRenderer rowRenderer = new CardExpenseRowRenderer(rowListener);
        prototypes.add(rowRenderer);

        return prototypes;
    }
}
