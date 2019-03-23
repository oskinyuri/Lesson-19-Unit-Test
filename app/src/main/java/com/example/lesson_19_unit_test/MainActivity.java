package com.example.lesson_19_unit_test;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lesson_19_unit_test.Entity.Operation;
import com.example.lesson_19_unit_test.db.OperationDAO;
import com.example.lesson_19_unit_test.db.OperationDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String OPERATION_DATABASE = "OPERATION_DATABASE";

    private TextView mCurrentOperationTv;
    private TextView mResultTv;
    private TextView mLastOperationTv;

    private EditText mFirstOperandEt;
    private EditText mSecondOperandEt;

    private Button mPlustBtn;
    private Button mMinusBtn;
    private Button mDivBtn;
    private Button mMultBtn;
    private Button mRestetBtn;
    private Button mEqualityBtn;

    // Значение по умолчанию
    private float mFirstOperand = 0;
    private float mSecondOperand = 0;

    // Операция по умолчанию
    private int mCurrentOperation = Calculator.OPERATION_PLUS;

    private Calculator mCalculator;
    private Operation mOperation;

    private Handler mHandler;
    private ExecutorService mExecutorService;

    private OperationDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();

        mCalculator = new Calculator();
        mOperation = new Operation();

        mHandler = new Handler(Looper.getMainLooper());
        mExecutorService = Executors.newSingleThreadScheduledExecutor();

        mDatabase = Room.databaseBuilder(this, OperationDatabase.class, OPERATION_DATABASE)
                .fallbackToDestructiveMigration()
                .build();
    }

    private void initViews() {
        mCurrentOperationTv = findViewById(R.id.calc_operation_tv);
        mLastOperationTv = findViewById(R.id.calc_last_operation_tv);
        mResultTv = findViewById(R.id.calc_result_tv);

        mFirstOperandEt = findViewById(R.id.calc_first_operand_et);
        mSecondOperandEt = findViewById(R.id.calc_second_operand_et);

        mPlustBtn = findViewById(R.id.calc_btn_plus);
        mMinusBtn = findViewById(R.id.calc_btn_minus);
        mDivBtn = findViewById(R.id.calc_btn_divide);
        mMultBtn = findViewById(R.id.calc_btn_multiplication);
        mEqualityBtn = findViewById(R.id.calc_btn_equality);
        mRestetBtn = findViewById(R.id.calc_btn_reset);
    }

    private void initListeners() {
        mFirstOperandEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(""))
                    mFirstOperand = Float.valueOf(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mSecondOperandEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(""))
                    mSecondOperand = Float.valueOf(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPlustBtn.setOnClickListener((view) -> {
            mCurrentOperation = Calculator.OPERATION_PLUS;
            mCurrentOperationTv.setText("+");
        });
        mMinusBtn.setOnClickListener((view) -> {
            mCurrentOperation = Calculator.OPERATION_MINUS;
            mCurrentOperationTv.setText("-");
        });
        mMultBtn.setOnClickListener((view) -> {
            mCurrentOperation = Calculator.OPERATION_MULTIPLICATION;
            mCurrentOperationTv.setText("*");
        });
        mDivBtn.setOnClickListener((view) -> {
            mCurrentOperation = Calculator.OPERATION_DIVIDE;
            mCurrentOperationTv.setText("/");
        });

        mRestetBtn.setOnClickListener((view) -> {
            mFirstOperandEt.setText("");
            mSecondOperandEt.setText("");
            mResultTv.setText("Result");
        });

        mEqualityBtn.setOnClickListener((view -> {
            if (mFirstOperandEt.getText().toString().equals("")) mFirstOperand = 0;
            if (mSecondOperandEt.getText().toString().equals("")) mSecondOperand = 0;
            mResultTv.setText(String.valueOf(mCalculator.calculate(mFirstOperand, mSecondOperand, mCurrentOperation)));

            mOperation.setOperation(mCalculator.getLastOperation());
            addOperationsInBD(mOperation);
        }));
    }

    /**
     * Добавление последней операции в БД
     */
    private void addOperationsInBD(Operation operation) {
        mExecutorService.execute(() -> {
            mDatabase.getOperationDB().addOperation(operation);
            updateLastOperation();
        });
    }

    /**
     * Обновление последней операции
     */
    private void updateLastOperation() {
        mExecutorService.execute(() -> {
            List<Operation> list = mDatabase.getOperationDB().getOperationHistory();
            mOperation = list.get(list.size() - 1);
            mHandler.post(() -> mLastOperationTv.setText(mOperation.getOperation()));
        });
    }

}
