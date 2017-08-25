package org.upv.ccupeiro.contadroid.summary.view.renderer;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedrogomez.renderers.Renderer;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.common.utils.StringUtils;
import org.upv.ccupeiro.contadroid.template.model.SummaryItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SummaryItemRenderer extends Renderer<SummaryItem>{
    private final int colorPositive = Color.parseColor("#3eef31");
    private final int colorNegative = Color.parseColor("#ec2323");
    private final int colorNeutral = Color.parseColor("#2b2a2a");

    @BindView(R.id.tv_summary_month)
    TextView month;
    @BindView(R.id.tv_summary_amount)
    TextView amount;
    @BindView(R.id.iv_summary_status)
    ImageView status;

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.summary_row_renderer, parent, false);

        ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }

    @Override
    public void render() {
        SummaryItem summaryItem = getContent();
        renderMonth(summaryItem);
        renderAmount(summaryItem);
        renderStatus(summaryItem);
    }

    private void renderMonth(SummaryItem summaryItem) {
        month.setText(summaryItem.getName());
    }

    private void renderAmount(SummaryItem summaryItem) {
        switch (summaryItem.getStatus()){
            case POSITIVE:
                amount.setText(StringUtils.formatAmount(summaryItem.getAmount()));
                amount.setTextColor(colorPositive);
                break;
            case NEGATIVE:
                amount.setText(StringUtils.formatAmount(summaryItem.getAmount()));
                amount.setTextColor(colorNegative);
                break;
            case NEUTRAL:
                amount.setText(StringUtils.formatAmount(summaryItem.getAmount()));
                amount.setTextColor(colorNegative);
                break;
            default:
                amount.setText("No hay datos");
                amount.setTextColor(colorNegative);
                break;
        }
    }

    private void renderStatus(SummaryItem summaryItem) {
        switch (summaryItem.getStatus()){
            case POSITIVE:
                status.setImageResource(R.drawable.rectangle_positive);
                break;
            case NEGATIVE:
                status.setImageResource(R.drawable.rectangle_negative);
                break;
            default:
                status.setImageResource(R.drawable.rectangle_neutral);
                break;
        }
    }

    @Override
    protected void setUpView(View rootView) {

    }

    @Override
    protected void hookListeners(View rootView) {

    }
}
