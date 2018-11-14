package restaurent.manzoor.restaurentapp.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Utilz {
    static ProgressDialog dialog;

    public static boolean isInternetConnected(Context c) {
        ConnectivityManager cm = (ConnectivityManager) c
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }


    public static boolean isValidEmail1(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target)
                    .matches();
        }
    }

    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager inputManager = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            // check if no view has focus:
            View view = activity.getCurrentFocus();
            if (view != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
            // Ignore exceptions if any
            Log.e("KeyBoardUtil", e.toString(), e);
        }
    }







    public static void displayMessageAlert(String Message, Context context) {
        try {
            new AlertDialog.Builder(context).setMessage(Message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                }
            }).create().show();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public static void logDisplay(String Message, Context context) {
        if (Message != null) {
            if (Message.trim().length() > 0 && context != null) {
                Log.d("PJ", Message);
            }
        }
    }
    public static void HideSoftkeyPad(EditText textfiled, Context c) {
        InputMethodManager imm = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(textfiled.getWindowToken(), 0);
    }

    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    public static String getCurrentTime() {
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
        String currentDateTimeString = sdf.format(d);
        return currentDateTimeString;
    }

    public static String getCurrentDateInDigit() {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    public static Date getDateFromString() {
        Date parsedDate = null;
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        try {
             parsedDate = DATE_FORMAT.parse(getCurrentDateInDigit());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }
    public static Date getDateFromString(String stringDate) {
        Date parsedDate = null;
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        try {
             parsedDate = DATE_FORMAT.parse(stringDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }


    public static void showProgress(Context context) {
        if (dialog != null && dialog.isShowing()) {
            return;
        }

        dialog = ProgressDialog.show(context, null, "Please wait...", true, true);

    }

    public static void dismissProgressDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }
    public static String GetDateTimeDifference(Date startDate, Date endDate){

        //milliseconds
        long different = startDate.getTime() - endDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long monthInMilli = daysInMilli * 30;

        long elapsedMonths = different / monthInMilli;
        different = different % monthInMilli;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;
        long totalSecond = (elapsedSeconds + (60*elapsedMinutes) + (3600*elapsedHours) + (86400*elapsedDays) + (2592000*elapsedMonths));

        Log.i("elapsedMonths",""+elapsedMonths);
        Log.i("elapsedDays",""+elapsedDays);
        Log.i("elapsedHours",""+elapsedHours);
        Log.i("elapsedMinutes",""+elapsedMinutes);
        Log.i("elapsedSeconds",""+elapsedSeconds);
        if (totalSecond<60){
            return "now";
        }
        if ((totalSecond>=60) && (!(totalSecond>=120))){
            return elapsedMinutes+" min ago";
        }
        if ((totalSecond>=120) && (!(totalSecond>=3600))){
            return elapsedMinutes+" mins ago";
        }
        if ((totalSecond>=3600) && (!(totalSecond>=7200))){
            return elapsedHours+" hour ago";
        }
        if ((totalSecond>=7200) && (!(totalSecond>=86400))){
            return elapsedHours+" hours ago";
        }
        if ((totalSecond>=86400) && (totalSecond<172800)){
            return elapsedDays+" day ago";
        }
        if ((totalSecond>=172800) && (!(totalSecond>=2592000))){
            return elapsedDays+" days ago";
        }
        if ((totalSecond>=2592000) && (!(totalSecond>=5184000))){
            return elapsedMonths+" month ago";
        }
        if ((totalSecond>=5184000) && (!(totalSecond<31104000))){
            return elapsedMonths+" months ago";
        }
        else {
            return ""+startDate;
        }


    }

    public static String getStatus(String order_staus) {
        if (order_staus.equals("0")){
            return "Pending";
        }
        else if (order_staus.equals("1")){
            return "Accepted";
        }
        else if (order_staus.equals("2")){
            return "Delivered";
        }
        else if (order_staus.equals("3")){
            return "Canceled";
        }
        return "";
    }
}

