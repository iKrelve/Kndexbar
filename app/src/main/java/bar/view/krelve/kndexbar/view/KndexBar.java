package bar.view.krelve.kndexbar.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by wwjun.wang on 2015/7/31.
 */
public class KndexBar extends LinearLayout {
    private float mHeightPerItem;
    private OnIndexChangedListener onIndexChangedListener;

    public KndexBar(Context context) {
        this(context, null);
    }

    public KndexBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public void setIndexDatas(final List<String> indexes) {
        removeAllViews();
        //match_parent = -1,wrap_content = -2
        LayoutParams params = new LayoutParams(-1, -2, 1);
        for (int i = 0; i < indexes.size(); i++) {
            TextView tv_index = new TextView(getContext());
            tv_index.setGravity(Gravity.CENTER);
            tv_index.setTextColor(0xff1ba9ba);
            tv_index.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
            tv_index.setText(indexes.get(i));
            tv_index.setLayoutParams(params);
            addView(tv_index);

        }
        setTouchDelegate(new TouchDelegate(new Rect(), this) {
            @Override
            public boolean onTouchEvent(MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        setBackgroundColor(0xffffffff);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        setBackgroundColor(0x00ffffff);//正常状态背景是透明的
                        break;
                }
                for (int i = 0; i < getChildCount(); i++) {
                    TextView tv_index = (TextView) getChildAt(i);
                    tv_index.setTextColor(0xff1ba9ba);
                }
                if (event.getY() >= 0) {
                    int index = (int) (event.getY() / mHeightPerItem);
                    TextView tv = (TextView) getChildAt(index);
                    tv.setTextColor(getResources().getColor(android.R.color.holo_orange_light));
                    if (onIndexChangedListener != null) {
                        onIndexChangedListener.onIndexChanged(index,indexes.get(index));
                    }
                }
                return true;
            }
        });

    }

    public void setOnIndexChangedListener(OnIndexChangedListener onIndexChangedListener) {
        this.onIndexChangedListener = onIndexChangedListener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (getHeight() != 0) {
            mHeightPerItem = getHeight() * 1.0f / getChildCount();
        }
    }

    public interface OnIndexChangedListener {
        void onIndexChanged(int index,CharSequence data);
    }
}
