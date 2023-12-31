package dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.HangHoa;


public class DBHelper extends SQLiteOpenHelper {


    private static final String DB_Name = "dbhanghoa.sqlite";
    private static final int DB_Version = 1;

    public static class TABLE implements BaseColumns {
        public static final String TABLE_NAME = "Hanghoa";
        public static final String COLUMN_M = "MAHANG";
        public static final String COLUMN_Ten = "TENHANG";
        public static final String COLUMN_Phanloai = "PHANLOAI";
        public static final String COLUMN_hinhanh = "HINHANH";
        public static final String COLUMN_Gia = "GIA";
        public static final String COLUMN_SIZE = "DUNGTICH";
    }


    public static final String SQL_Create_Table = "CREATE TABLE IF NOT EXISTS " + TABLE.TABLE_NAME + " (" +
            TABLE.COLUMN_M + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TABLE.COLUMN_Ten + " VARCHAR(50), " +
            TABLE.COLUMN_Phanloai + " VARCHAR(50), " +
            TABLE.COLUMN_hinhanh + " TEXT, " +
            TABLE.COLUMN_Gia + " REAL, " +
            TABLE.COLUMN_SIZE + " REAL)";



    public  static  final String Drop_table = "DROP TABLE IF EXISTS " + TABLE.TABLE_NAME;


    public void QueryData(String sql)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
    public DBHelper(Context context) {
        super(context, DB_Name, null, DB_Version);
    }


    public void insertHanghoa(HangHoa hh) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
       // values.put(TABLE.COLUMN_M, hh.getMaHang());
        values.put(TABLE.COLUMN_Ten, hh.getTenHang());
        values.put(TABLE.COLUMN_Phanloai, hh.getTenloai());
        // Nếu hh.getHinhanh() trả về đường dẫn của hình ảnh, bạn có thể lưu đường dẫn vào cột COLUMN_hinhanh
        if(hh.getHinhanh()!=null) {
            values.put(TABLE.COLUMN_hinhanh, hh.getHinhanh()); // Nếu getHinhanh trả về đường dẫn hình ảnh
        }
        values.put(TABLE.COLUMN_Gia, hh.getGia());
        values.put(TABLE.COLUMN_SIZE, hh.getSize());

        // Thực hiện lệnh SQL chèn dữ liệu vào cơ sở dữ liệu
        long newRow = db.insert(TABLE.TABLE_NAME, null, values);

        // Kiểm tra xem dữ liệu đã được chèn thành công hay không
        if (newRow == -1) {
            // Có thể xảy ra lỗi khi chèn dữ liệu
            Log.e("DBHelper", "Error inserting data into table.");
        } else {
            // Dữ liệu đã được chèn thành công
            Log.i("DBHelper", "Data inserted successfully.");
        }
    }

    public List<HangHoa> getAllHanghoa() {
        List<HangHoa> listHangHoa = new ArrayList<>();
            String[] projection = {
                    TABLE.COLUMN_M,
                    TABLE.COLUMN_Ten,
                    TABLE.COLUMN_Phanloai,
                    TABLE.COLUMN_hinhanh,
                    TABLE.COLUMN_Gia,
                    TABLE.COLUMN_SIZE
            };
            Cursor cursor = getReadableDatabase().query(
                    TABLE.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            while (cursor.moveToNext()) {
                HangHoa hangHoa = new HangHoa();
                hangHoa.setMaHang(cursor.getString(cursor.getColumnIndexOrThrow(TABLE.COLUMN_M)));
                hangHoa.setTenHang(cursor.getString(cursor.getColumnIndexOrThrow(TABLE.COLUMN_Ten)));
                hangHoa.setLoaiHangHoa(cursor.getString(cursor.getColumnIndexOrThrow(TABLE.COLUMN_Phanloai)));


                hangHoa.setHinhanh(cursor.getString(cursor.getColumnIndexOrThrow(TABLE.COLUMN_hinhanh)));

                hangHoa.setGia(cursor.getDouble(cursor.getColumnIndexOrThrow(TABLE.COLUMN_Gia)));
                hangHoa.setSize(cursor.getDouble(cursor.getColumnIndexOrThrow(TABLE.COLUMN_SIZE)));

                listHangHoa.add(hangHoa);
            }

            cursor.close();
            return listHangHoa;
    }





    public Cursor getData(String sql)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public boolean a;


    Context context;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldver ,int newver) {

    }

    public void deleteHanghoa(String Ma)
    {
        String selection = TABLE.COLUMN_M + " = ?";
        String[] selectionArgs ={Ma};
        int deleteRows =getWritableDatabase().delete(TABLE.TABLE_NAME, selection, selectionArgs);

    }

    public void updateHanghoa(HangHoa hh) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TABLE.COLUMN_Ten, hh.getTenHang());
        values.put(TABLE.COLUMN_Phanloai, hh.getTenloai());

        values.put(TABLE.COLUMN_hinhanh, hh.getHinhanh());
        values.put(TABLE.COLUMN_Gia, hh.getGia());
        values.put(TABLE.COLUMN_SIZE, hh.getSize());

        String selection = TABLE.COLUMN_M + " = ?";
        String[] selectionArgs = {hh.getMaHang()};

        int updatedRows = db.update(TABLE.TABLE_NAME, values, selection, selectionArgs);

    }

    public boolean isManvExists(String mahanghoa) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + TABLE.TABLE_NAME +
                " WHERE " + TABLE.COLUMN_M + " = ?";

        String[] selectionArgs = {mahanghoa};

        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            int count = cursor.getInt(0);
            cursor.close();
            return count > 0;
        } else {
            cursor.close();
            return false;
        }
    }

}
