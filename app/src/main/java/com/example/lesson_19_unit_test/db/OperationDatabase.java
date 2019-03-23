package com.example.lesson_19_unit_test.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.lesson_19_unit_test.Entity.Operation;

@Database(entities = Operation.class, version = 1, exportSchema = false)
public abstract class OperationDatabase extends RoomDatabase {
    public abstract OperationDAO getOperationDB();
}
