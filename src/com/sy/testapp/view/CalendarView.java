/**
 * @项目名称：TestApp
 * @文件名：CalendarView.java
 * @版本信息：
 * @日期：2015年10月8日
 * @Copyright 2015 GALAXYWIND Network Systems Co.,Ltd.All rights reserved.
 */
package com.sy.testapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.sy.testapp.R;
import com.sy.testapp.util.DateUtil;
import com.sy.testapp.util.log.Log;

/**
 * @项目名称：TestApp
 * @类名称：CalendarView
 * @类描述：自定义日历控件，显示一个月的日历，样式：
 *              Jan
 *              
 *            1  2  3  4  5
 *      6  7  8  9  10 11 12
 *      13 14 15 16 17 18 19
 *      20 21 22 23 24 25 26
 *      27 28 29 30 31
 * @version
 */
public class CalendarView extends View {
    private static final int TOTAL_COL = 7; // 7列
    
    private static final int TOTAL_ROW = 6; // 6行
    
    private Paint mCirclePaint; // 绘制圆形的画笔
    
    private TextPaint mTextPaint; // 绘制文本的画笔
    
    private int mViewWidth; // 视图的宽度
    
    private int mViewHeight; // 视图的高度
    
    private int mCellSpace; // 单元格间距
    
    private Row rows[] = new Row[TOTAL_ROW]; // 行数组，每个元素代表一行
    
    private static CustomDate mShowDate; // 自定义的日期，包括year,month,day
    
    private OnCellClickListener mCellClickListener; // 单元格点击回调事件
    
    private int touchSlop; //滑动最小距离
    
    private boolean callBackCellSpace;
    
    private Cell mClickCell;
    
    private float mDownX;
    
    private float mDownY;
    
    private int mMonth = 0;
    private int mSelectedDay;
    private static final int DEF_MAIN_COLOR = 0xFFF24949; // 红色
    private static final float TITLE_OFFSET = 10;
    private static final float TITLE_SIZE = 18;
    private static final String[] MonDescEn = {"Jan","Feb","Mar","Apr",
                                               "May","Jun","Jul","Aug",
                                               "Sep","Oct","Nov","Dec"};
    private float mTitleHeight;
    private float mDensity;
    private int mTitleColor = DEF_MAIN_COLOR;
    private boolean mShowTitle = true; 
    private int mSelectedColor = DEF_MAIN_COLOR;
    /**
     * @项目名称：TestApp    
     * @类名称：OnCellClickListener    
     * @类描述：单元格点击回调接口  
     * @version
     */
    public interface OnCellClickListener {
        void clickDate(CustomDate date); // 回调点击的日期
    }
    
    public void setOnCellClickListener(OnCellClickListener cellClickListener) {
        this.mCellClickListener = cellClickListener;
    }
    
    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(context, attrs);
        init(context);
    }
    
    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(context, attrs);
        init(context);
    }
    
    public CalendarView(Context context) {
        super(context);
        init(context);
    }
    
    private void getAttrs(Context context, AttributeSet attrs) {
        if (null == attrs) {
            return;
        }
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CalendarView);
        mMonth = a.getInt(R.styleable.CalendarView_setMonth, mMonth);
        mShowTitle = a.getBoolean(R.styleable.CalendarView_showMonthTitle, mShowTitle);
        mSelectedColor = a.getColor(R.styleable.CalendarView_selectedDayColor, mSelectedColor);
        Log.MyLog.i("mMonth=" + mMonth);
        a.recycle();
    }
    
    private void init(Context context) {
        mDensity = context.getResources().getDisplayMetrics().density;
        
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(mSelectedColor);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        
        if (!mShowTitle) {
            mTitleHeight = 0;
        }
        
        initDate();
    }
    
    private void initDate() {
        mShowDate = new CustomDate();
        if (0 < mMonth && mMonth <= 12) {
            mShowDate.setMonth(mMonth);
        }
        if (0 < mSelectedDay && mSelectedDay <= 31) {
            mShowDate.setDay(mSelectedDay);
        }
        fillDate();
    }
    
    private void fillDate() {
        int monthDay = DateUtil.getCurrentMonthDay(); // 今天
        int lastMonthDays = DateUtil.getMonthDays(mShowDate.year, mShowDate.month - 1); // 上个月的天数
        int currentMonthDays = DateUtil.getMonthDays(mShowDate.year, mShowDate.month); // 当前月的天数
        int firstDayWeek = DateUtil.getWeekDayFromDate(mShowDate.year, mShowDate.month);
        boolean isCurrentMonth = false;
        if (DateUtil.isCurrentMonth(mShowDate)) {
            isCurrentMonth = true;
        }
        int day = 0;
        for (int j = 0; j < TOTAL_ROW; j++) {
            rows[j] = new Row(j);
            for (int i = 0; i < TOTAL_COL; i++) {
                int position = i + j * TOTAL_COL; // 单元格位置
                // 当前月
                if (position >= firstDayWeek && position < firstDayWeek + currentMonthDays) {
                    day++;
                    rows[j].cells[i] = new Cell(CustomDate.modifiDayForObject(mShowDate, day), State.CURRENT_MONTH_DAY, i, j);
                    // 今天
                    if (isCurrentMonth && day == monthDay) {
                        CustomDate date = CustomDate.modifiDayForObject(mShowDate, day);
                        rows[j].cells[i] = new Cell(date, State.TODAY, i, j);
                    }
                    
                    if (isCurrentMonth && day > monthDay) { // 如果比这个月的今天要大，表示还没到
                        rows[j].cells[i] = new Cell(CustomDate.modifiDayForObject(mShowDate, day), State.UNREACH_DAY, i, j);
                    }
                }
                // 上一个月
                else if (position < firstDayWeek) {
                    rows[j].cells[i] = new Cell(new CustomDate(mShowDate.year,
                                                               mShowDate.month - 1,
                                                               lastMonthDays - (firstDayWeek - position - 1)), State.PAST_MONTH_DAY, i, j);
                }
                // 下一个月
                else if (position >= firstDayWeek + currentMonthDays) {
                    rows[j].cells[i] = new Cell((new CustomDate(mShowDate.year, mShowDate.month + 1, position - firstDayWeek
                                                                                                     - currentMonthDays
                                                                                                     + 1)), State.NEXT_MONTH_DAY, i, j);
                }
            }
        }
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float defSize = mTextPaint.getTextSize();
        if (mShowTitle) {
            if (0 < mMonth && mMonth <= 12) {
                mTextPaint.setTextSize(defSize * 2.5f);
                mTextPaint.setColor(mTitleColor);
                FontMetrics fm = mTextPaint.getFontMetrics();
                float titleW = mTextPaint.measureText(MonDescEn[mMonth-1] + "");
                float startX = (getWidth() - titleW) / 2;
                mTitleHeight = (int) (fm.bottom - fm.top + TITLE_OFFSET * mDensity);
                Rect targetRect = new Rect(0, 0, getWidth(), (int) mTitleHeight);
                float startY = targetRect.top + (targetRect.bottom - targetRect.top - fm.bottom + fm.top) / 2 - fm.top;
                canvas.drawText(MonDescEn[mMonth-1] + "", startX, startY, mTextPaint);
            }
        }
        mTextPaint.setTextSize(defSize * 1.8f);
        for (int i = 0; i < TOTAL_ROW; i++) {
            if (rows[i] != null) {
                rows[i].drawCells(canvas);
            }
        }
        mTextPaint.setTextSize(defSize);
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        mCellSpace = (int) Math.min((mViewHeight - mTitleHeight) / TOTAL_ROW, mViewWidth / TOTAL_COL);
        if (!callBackCellSpace) {
            callBackCellSpace = true;
        }
        mTextPaint.setTextSize(mCellSpace / 3);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                float disX = event.getX() - mDownX;
                float disY = event.getY() - mDownY;
                if (Math.abs(disX) < touchSlop && Math.abs(disY) < touchSlop) {
                    int col = (int) (mDownX / mCellSpace);
                    int row = (int) ((mDownY - mTitleHeight) / mCellSpace);
                    measureClickCell(col, row);
                }
                break;
            default:
                break;
        }
        
        return true;
    }
    
    /**
     * @description 计算点击的单元格
     * @date 2015年10月9日
     * @param col
     * @param row
     */
    private void measureClickCell(int col, int row) {
        if (col >= TOTAL_COL || row >= TOTAL_ROW)
            return;
        if (mClickCell != null) {
            rows[mClickCell.j].cells[mClickCell.i] = mClickCell;
        }
        if (rows[row] != null) {
            mClickCell = new Cell(rows[row].cells[col].date, rows[row].cells[col].state, rows[row].cells[col].i, rows[row].cells[col].j);
            
            CustomDate date = rows[row].cells[col].date;
            date.week = col;
            if (null != mCellClickListener) {
                mCellClickListener.clickDate(date);
            }
            // 刷新界面
            update();
        }
    }
    
    /**
     * @项目名称：TestApp    
     * @类名称：Row    
     * @类描述：组元素，一行为一组  
     * @version
     */
    class Row {
        public int j;
        
        Row(int j) {
            this.j = j;
        }
        
        public Cell[] cells = new Cell[TOTAL_COL];
        
        // 绘制单元格
        public void drawCells(Canvas canvas) {
            for (int i = 0; i < cells.length; i++) {
                if (cells[i] != null) {
                    cells[i].drawSelf(canvas);
                }
            }
        }
        
    }
    
    /**
     * @项目名称：TestApp    
     * @类名称：Cell    
     * @类描述：单元格元素  
     * @version
     */
    class Cell {
        public CustomDate date;
        
        public State state;
        
        public int i;
        
        public int j;
        
        public Cell(CustomDate date, State state, int i, int j) {
            super();
            this.date = date;
            this.state = state;
            this.i = i;
            this.j = j;
        }
        
        public void drawSelf(Canvas canvas) {
            switch (state) {
                case TODAY: // 今天
                    mTextPaint.setColor(Color.WHITE);
                    // TODO 偏移0.88位置才正确？
                    canvas.drawCircle((float) (mCellSpace * (i + 0.5)), (float) ((j + 0.5) * mCellSpace + mTitleHeight*0.88), mCellSpace / 2, mCirclePaint);
                    break;
                case CURRENT_MONTH_DAY: // 当前月日期
                    mTextPaint.setColor(Color.BLACK);
                    break;
                case PAST_MONTH_DAY: // 上一个月
                case NEXT_MONTH_DAY: // 下一个月
                    mTextPaint.setColor(Color.WHITE);
                    break;
                case UNREACH_DAY: // 还未到的天
                    mTextPaint.setColor(Color.BLACK);
                    break;
                default:
                    break;
            }
            // 绘制文字
            String content = date.day + "";
            canvas.drawText(content,
                            (float) ((i + 0.5) * mCellSpace - mTextPaint.measureText(content) / 2),
                            (float) ((j + 0.7) * mCellSpace - mTextPaint.measureText(content, 0, 1) / 2 + mTitleHeight),
                            mTextPaint);
        }
    }
    
    /**
     * @项目名称：TestApp    
     * @类名称：State    
     * @类描述：单元格的状态 当前月日期，过去的月的日期，下个月的日期  
     * @version
     */
    enum State {
        TODAY, CURRENT_MONTH_DAY, PAST_MONTH_DAY, NEXT_MONTH_DAY, UNREACH_DAY;
    }
    
    /**
     * @description 从左往右划，上一个月
     * @date 2015年10月9日
     */
    public void leftSlide() {
        if (mShowDate.month == 1) {
            mShowDate.month = 12;
            mShowDate.year -= 1;
        }
        else {
            mShowDate.month -= 1;
        }
        update();
    }
    
    /**
     * @description 从右往左划，下一个月
     * @date 2015年10月9日
     */
    public void rightSlide() {
        if (mShowDate.month == 12) {
            mShowDate.month = 1;
            mShowDate.year += 1;
        }
        else {
            mShowDate.month += 1;
        }
        update();
    }
    
    public void update() {
        fillDate();
        invalidate();
    }
    
    public void setMonth(int month) {
        mMonth = month;
    }
    
    public void setSelectedDay(int day) {
        mSelectedDay = day;
    }
}
