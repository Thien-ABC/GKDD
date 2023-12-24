    package adapter;

    import android.app.Activity;
    import android.content.Intent;
    import android.net.Uri;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.ImageView;
    import android.widget.TextView;

    import com.example.gkdd.R;
    import com.squareup.picasso.Picasso;

    import java.util.List;

    import dao.DBHelper;
    import model.HangHoa;


    public class AdapterHanghoa extends ArrayAdapter<HangHoa> {
        Activity context;
        int res;
        List<HangHoa> obj;

        DBHelper helper;

        public AdapterHanghoa(Activity context, int res, List<HangHoa> list, DBHelper helper) {
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
            TextView mahang = item.findViewById(R.id.tvmahang);

            TextView tenhang = item.findViewById(R.id.tvTenhang);
            TextView phanloai = item.findViewById(R.id.tvPhanloai);
/*            TextView gia = item.findViewById(R.id.tvgia);
            TextView size = item.findViewById(R.id.tvSize);*/
            ImageView imageView = item.findViewById(R.id.imagehanghoa);
            final HangHoa hh = obj.get(position);
            mahang.setText(hh.getMaHang());

            tenhang.setText(hh.getTenHang());
            phanloai.setText(hh.getTenloai());

//            gia.setText(hh.getGia() + "");
//            size.setText(hh.getSize() + "");
            if (!hh.getHinhanh().equals("")) {
                Uri imageUri = Uri.parse(hh.getHinhanh());
                if(imageUri!=null) {
                    context.getContentResolver().takePersistableUriPermission(imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }

                Picasso.get().load(imageUri).into(imageView);

            }

            return item;
        }


    }


