package com.example.weightrecord.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "dailyWeights")
public class Weight {
    @PrimaryKey(autoGenerate = true )
    int Uid;

    @ColumnInfo(name="User_Height")
    String Height;
    @ColumnInfo(name="User_weight")
    String Weight;
    Date date;

    @ColumnInfo(typeAffinity=ColumnInfo.BLOB)
    byte[] image;



    public void setUid(int uid) {
        Uid = uid;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getHeight() {
        return Height;
    }


    public int getUid() {
        return Uid;
    }

    public String getWeight() {
        return Weight;
    }

    public Date getDate() {
        return date;
    }

    public byte[] getImage() {
        return image;
    }

}
