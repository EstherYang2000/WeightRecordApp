package com.example.weightrecord.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WeightDao {

    @Query("SELECT * FROM dailyWeights")
    List<Weight> getAllWeights();

    @Insert
    void insertWeight(Weight weight);

    @Update
    void updateWeight(Weight weight);

    @Delete
//    @Query("DELETE FROM dailyWeights WHERE Uid = :Uid")
    void deleteWeight(Weight weight);

}
