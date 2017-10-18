package com.cosfund.library.libdb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.j256.ormlite.field.DatabaseField;

/**
 * 作者 by Gavin on 2015/12/29 0029.
 * 描述：
 * ormlite-android 案例bean
 */
public class SimpleData {

    // id is generated by the database and set on the object automagically
    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField(index = true)
    String string;
    @DatabaseField
    long millis;
    @DatabaseField
    Date date;
    @DatabaseField
    boolean even;

    SimpleData() {
        // needed by ormlite
    }

    public SimpleData(long millis) {
        this.date = new Date(millis);
        this.string = (millis % 1000) + "ms";
        this.millis = millis;
        this.even = ((millis % 2) == 0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id);
        sb.append(", ").append("str=").append(string);
        sb.append(", ").append("ms=").append(millis);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd HH:mm:ss", Locale.US);
        sb.append(", ").append("date=").append(dateFormatter.format(date));
        sb.append(", ").append("even=").append(even);
        return sb.toString();
    }
}
