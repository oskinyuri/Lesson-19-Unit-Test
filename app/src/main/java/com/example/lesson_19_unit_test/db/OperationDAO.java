package com.example.lesson_19_unit_test.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.lesson_19_unit_test.Entity.Operation;

import java.util.List;

@Dao
public interface OperationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addOperation(Operation operation);

    @Update
    void update(Operation operation);

    @Query("select * from Operation")
    List<Operation> getOperationHistory();

    @Query("delete from Operation")
    void clearOperationHistory();
}
