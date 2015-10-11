/** 
 * @项目名称：TestApp   
 * @文件名：CustomDate.java    
 * @版本信息：
 * @日期：2015年10月8日    
 * @Copyright 2015 GALAXYWIND Network Systems Co.,Ltd.All rights reserved.         
 */
package com.sy.testapp.view;

import java.io.Serializable;

import com.sy.testapp.util.DateUtil;

/**       
 * @项目名称：TestApp    
 * @类名称：CustomDate    
 * @类描述：    
 * @创建人：Administrator    
 * @创建时间：2015年10月8日 下午8:40:52    
 * @修改人：Administrator    
 * @修改时间：2015年10月8日 下午8:40:52    
 * @修改备注：    
 * @version          
 */
public class CustomDate implements Serializable {
    private static final long serialVersionUID = 1L;
    public int year;
    public int month;
    public int day;
    public int week;
    
    public CustomDate(int year,int month,int day){
        if(month > 12){
            month = 1;
            year++;
        }else if(month <1){
            month = 12;
            year--;
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    public CustomDate(){
        this.year = DateUtil.getYear();
        this.month = DateUtil.getMonth();
        this.day = DateUtil.getCurrentMonthDay();
    }
    
    public static CustomDate modifiDayForObject(CustomDate date,int day){
        CustomDate modifiDate = new CustomDate(date.year,date.month,day);
        return modifiDate;
    }
    @Override
    public String toString() {
        return year+"-"+month+"-"+day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}
