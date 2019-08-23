package it.cristopher.tabbarcheckout;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BarraStatoCheckOut extends RelativeLayout {
    private Context context;
    private AttributeSet attrs;
    private int styleAttr;
    private View view;

    TextView firstElement;
    TextView secondElement;
    TextView thirdElement;

    Drawable drawableActive;
    Drawable drawableInactive;

    private int backTabColor;
    private int textColorActive;
    private int textColorInactive;
    private int itemTabActive;
    private int itemTabInactive;
    private int itemActive;


    public BarraStatoCheckOut(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public BarraStatoCheckOut(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;
        initView();
    }

    public BarraStatoCheckOut(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.attrs = attrs;
        this.styleAttr = defStyleAttr;
        initView();
    }

    private void initView() {
        this.view = this;
        inflate(context, R.layout.layout, this);
        final Resources res = getResources();
        final int coloreBackgoundTabDef = res.getColor(R.color.backgroung_tab);
        final int textColorActiveDef = res.getColor(R.color.testo_active);
        final int textColorInactiveDef = res.getColor(R.color.testo_inactive);
        final int itemTabActiveDef = res.getColor(R.color.tab_item_active);
        final int itemTabInactiveDef = res.getColor(R.color.tab_item_inactive);
        final Drawable defaultDrawable = res.getDrawable(R.drawable.bordi_arrotondati);
        drawableActive = res.getDrawable(R.drawable.bordi_arrotondati);
        drawableInactive = res.getDrawable(R.drawable.bordi_arrotondati);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BarraStatoCheckOut, styleAttr, 0);
        backTabColor = array.getColor(R.styleable.BarraStatoCheckOut_backgroudTabColor, coloreBackgoundTabDef);
        textColorActive = array.getColor(R.styleable.BarraStatoCheckOut_textColorActive, textColorActiveDef);
        textColorInactive = array.getColor(R.styleable.BarraStatoCheckOut_textColorInactive, textColorInactiveDef);
        itemTabActive = array.getColor(R.styleable.BarraStatoCheckOut_tabColorActive, itemTabActiveDef);
        itemTabInactive = array.getColor(R.styleable.BarraStatoCheckOut_tabColorInactive, itemTabInactiveDef);
        itemActive = array.getInt(R.styleable.BarraStatoCheckOut_item_active, 1);

        firstElement = (TextView)findViewById(R.id.uno_element_barra);
        secondElement = (TextView)findViewById(R.id.due_element_barra);
        thirdElement = (TextView)findViewById(R.id.tre_element_barra);
        LinearLayout mLayout = (LinearLayout) findViewById(R.id.layout_barra_stato);

        if (Build.VERSION.SDK_INT > 16) {
            defaultDrawable.setColorFilter(new PorterDuffColorFilter(backTabColor, PorterDuff.Mode.SRC));
            mLayout.setBackground(defaultDrawable);

        } else{
            defaultDrawable.setColorFilter(new PorterDuffColorFilter(backTabColor, PorterDuff.Mode.SRC));
            mLayout.setBackgroundDrawable(defaultDrawable);;

        }
        this.attivazioneItem();
        array.recycle();
    }

    public void changeItemActive(int item){
        this.itemActive = item;
        this.attivazioneItem();
    }

    private void attivazioneItem(){
        drawableActive.setColorFilter(new PorterDuffColorFilter(itemTabActive, PorterDuff.Mode.SRC));
        drawableInactive.setColorFilter(new PorterDuffColorFilter(itemTabInactive, PorterDuff.Mode.SRC));
        if (itemActive < 1 || itemActive > 3 || itemActive == 1){
            firstElement.setBackground(drawableActive);
            firstElement.setTextColor(textColorActive);
            secondElement.setBackground(drawableInactive);
            secondElement.setTextColor(textColorInactive);
            thirdElement.setBackground(drawableInactive);
            thirdElement.setTextColor(textColorInactive);
        } else if(itemActive == 2) {
            firstElement.setTextColor(textColorInactive);
            secondElement.setTextColor(textColorActive);
            thirdElement.setTextColor(textColorInactive);
            secondElement.setBackground(drawableActive);
            firstElement.setBackground(drawableInactive);
            thirdElement.setBackground(drawableInactive);
        } else if (itemActive == 3){
            firstElement.setBackground(drawableInactive);
            firstElement.setTextColor(textColorInactive);
            secondElement.setBackground(drawableInactive);
            secondElement.setTextColor(textColorInactive);
            thirdElement.setBackground(drawableActive);
            thirdElement.setTextColor(textColorActive);
        }
    }


}
