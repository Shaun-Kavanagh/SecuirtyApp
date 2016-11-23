package com.example.zach.myapp2;

/**
 * Created by Dave on 11/11/2016.
 */
import java.io.PrintWriter;
import java.io.StringWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

public class ExceptionHandler implements
        Thread.UncaughtExceptionHandler {
    private final Activity myContext;
    private final String LINE_SEPARATOR = "\n";

    public ExceptionHandler(Activity context) {
        myContext = context;
    }

    public void uncaughtException(Thread thread, Throwable exception) {
        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        StringBuilder errorReport = new StringBuilder();
        errorReport.append("************ CAUSE OF ERROR ************\n\n");
        errorReport.append(stackTrace.toString());

        errorReport.append("\n************ DEVICE INFORMATION ***********\n");
        errorReport.append("Brand: ");
        errorReport.append(Build.BRAND);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Device: ");
        errorReport.append(Build.DEVICE);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Model: ");
        errorReport.append(Build.MODEL);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Id: ");
        errorReport.append(Build.ID);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Product: ");
        errorReport.append(Build.PRODUCT);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("\n************ FIRMWARE ************\n");
        errorReport.append("SDK: ");
        errorReport.append(Build.VERSION.SDK);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Release: ");
        errorReport.append(Build.VERSION.RELEASE);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Incremental: ");
        errorReport.append(Build.VERSION.INCREMENTAL);
        errorReport.append(LINE_SEPARATOR);

        Intent intent = new Intent(myContext, Checker.class);
        intent.putExtra("error", errorReport.toString());
        myContext.startActivity(intent);

        Intent intent1 = new Intent(myContext, Entries.class);
        intent1.putExtra("error", errorReport.toString());
        myContext.startActivity(intent1);

        Intent intent2 = new Intent(myContext, Entry.class);
        intent2.putExtra("error", errorReport.toString());
        myContext.startActivity(intent2);

        Intent intent3 = new Intent(myContext, Generation.class);
        intent3.putExtra("error", errorReport.toString());
        myContext.startActivity(intent3);

        Intent intent4 = new Intent(myContext, MainActivity.class);
        intent4.putExtra("error", errorReport.toString());
        myContext.startActivity(intent4);

        Intent intent5 = new Intent(myContext, SignUp.class);
        intent5.putExtra("error", errorReport.toString());
        myContext.startActivity(intent5);

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }

}