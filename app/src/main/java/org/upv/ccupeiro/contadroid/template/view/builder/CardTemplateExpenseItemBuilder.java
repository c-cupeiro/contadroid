package org.upv.ccupeiro.contadroid.template.view.builder;

import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import org.upv.ccupeiro.contadroid.actualmonth.view.renderer.CardExpenseHeaderRenderer;
import org.upv.ccupeiro.contadroid.actualmonth.view.renderer.CardExpenseRowRenderer;
import org.upv.ccupeiro.contadroid.common.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.common.view.listener.CardExpenseListener;
import org.upv.ccupeiro.contadroid.template.view.renderer.CardTemplateExpenseRowRenderer;

import java.util.LinkedList;
import java.util.List;


public class CardTemplateExpenseItemBuilder extends RendererBuilder<CardExpenseItem> {

    private CardExpenseListener rowListener;

    public CardTemplateExpenseItemBuilder(CardExpenseListener rowListener) {
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
            prototypeClass = CardTemplateExpenseRowRenderer.class;
        }
        return prototypeClass;
    }

    public List<Renderer<CardExpenseItem>> getRendererCardExpenseItemPrototypes() {
        List<Renderer<CardExpenseItem>> prototypes = new LinkedList<>();
        CardExpenseHeaderRenderer headerRenderer = new CardExpenseHeaderRenderer();
        prototypes.add(headerRenderer);

        CardTemplateExpenseRowRenderer rowRenderer = new CardTemplateExpenseRowRenderer(rowListener);
        prototypes.add(rowRenderer);

        return prototypes;
    }
}
