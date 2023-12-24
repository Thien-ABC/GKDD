package com.example.gkdd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import hanghoaUI.hienthihanghoa;
import hanghoaUI.nhaphanghoa;
import loaihanghoaUI.hienthiloaihanghoa;
import model.HangHoa;
import model.LoaiHangHoa;


public class chonmenu extends AppCompatActivity {
    private  final int loaihanghoaview = 114;

    private  final int REQUEST_CODE_ACTIVITY2 = 114;
    Button btnchonlhh, btnchonhanghoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chonmenu);
        addconTrol();
        addEvent();
    }

    private void addEvent() {
        btnchonhanghoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chonmenu.this, nhaphanghoa.class);

                startActivityForResult(intent, REQUEST_CODE_ACTIVITY2);
            }
        });
        btnchonlhh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chonmenu.this, hienthiloaihanghoa.class);
                startActivityForResult(intent, loaihanghoaview);
            }
        });
    }

    private void addconTrol() {
        btnchonhanghoa = findViewById(R.id.btn_open_hanghoa);
        btnchonlhh=findViewById(R.id.btn_open_themloai);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Kiểm tra nếu kết quả trả về từ Activity 3
        if(REQUEST_CODE_ACTIVITY2==requestCode) {
            if (data.hasExtra("trahh")) {
                // Xử lý dữ liệu từ Activity 3 (nếu có)
                HangHoa hh = (HangHoa) data.getSerializableExtra("trahh");
                // Xử lý dữ liệu nhận được
                // Trả dữ liệu về cho Activity 1 (MainActivity)
                Intent resultIntent = new Intent();
                resultIntent.putExtra("trahh", hh);
                setResult(REQUEST_CODE_ACTIVITY2, resultIntent);
                finish();
            }
        }
        if (data.hasExtra("tralhh")) {
            // Xử lý dữ liệu từ Activity 3 (nếu có)
            LoaiHangHoa hh = (LoaiHangHoa) data.getSerializableExtra("tralhh");
            // Xử lý dữ liệu nhận được
            // Trả dữ liệu về cho Activity 1 (MainActivity)
            Intent resultIntent = new Intent();
            resultIntent.putExtra("tralhh", hh);
            setResult(113, resultIntent);
            finish();
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(chonmenu.this, hienthihanghoa.class);
        startActivity(intent);
    }
}