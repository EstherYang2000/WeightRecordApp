//package com.example.weightrecord.data;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//
//public class FooFormatter implements ValueFormatter {
//
//    private long referenceTimestamp; // minimum timestamp
//    private DateFormat mDataFormat;
//    private Date mDate;
//
//    public FooFormatter(long referenceTimestamp) {
//        this.referenceTimestamp = referenceTimestamp;
//        this.mDataFormat = new SimpleDateFormat( "HH:mm", Locale.ENGLISH );
//        this.mDate = new Date();
//    }
//
//    @Override
//    public String getFormattedValue(float value) {
//        // convertedTimestamp = originalTimestamp - referenceTimestamp
//        long convertedTimestamp = (long) value;
//
//        // Retrieve original timestamp
//        long originalTimestamp = referenceTimestamp + convertedTimestamp;
//
//        // Convert timestamp
//        return getDateString(originalTimestamp);
//    }
//
//    private String getDateString(long timestamp) {
//        try {
//            mDate.setTime(timestamp * 1000);
//            return mDataFormat.format(mDate);
//        } catch(Exception ex) {
//            return "xx";
//        }
//    }
//
//}
