package com.bnk.example.bnkdata;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bnk.example.bnkdata.DB.DBManager;
import com.bnk.example.bnkdata.Model.CltRgnModel;
import com.bnk.example.bnkdata.Model.CrByAgeModel;
import com.bnk.example.bnkdata.Model.CrByRgnModel;
import com.bnk.example.bnkdata.Model.CrdStrModel;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//차트-신용 탭
public class Tab2Fragment extends Fragment implements ImageView.OnTouchListener {

    ImageView[] map = new ImageView[17];
    ImageView pin;
    int[] originColor = new int[17];
    String[] map_itemstr = new String[17], map_item_valstr = new String[17];
    int[] rscid = {R.drawable.m0, R.drawable.m1, R.drawable.m2, R.drawable.m3, R.drawable.m4, R.drawable.m5, R.drawable.m6, R.drawable.m7, R.drawable.m8, R.drawable.m9,
            R.drawable.m10, R.drawable.m11, R.drawable.m12, R.drawable.m13, R.drawable.m14, R.drawable.m15, R.drawable.m16};
    FrameLayout frame;
    Spinner selMonth, category, age, selRate;

    String[] ctgstr; // CRBYAGE,CRBYRGN,CLTRGN
    String[] months, ages, rates;

    TextView rateLabel, ageLabel, map_item, map_item_val, desc;

    int previousSelectedMapIdx = -1; // 이전에 선택했던 지역의 선택효과 초기화를 위해 저장
    int previousTouchMapIdx = -1; //이전에 선택한 지역(터치 누름->터치 뗀 맵이 다를 때 초기화 하기 위해 저장)

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void valInit(View view) {
        map_item = view.findViewById(R.id.map_item);
        map_item_val = view.findViewById(R.id.map_item_val);
        rateLabel = view.findViewById(R.id.rateLabel);
        ageLabel = view.findViewById(R.id.ageLabel);
        desc = view.findViewById(R.id.description);
        pin = view.findViewById(R.id.pin);

        age = view.findViewById(R.id.spinner_age);
        selRate = view.findViewById(R.id.spinner_selRate);
        category = view.findViewById(R.id.spinner_category);
        selMonth = view.findViewById(R.id.spinner);

        ctgstr = getResources().getStringArray(R.array.ctg);
        months = getResources().getStringArray(R.array.months);
        ages = getResources().getStringArray(R.array.ages);
        rates = getResources().getStringArray(R.array.cr);
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
        showSelectedMapInfo("군/구 별 " + age.getSelectedItem().toString() + "대 " + category.getSelectedItem().toString(), "데이터를 보고싶은 지역을 터치해주세요!");
        return view;
    }

    private void setValue() {
        //CRBYAGE , 평균 신용등급
        pin.setVisibility(View.GONE);
        if (category.getSelectedItemPosition() == 0) {
            CharSequence seldt = selMonth.getSelectedItem().toString();
            List<CrByAgeModel> list = DBManager.crByAges.stream().filter(t -> t.getDt().contains(seldt)).collect(Collectors.toList()); //선택된 달만 추출
            float maxcr = 4.8f;
            float mincr = 2.9f;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getAge() == Integer.parseInt(age.getSelectedItem().toString())) {
                    int conid = list.get(i).getCondst();
                    int rate = Math.round((list.get(i).getAvgcr() - mincr) / (maxcr - mincr) * 255f);
                    originColor[conid] = Color.argb(rate, 180, 44, 44);
                    map[conid].setColorFilter(originColor[conid]);
                    map_item_valstr[conid] = Float.toString(list.get(i).getAvgcr());
                    map_itemstr[conid] = DBManager.condsts.get(conid).getNm() + " " + age.getSelectedItem().toString() + "대 " + category.getSelectedItem().toString() + " : " + map_item_valstr[conid];
                }
            }
            desc.setText(" ※ 신용등급 범위: 1~10등급 (1에 가까울수록 우량함)\n" +
                    " ※ 신용정보법에 따른 부산 만18세이상 경제활동 경험 인구 기준으로 민간 신용평가\n");
        }
        //CRBYRGN 등급별 신용자수
        else if (category.getSelectedItemPosition() == 1) {
            CharSequence seldt = selMonth.getSelectedItem().toString();
            List<CrByRgnModel> list = DBManager.crByRgns.stream().filter(t -> t.getDt().contains(seldt)).collect(Collectors.toList());
            int maxn = 0, minn = Integer.MAX_VALUE;
            for (int i = 0; i < list.size(); i++) {
                switch (selRate.getSelectedItemPosition()) {
                    case 0:
                        maxn = Integer.max(maxn, DBManager.crByRgns.get(i).getRatesg());
                        minn = Integer.min(minn, DBManager.crByRgns.get(i).getRatesg());
                        break;
                    case 1:
                        maxn = Integer.max(maxn, DBManager.crByRgns.get(i).getRateg());
                        minn = Integer.min(minn, DBManager.crByRgns.get(i).getRateg());
                        break;
                    case 2:
                        maxn = Integer.max(maxn, DBManager.crByRgns.get(i).getRatebd());
                        minn = Integer.min(minn, DBManager.crByRgns.get(i).getRatebd());
                        break;
                    case 3:
                        maxn = Integer.max(maxn, DBManager.crByRgns.get(i).getRategbd());
                        minn = Integer.min(minn, DBManager.crByRgns.get(i).getRategbd());
                        break;
                }
            }
            minn /= 2;
            for (int i = 0; i < list.size(); i++) {
                int conid = list.get(i).getCondst();
                int rate = 0;
                if (maxn - minn == 0) {
                    rate = 0;
                    map_item_valstr[conid] = Integer.toString(0);
                    originColor[conid] = Color.argb(0, 0, 0, 0);
                } else {
                    switch (selRate.getSelectedItemPosition()) {
                        case 0:
                            rate = Math.round((float) (list.get(i).getRatesg() - minn) / (maxn - minn) * 255f);
                            map_item_valstr[conid] = Integer.toString(list.get(i).getRatesg());
                            originColor[conid] = Color.argb(rate, 44, 200, 44);
                            break;
                        case 1:
                            rate = Math.round((float) (list.get(i).getRateg() - minn) / (maxn - minn) * 255f);
                            map_item_valstr[conid] = Integer.toString(list.get(i).getRateg());
                            originColor[conid] = Color.argb(rate, 44, 95, 95);
                            break;
                        case 2:
                            rate = Math.round((float) (list.get(i).getRatebd() - minn) / (maxn - minn) * 255f);
                            map_item_valstr[conid] = Integer.toString(list.get(i).getRatebd());
                            originColor[conid] = Color.argb(rate, 180, 44, 44);
                            break;
                        case 3:
                            rate = Math.round((float) (list.get(i).getRategbd() - minn) / (maxn - minn) * 255f);
                            map_item_valstr[conid] = Integer.toString(list.get(i).getRategbd());
                            originColor[conid] = Color.argb(rate, 180, 44, 44);
                            break;
                    }
                }
                map_itemstr[conid] = DBManager.condsts.get(conid).getNm() + " " + selRate.getSelectedItem().toString() + " " + category.getSelectedItem().toString() + " : " + map_item_valstr[conid] + "명";
                map_item_valstr[conid] = "";
                map[conid].setColorFilter(originColor[conid]);
            }
            desc.setText("\t\n" +
                    "○ 초우량관리지수 : 고객군별 최상위 Band(1~2) 지수에 해당하는 카드 이용자수" +
                    "\n" +
                    "○ 우량관리지수 : 고객군별 상위 Band(1~3) 지수에 해당하는 카드 이용자수" +
                    "\n" +
                    "○ 불량관리지수 : 고객군별 하위 Band(7~10) 지수에 해당하는 카드 이용자수" +
                    "\n" +
                    "○ 초불량관리지수 : 고객군별 하위 Band(9~10) 지수에 해당하는 카드 이용자수");
        }
        //CLTRGN 물건 담보물 (부채량)
        else {
            if (category.getSelectedItemPosition() == 2) {
                CharSequence seldt = selMonth.getSelectedItem().toString();
                List<CltRgnModel> list = DBManager.cltRgns.stream().filter(t -> t.getDt().contains(seldt)).collect(Collectors.toList()); //선택된 달만 추출
                long max = 0, min = Long.MAX_VALUE;
                for(int i=0;i<list.size();i++){
                    max = Long.max(DBManager.cltRgns.get(i).getCollateral(),max);
                    min = Long.min(DBManager.cltRgns.get(i).getCollateral(),min);
                }
                DecimalFormat df = new DecimalFormat("###,###");
                for (int i = 0; i < list.size(); i++) {
                    int conid = list.get(i).getCondst();
                    long c1 = list.get(i).getCollateral() - min;
                    long c2 = (max - min);
                    long rate = c1*255l / c2*255l / 255l;
                    rate = (rate>=255) ? 255 : rate;
                    originColor[conid] = Color.argb(new Long(rate).intValue(), 120, 22, 22);
                    map[conid].setColorFilter(originColor[conid]);
                    map_itemstr[conid] = DBManager.condsts.get(conid).getNm() + " " + category.getSelectedItem().toString() + " : " + df.format(list.get(i).getCollateral()*1000)+"원";
                    double tmp = Math.floor(list.get(i).getCollateral()/100000l);
                    map_item_valstr[conid] = "(" + df.format(tmp) + "억원)";
                }
                desc.setText("총 물건담보대출 잔액\n" +
                        " - 물건담보 기준(주거용 및 비주거용 부동산, 자동차 등)\n");
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
        int color = bmp.getPixel((int) event.getX(), (int) event.getY());
        if (color == Color.TRANSPARENT) {
            return false; // 공백터치시 무시
        } else {
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
                    pin.setVisibility(View.GONE);
                    if (previousSelectedMapIdx != -1) {
                        map[previousSelectedMapIdx].setColorFilter(originColor[previousSelectedMapIdx]); // 이전에 선택했던 지역 색 초기화
                    }
                    map[idx].setColorFilter(Color.WHITE);
                    previousTouchMapIdx = idx;
                    break;
                case MotionEvent.ACTION_UP:
                    // 뗄때
                    pin.setVisibility(View.VISIBLE);
                    if (previousTouchMapIdx != -1)
                        map[previousTouchMapIdx].setColorFilter(originColor[previousTouchMapIdx]);
                    showSelectedMapInfo(map_itemstr[idx], map_item_valstr[idx]);
                    previousSelectedMapIdx = idx;
                    pin.setX(event.getX() - pin.getWidth() / 2);
                    pin.setY(event.getY() + pin.getHeight() * 2);
                    break;
            }
            return true;
        }
    }

    @ColorInt
    private int adjustAlpha(@ColorInt int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    private void showSelectedMapInfo(String itemname) {
        showSelectedMapInfo(itemname, "");
    }

    private void showSelectedMapInfo(String itemname, String value) {
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
        final ArrayAdapter<String> adapterctg = new ArrayAdapter<>(getContext(), R.layout.spinner_text, ctgstr);
        adapterctg.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        category.setAdapter(adapterctg);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (category.getSelectedItemPosition() >= 0) {
                    setValue();
                    showSelectedMapInfo("군/구 별 " + category.getSelectedItem().toString(), "\n데이터를 보고싶은 지역을 터치하세요!");
                    if (category.getSelectedItemPosition() == 2) {
                        age.setVisibility(View.GONE);
                        selRate.setVisibility(View.GONE);
                        ageLabel.setVisibility(View.GONE);
                        rateLabel.setVisibility(View.GONE);
                    } else if (category.getSelectedItemPosition() == 1) {
                        age.setVisibility(View.GONE);
                        selRate.setVisibility(View.VISIBLE);
                        ageLabel.setVisibility(View.GONE);
                        rateLabel.setVisibility(View.VISIBLE);
                    } else {
                        age.setVisibility(View.VISIBLE);
                        selRate.setVisibility(View.GONE);
                        ageLabel.setVisibility(View.VISIBLE);
                        rateLabel.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // months.xml 배열로 가져오기
        //spinner_text.xml과 str을 인자로 어댑터 생성.
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_text_small, months);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        selMonth.setAdapter(adapter);
        selMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setValue();
                showSelectedMapInfo("군/구 별 " + category.getSelectedItem().toString(), selMonth.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        final ArrayAdapter<String> adapterage = new ArrayAdapter<>(getContext(), R.layout.spinner_text_small, ages);
        adapterage.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        age.setAdapter(adapterage);
        age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setValue();
                showSelectedMapInfo(age.getSelectedItem().toString() + "대 " + category.getSelectedItem().toString(), selMonth.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        final ArrayAdapter<String> adapterate = new ArrayAdapter<>(getContext(), R.layout.spinner_text_small, rates);
        adapterate.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        selRate.setAdapter(adapterate);
        selRate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setValue();
                //showSelectedMapInfo(selRate.getSelectedItem().toString() + " " + category.getSelectedItem().toString(), selMonth.getSelectedItem().toString());
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
