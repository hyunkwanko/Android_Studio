package com.example.hyunkwan.Assignment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class Audio extends AppCompatActivity {
    MediaPlayer mp; // 음악 재생을 위한 객체
    int pos; // 재생 멈춘 시점
    Button bStart, bPause, bRestart, bStop, back;
    SeekBar sb;
    boolean isPlaying = false;

    class MyThread extends Thread {
        @Override
        public void run() { // 쓰레드가 시작되면 콜백되는 메서드
            // 씨크바 막대기 조금씩 움직이기 (노래 끝날 때까지 반복)
            while(isPlaying) {
                sb.setProgress(mp.getCurrentPosition());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        sb = (SeekBar)findViewById(R.id.seekBar1);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                isPlaying = true;
                int ttt = seekBar.getProgress(); // 사용자가 움직여놓은 위치
                mp.seekTo(ttt);
                mp.start();
                new MyThread().start();
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                isPlaying = false;
                mp.pause();
            }
            public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser) {
                if (seekBar.getMax()==progress) {
                    isPlaying = false;
                    mp.stop();
                }
            }


        });

        bStart = (Button)findViewById(R.id.button1);
        bPause = (Button)findViewById(R.id.button2);
        bRestart=(Button)findViewById(R.id.button3);
        bStop  = (Button)findViewById(R.id.button4);
        back  = (Button)findViewById(R.id.back);

        final Intent homeScreen =  new Intent(this, HomeScreen.class);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(homeScreen);
            }
        });

        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MediaPlayer 객체 초기화 , 재생
                mp = MediaPlayer.create(
                        getApplicationContext(), // 현재 화면의 제어권자
                        R.raw.song1); // 음악파일
                mp.setLooping(true); // true:무한반복
                mp.start(); // 노래 재생 시작

                int a = mp.getDuration(); // 노래의 재생시간(miliSecond)
                sb.setMax(a);// 씨크바의 최대 범위를 노래의 재생시간으로 설정
                new MyThread().start(); // 씨크바 그려줄 쓰레드 시작
                isPlaying = true; // 씨크바 쓰레드 반복 하도록
            }
        });

        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 음악 종료
                isPlaying = false; // 쓰레드 종료

                mp.stop(); // 멈춤
                mp.release(); // 자원 해제
                sb.setProgress(0); // 씨크바 초기화
            }
        });

        bPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 일시중지
                pos = mp.getCurrentPosition();
                mp.pause(); // 일시중지
                isPlaying = false; // 쓰레드 정지
            }
        });
        bRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 멈춘 지점부터 재시작
                mp.seekTo(pos); // 일시정지 시점으로 이동
                mp.start(); // 시작
                isPlaying = true; // 재생하도록 flag 변경
                new MyThread().start(); // 쓰레드 시작
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPlaying = false; // 쓰레드 정지
        if (mp!=null) {
            mp.release(); // 자원해제
        }
    }
}