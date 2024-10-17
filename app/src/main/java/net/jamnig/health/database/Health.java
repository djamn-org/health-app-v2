package net.jamnig.health.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Defines schema for the Health Data
 */
@Entity
public class Health {
    @PrimaryKey(autoGenerate = true) // automatically generate uid
    public int uid;

    @ColumnInfo(name = "health_water")
    public int waterAmount;

    @ColumnInfo(name = "health_food")
    public int foodAmount;

    @ColumnInfo(name = "health_steps")
    public int lastStepsAmount;

    @ColumnInfo(name = "health_date")
    public String date;
}
