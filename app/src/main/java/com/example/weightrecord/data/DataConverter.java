package com.example.weightrecord.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class DataConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }
    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
    public static byte[] convertImage2ByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        return stream.toByteArray();

    }

    public static Bitmap convertByteArray2Image(byte [] array){
        return BitmapFactory.decodeByteArray(array,0,array.length);
    }
}
