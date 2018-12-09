package com.example.hyunkwan.Assignment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {
    myDBHelper myHelper;
    SQLiteDatabase sqlDB;
    Button complete, lookup;
    EditText sname, studentID, sID, password, password_check, semail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        myHelper = new myDBHelper(this);
        complete = (Button)findViewById(R.id.complete);
        lookup = (Button)findViewById(R.id.lookup);

        sname = (EditText)findViewById(R.id.sname);
        studentID = (EditText)findViewById(R.id.studentID);
        sID = (EditText)findViewById(R.id.sID);
        password = (EditText)findViewById(R.id.password);
        password_check = (EditText)findViewById(R.id.password_check);
        semail = (EditText)findViewById(R.id.semail);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = sname.getText().toString();
                String student = studentID.getText().toString();
                String id = sID.getText().toString();
                String pass = password.getText().toString();
                String pass_chk = password_check.getText().toString();
                String email = semail.getText().toString();

                System.out.println(name);
                System.out.println(student);
                System.out.println(id);

                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL (SName) VALUES ('test')");
//                db.execSQL("insert into mytable (name) values('Seo');");

                sqlDB.close();
            }
        });

        lookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor = sqlDB.rawQuery("SELECT * FROM groupTBL WHERE SName ='test'", null);

                while(cursor.moveToNext()){
                    sname.setText(cursor.getString(0));
                    studentID.setText(cursor.getString(0));
                    sID.setText(cursor.getString(0));
                    password.setText(cursor.getString(0));
                    password_check.setText(cursor.getString(0));
                    semail.setText(cursor.getString(0));
                }
                cursor.close();
                sqlDB.close();
            }
        });

    }

    public class myDBHelper extends SQLiteOpenHelper{
        public myDBHelper(Context context){
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            //TODO Auto-generated method stub
            db.execSQL("CREATE TABLE groupTBL (SName CHAR(20) PRIMARY KEY," +
                    "StudentID CHAR(20), ID CHAR(20), Password CHAR(20), " +
                    "Password_check CHAR(20), SEmail CHAR(30));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            //TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }
}

