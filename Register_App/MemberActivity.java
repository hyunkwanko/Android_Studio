package com.example.hyeongwan.code;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MemberActivity extends AppCompatActivity {

    ArrayList<Member> memberArrayList = new ArrayList<>();

    // arraylist 에 name을 가진 member 가 있는지 체크해주는 함수
    public Member checkMember(String name){
        for(int i = 0; i < memberArrayList.size(); i++)
            if(name.equals(memberArrayList.get(i).name))
                return memberArrayList.get(i);
        return null;
    }

    // member 추가
    public boolean addMember(Member m){
        if (memberArrayList.size() < 5) {
            memberArrayList.add(m);
            Toast.makeText(getApplicationContext(), "Add Member",
                    Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Full Member",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText name = (EditText)findViewById(R.id.name);
        final EditText tel = (EditText)findViewById(R.id.tel);
        final EditText email = (EditText)findViewById(R.id.email);
        final EditText dept = (EditText)findViewById(R.id.dept);


        findViewById(R.id.buttonadd).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        // EditText 내용을 가지고 Member라는 인스턴스 생성
                        Member member = new Member(name.getText().toString(), tel.getText().toString(), email.getText().toString(), dept.getText().toString());
                        Member cMember = checkMember(member.name);
                        if(cMember == null){ // 멤버가 존재하지 않으면
                            if(!addMember(member)) // 멤버가 추가
                                Toast.makeText(getApplicationContext(), "Add Error",
                                    Toast.LENGTH_SHORT).show();
                        }else{ // 멤버가 존재한다면 cMember 의 변수들을 editText 에 넣어주면 됨
                            name.setText(cMember.name);
                            tel.setText(cMember.phoneNumber);
                            email.setText(cMember.email);
                            dept.setText(cMember.sns);
                            Toast.makeText(getApplicationContext(), "Already Exist",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        findViewById(R.id.buttondel).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        // EditText 내용을 가지고 Member라는 인스턴스 생성
                        Member cMember = checkMember(name.getText().toString());

                        if(cMember == null){ // 멤버가 존재하지 않으면
                            Toast.makeText(getApplicationContext(), "Delete Error",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            memberArrayList.remove(cMember);
                            Toast.makeText(getApplicationContext(), "Delete Complete",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }
}