package com.bnk.example.bnkdata;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.graphics.Matrix;
import android.widget.Spinner;
import android.widget.TextView;

import com.bnk.example.bnkdata.DB.DBManager;
import com.bnk.example.bnkdata.Model.CrByAgeModel;
import com.bnk.example.bnkdata.Model.CrByRgnModel;

import java.util.List;
import java.util.stream.Collectors;

//차트-신용 탭
public class Tab2Fragment extends Fragment implements ImageView.OnTouchListener {

    ImageView[] map = new ImageView[17];
    int[] originColor = new int[17];
    String[] map_itemstr = new String[17], map_item_valstr = new String[17];
    int[] rscid = {R.drawable.m0, R.drawable.m1, R.drawable.m2, R.drawable.m3, R.drawable.m4, R.drawable.m5, R.drawable.m6, R.drawable.m7, R.drawable.m8, R.drawable.m9,
            R.drawable.m10, R.drawable.m11, R.drawable.m12, R.drawable.m13, R.drawable.m14, R.drawable.m15, R.drawable.m16};
    FrameLayout frame;
    Spinner spinner, category, age;

    String[] ctgstr = {"평균 신용등급", "우량 신용자수", "물건 담보물"}; // CRBYAGE,CRBYRGN,CLTRGN
    String[] months, ages; // 2019-12 , 2019-09, 2019-06

    TextView map_item, map_item_val;

    int previousSelectedMapIdx = -1; // 이전에 선택했던 지역의 선택효과 초기화를 위해 저장

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void valInit(View view) {
        map_item = view.findViewById(R.id.map_item);
        map_item_val = view.findViewById(R.id.map_item_val);
        months = getResources().getStringArray(R.array.months);
        ages = getResources().getStringArray(R.array.ages);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3, null);
        Log.d("load", "==========loadImgStart===========");
        valInit(view);
        createSpinner(view);
        createMap(view);
        age.setSelection(1);
        setValue();
        showSelectedMapInfo("군/구 별 " + age.getSelectedItem().toString() + "대 " + category.getSelectedItem().toString(),"데이터를 보고싶은 지역을 터치해주세요!");
        return view;
    }
    private void setValue() {
        //CRBYAGE , 평균 신용등급
        if (spinner.getSelectedItemPosition() == 0) {
            CharSequence seldt = spinner.getSelectedItem().toString();
            List<CrByAgeModel> list = DBManager.crByAges.stream().filter(t -> t.getDt().contains(seldt)).collect(Collectors.toList()); //선택된 달만 추출
            float maxcr = 4.8f;
            float mincr = 2.9f;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getAge() == Integer.parseInt(age.getSelectedItem().toString())) {
                    int conid = list.get(i).getCondst();
                    int rate = Math.round((list.get(i).getAvgcr()-mincr) / (maxcr-mincr) * 255f);
                    originColor[conid] = Color.argb(rate, 180, 44, 44);
                    map[conid].setColorFilter(originColor[conid]);
                    map_itemstr[conid] = DBManager.condsts.get(conid).getNm() + " " + age.getSelectedItem().toString() + "대 " + category.getSelectedItem().toString();
                    map_item_valstr[conid] = Float.toString(list.get(i).getAvgcr());
                }
            }
        }
        //CRBYRGN 우량 신용자수
        else if (spinner.getSelectedItemPosition() == 1) {
            CharSequence seldt = spinner.getSelectedItem().toString();
            List<CrByRgnModel> list = DBManager.crByRgns.stream().filter(t -> t.getDt().contains(seldt)).collect(Collectors.toList());

        }
        //CLTRGN 물건 담보물 (부채량)
        else {

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
        int color = bmp.getPixel((int) event.getX(), (int) event.getY());
        if (color == Color.TRANSPARENT)
            return false; // 공백터치시 무시
        else {
            int idx = 1;
            for (int i = 1; i < 17; i++) { //0번은 배경이므로 1번부터
                if (v.equals(map[i])) {
                    idx = i;
                    break;
                }
            }
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // 누를때
                    if(previousSelectedMapIdx!=-1){
                        map[previousSelectedMapIdx].setColorFilter(originColor[previousSelectedMapIdx]); // 이전에 선택했던 지역 색 초기화
                    }else if(previousSelectedMapIdx == idx){
                        //한번 더 눌렀을 때 초기화
                        map[idx].setColorFilter(originColor[idx]);
                    }else{
                        map[idx].setColorFilter(originColor[idx], PorterDuff.Mode.SRC_IN);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    // 뗄때
                    map[idx].setColorFilter(originColor[idx], PorterDuff.Mode.DST_OUT);
                    showSelectedMapInfo(map_itemstr[idx], map_item_valstr[idx]);
                    previousSelectedMapIdx = idx;
                    break;
            }
            return true;
        }
    }

    public void showSelectedMapInfo(String itemname, String value) {
        map_item.setText(itemname);
        map_item_val.setText(value);
    }

    private void createMap(View view) {
        frame = view.findViewById(R.id.frame);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = true;
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        options.inSampleSize = calculateInSampleSize(options, imageWidth, imageHeight);
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = false;
        for (int i = 0; i < 17; i++) {
            Bitmap bm = BitmapFactory.decodeResource(getResources(), rscid[i], options);
            ImageView img = new ImageView(getContext());
            img.setImageBitmap(bm);
            img.setDrawingCacheEnabled(true); // 공백 터치하는지 검사하기 위해 필요
            img.setLayoutParams(layoutParams);
            frame.addView(img);
            map[i] = img;
            if (i > 0) { //0번은 지도틀 이미지이므로 클릭이벤트 발생하면안됨
                map[i].setOnTouchListener(this);
            }
        }
    }

    private void createSpinner(View view) {
        age = view.findViewById(R.id.spinner_age);

        final ArrayAdapter<String> adapterage = new ArrayAdapter<>(getContext(), R.layout.spinner_text_small, ages);
        adapterage.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        age.setAdapter(adapterage);
        age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setValue();
                showSelectedMapInfo(age.getSelectedItem().toString() + "대 " + category.getSelectedItem().toString(),spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        category = view.findViewById(R.id.spinner_category);

        final ArrayAdapter<String> adapterctg = new ArrayAdapter<>(getContext(), R.layout.spinner_text, ctgstr);
        adapterctg.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        category.setAdapter(adapterctg);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (category.getSelectedItemPosition() >= 0) {
                    setValue();
                    showSelectedMapInfo("군/구 별 "+ category.getSelectedItem().toString(),"데이터를 보고싶은 지역을 터치하세요!");
                    if(category.getSelectedItemPosition()>0){
                        age.setVisibility(View.GONE);
                    }else{
                        age.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinner = view.findViewById(R.id.spinner);
        // months.xml 배열로 가져오기
        //spinner_text.xml과 str을 인자로 어댑터 생성.
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_text_small, months);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setValue();
                showSelectedMapInfo("군/구 별 " + category.getSelectedItem().toString(),spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
