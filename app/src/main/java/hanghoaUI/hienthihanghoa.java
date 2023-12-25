package hanghoaUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gkdd.MainActivity;
import com.example.gkdd.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import adapter.AdapterHanghoa;
import dao.DBHelper;
import dao.DBHelperlhh;
import loaihanghoaUI.hienthiloaihanghoa;
import loaihanghoaUI.nhapLoaihanghoa;
import model.HangHoa;


public class hienthihanghoa extends AppCompatActivity {

    private static final int REQUEST_CODE_ACTIVITY2 =115 ;
    FloatingActionButton fa;
    AdapterHanghoa adapter;
    ListView listView;
    DBHelper helper;
    DBHelperlhh helplhh;
    List<HangHoa> listHanghoa=new ArrayList<>();
    HangHoa chon;
    Button btnLoai;
    int requestUpdate=114,resultcode=115;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hienthihanghoa);
        addControls();
        addEvent();
        helper=new DBHelper(hienthihanghoa.this);
        helplhh=new DBHelperlhh(hienthihanghoa.this);
 //       helper.QueryData(DBHelper.Drop_table);
  //      helper.QueryData(DBHelper.SQL_Create_Table);

        hienthiHanghoa();
        chon=null;
    }

    private void addEvent() {
        fa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent=new Intent(hienthihanghoa.this, nhaphanghoa.class);
                startActivityForResult(intent,requestcode);*/
                Intent intent=new Intent(hienthihanghoa.this, nhaphanghoa.class);
               startActivityForResult(intent,REQUEST_CODE_ACTIVITY2);
            }
        });
        btnLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(hienthihanghoa.this, hienthiloaihanghoa.class);
                startActivityForResult(intent,REQUEST_CODE_ACTIVITY2);
            }
        });
    }

    private void addControls() {
        fa=findViewById(R.id.faThem);
        listView = findViewById(R.id.lvQlhh);
        registerForContextMenu(listView);
        btnLoai = findViewById(R.id.title2);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId()==R.id.lvQlhh){
            getMenuInflater().inflate(R.menu.context_menu,menu);
        }
    }
    // chon nut sua va xoa
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index=info.position;
        if(item.getItemId()==R.id.btnsua)
        {
            Intent intent=new Intent(hienthihanghoa.this, nhaphanghoa.class);
            chon=adapter.getItem(index);
            intent.putExtra("chon",chon);
            startActivityForResult(intent,REQUEST_CODE_ACTIVITY2);
        }
        else if(item.getItemId()==R.id.btnxoa){
           xulyxoa(index);
        }
        return super.onContextItemSelected(item);
    }

    private  void capnhathanghoa(Intent intent)
    {
        if (intent.hasExtra("trahh")) {
            if (helper.isManvExists(String.valueOf(chon.getMaHang()))) {
                helper.updateHanghoa(chon);
                hienthiHanghoa();
            }
        }
    }
    @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==this.resultcode) {
                chon=(HangHoa) data.getSerializableExtra("trahh");
                if(helper.isManvExists(String.valueOf(chon.getMaHang())))
                {
                    capnhathanghoa(data);
                }
                else {
                    helper.insertHanghoa(chon);
                    hienthiHanghoa();
                }

            }
        }

    private void hienthiHanghoa() {
        listHanghoa=helper.getAllHanghoa();
        if(listHanghoa.size()>=0){
            adapter=new AdapterHanghoa(hienthihanghoa.this,R.layout.item_hanghoa,listHanghoa,helper);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(hienthihanghoa.this, MainActivity.class);
        startActivity(intent);
    }
    private void xulyxoa(int index) {
        chon = adapter.getItem(index);
        AlertDialog.Builder dialog = new AlertDialog.Builder(hienthihanghoa.this);
        dialog.setTitle("Xác nhận xóa");
        dialog.setMessage("Bạn có muốn xóa không?");
        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                helper.deleteHanghoa(chon.getMaHang());
                hienthiHanghoa();
            }
        });
        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });
        dialog.show();
    }
}