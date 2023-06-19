package lk.RoyalGatesHotels.util;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTime {
    public static String setDate(String date){

        String monthName = getMonthName(date);
        int year = Integer.parseInt(date.split("-")[0]);
        int day = Integer.parseInt(date.split("-")[2]);

        String today = year+"-"+monthName+"-"+day;
        return today;
    }


    public static void localTime(Label time){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        event -> time.setText(
                                new SimpleDateFormat("hh:mm").format(Calendar.getInstance().getTime()))),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static String getMonthName(String date){

        //String date = String.valueOf(dteDate.getValue());
        int month = Integer.parseInt(date.split("-")[1]);

        String monthName=null;

        switch (month){
            case 1:
                monthName="January";
                break;

            case 2:
                monthName="February";
                break;

            case 3:
                monthName="March";
                break;

            case 4:
                monthName="April";
                break;

            case 5:
                monthName="May";
                break;

            case 6:
                monthName="June";
                break;

            case 7:
                monthName="July";
                break;

            case 8:
                monthName="August";
                break;

            case 9:
                monthName="September";
                break;

            case 10:
                monthName="October";
                break;

            case 11:
                monthName="November";
                break;

            case 12:
                monthName="December";
                break;

        }
        return monthName;
    }
}
