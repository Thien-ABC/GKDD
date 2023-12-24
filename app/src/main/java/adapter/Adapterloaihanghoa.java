package adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gkdd.R;

import java.util.List;

import dao.DBHelperlhh;
import model.LoaiHangHoa;

public class Adapterloaihanghoa extends ArrayAdapter<LoaiHangHoa> {
    Activity context;
    int res;
    List<LoaiHangHoa> obj;

    DBHelperlhh helper;

    public Adapterloaihanghoa(Activity context, int res, List<LoaiHangHoa> list, DBHelperlhh helper) {
        super(context, res, list);
        this.context = context;
        this.res = res;
        this.obj = list;
        this.helper = helper;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.res, null);

        TextView tenloai = item.findViewById(R.id.tv_tenloai);

        final LoaiHangHoa hh = obj.get(position);
        tenloai.setText(hh.getTenloai());
        return item;
    }
}
