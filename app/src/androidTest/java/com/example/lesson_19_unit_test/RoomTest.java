package com.example.lesson_19_unit_test;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.lesson_19_unit_test.Entity.Operation;
import com.example.lesson_19_unit_test.db.OperationDAO;
import com.example.lesson_19_unit_test.db.OperationDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class RoomTest {

    private OperationDAO mOperationDAO;
    private OperationDatabase mOperationDatabase;

    @Before
    public void createDb(){
        Context context = InstrumentationRegistry.getTargetContext();
        mOperationDatabase = Room.inMemoryDatabaseBuilder(context, OperationDatabase.class).build();
        mOperationDAO = mOperationDatabase.getOperationDB();
    }

    @After
    public void closeDb(){
        mOperationDatabase.close();
    }

    @Test
    public void addAndGetOperation(){
        Operation operation = new Operation();
        operation.setOperation("5 + 5");

        mOperationDAO.addOperation(operation);
        List<Operation> operationList = mOperationDAO.getOperationHistory();

        assertEquals(operation, operationList.get(0));
    }
}
