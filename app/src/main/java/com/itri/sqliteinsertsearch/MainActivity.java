package com.itri.sqliteinsertsearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13;
     TextView tv1;
     DBHP dbhp;
     EditText et2, et3, et4, et5;
     ArrayList<String> list;
     ArrayList<DbData> AL;
     int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        btn10 = findViewById(R.id.button10);
        btn11 = findViewById(R.id.button11);
        btn12 = findViewById(R.id.button12);
        btn13 = findViewById(R.id.button13);
        tv1 = findViewById(R.id.textView);
        et2 = findViewById(R.id.editText2);
        et3 = findViewById(R.id.editText3);
        et4 = findViewById(R.id.editText4);
        et5 = findViewById(R.id.editText5);
        if(dbhp == null)
            dbhp = new DBHP(MainActivity.this);
        AL = dbhp.queryALL();
        showData(index);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhp.insertDB();
                Toast.makeText(MainActivity.this, "新增完成", Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               list = dbhp.queryDB();
               StringBuilder sb = new StringBuilder("查詢結果如下:\n");
               if (list.size() > 0){
                   for (int i = 0; i < list.size(); i++) {
                       sb.append(list.get(i) + "\n");
                   }
               }else{
                   sb.append("查無資料");
               }
               tv1.setText(sb);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = et2.getText().toString();
                String name = et3.getText().toString();
                String phoneNo = et4.getText().toString();
                String address = et5.getText().toString();
                dbhp.insertDB(id, name, phoneNo, address);
                Toast.makeText(MainActivity.this, "新增完成....", Toast.LENGTH_SHORT).show();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = et2.getText().toString();
                String name = et3.getText().toString();
                String phoneNo = et4.getText().toString();
                String address = et5.getText().toString();
                DbData data = new DbData(id, name, phoneNo, address);
                dbhp.insertDB(data);
                Toast.makeText(MainActivity.this, "新增完成......", Toast.LENGTH_SHORT).show();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = index -1;
                if (index < 0){
                    index = AL.size() - 1;
                }
                showData(index);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = index + 1;
                if ((index >= AL.size())){
                    index = 0;
                }
                showData(index);
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id =  et2.getText().toString();
                String name =  et3.getText().toString();
                String phoneNo =  et4.getText().toString();
                String address =  et5.getText().toString();
                DbData data = new DbData(id, name, phoneNo, address);
                long rowId =  dbhp.insertDBMethod(data);
                if (rowId == -1)
                    Toast.makeText(MainActivity.this, "新增失敗!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "新增成功!", Toast.LENGTH_SHORT).show();
                AL = dbhp.queryALL();
                index = 0;
                showData(0);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id =  et2.getText().toString();
                String name =  et3.getText().toString();
                String phoneNo =  et4.getText().toString();
                String address =  et5.getText().toString();
                DbData data = new DbData(id, name, phoneNo, address);
                dbhp.updateDB(data);
                AL = dbhp.queryALL();
                showData(0);
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id =  et2.getText().toString();
                String name =  et3.getText().toString();
                String phoneNo =  et4.getText().toString();
                String address =  et5.getText().toString();
                dbhp.updateDB(id, name, phoneNo, address);
                AL = dbhp.queryALL();
                showData(0);
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id =  et2.getText().toString();
                String name =  et3.getText().toString();
                String phoneNo =  et4.getText().toString();
                String address =  et5.getText().toString();
                DbData data = new DbData(id, name, phoneNo, address);
                int count = dbhp.updateMethod(data);
                Toast.makeText(MainActivity.this, "更新" +count+"筆", Toast.LENGTH_SHORT).show();
                AL = dbhp.queryALL();
                showData(0);
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id =  et2.getText().toString();
                String name =  et3.getText().toString();
                String phoneNo =  et4.getText().toString();
                String address =  et5.getText().toString();
                DbData data = new DbData(id, name, phoneNo, address);
                dbhp.deleteDB(data);
                AL = dbhp.queryALL();
                showData(0);
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = et5.getText().toString();
                dbhp.deleteDB(address);
                AL = dbhp.queryALL();
                showData(0);
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id =  et2.getText().toString();
                String name =  et3.getText().toString();
                String phoneNo =  et4.getText().toString();
                String address =  et5.getText().toString();
                DbData data = new DbData(id, name, phoneNo, address);
                int count =  dbhp.deleteDBMethod(data);
                Toast.makeText(MainActivity.this, "刪除" +count+"筆", Toast.LENGTH_SHORT).show();
                AL = dbhp.queryALL();
                showData(0);
            }
        });

    }

    private void showData(int index) {
        if (AL.size() > 0){
            tv1.setText((index + 1) + "  / " + AL.size() + "---第幾筆/總比數");
            et2.setText(AL.get(index).getId());
            et3.setText(AL.get(index).getName());
            et4.setText(AL.get(index).getPhoneNo());
            et5.setText(AL.get(index).getAddress());
        }else{
            tv1.setText("0/0 ---第幾筆/總比數");
            et2.setText("");
            et3.setText("");
            et4.setText("");
            et5.setText("");
        }
    }
}
