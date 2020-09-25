package com.example.newapp_to_do_list.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Utility {


    public String getCurrentDate(int date1) {
        Calendar calendar = Calendar.getInstance();
        String dateCurrent = null;
        int date = date1;
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);


        dateCurrent = date + "-" + month + "-" + year;

        return dateCurrent;
    }



}