package com.example.weightrecord.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@TypeConverters(DataConverter.class)
@Database(
        entities = {Weight.class},
        version =2,
        exportSchema = false
)

public abstract class AppDatabase  extends RoomDatabase {
//    private static AppDatabase weightDB=null;
//    public abstract WeightDao weightDao();
//    public static synchronized  AppDatabase getDBINSTANCE(Context context){
//        if(weightDB==null){
//            weightDB=Room.databaseBuilder(
//                    context.getApplicationContext(),
//                    AppDatabase.class,
//                    "weight19b2"
//
//            ).allowMainThreadQueries()
//                    .build();
//        }
//public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//    @Override
//    public void migrate(@NonNull SupportSQLiteDatabase database) {
//        database.execSQL("ALTER TABLE Repo "
//                + "ADD COLUMN html_url TEXT");
//    }
//};
//    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE Repo "
//                    + "ADD COLUMN html_url DATE");
//        }
//    };
        public abstract WeightDao weightDao();
    private static AppDatabase INSTANCE;
    public static AppDatabase getDbInstance(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"DB NAME")
                    .allowMainThreadQueries()
                    .build();
//            INSTANCE=Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"DB NAME")
//                    .addMigrations(MIGRATION_1_2)
//                    .build();
//            INSTANCE=Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"DB NAME")
//                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
//                    .build();
        }
        return INSTANCE;
    }
//        return weightDB;
    }


//    public abstract WeightDao userDao();
//    private static AppDatabase INSTANCE;
//    public static AppDatabase getDbInstance(Context context){
//        if(INSTANCE==null){
//            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"DB NAME")
//                    .allowMainThreadQueries()
//                    .build();
//        }
//        return INSTANCE;
//    }



