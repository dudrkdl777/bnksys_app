package com.bnk.example.bnkdata;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.graphics.Matrix;

//차트-신용 탭
public class Tab2Fragment extends Fragment {

    ImageView[] map = new ImageView[17];
    int[] rscid = {R.drawable.m0,R.drawable.m1,R.drawable.m2,R.drawable.m3,R.drawable.m4,R.drawable.m5,R.drawable.m6,R.drawable.m7,R.drawable.m8,R.drawable.m9,
    R.drawable.m10,R.drawable.m11,R.drawable.m12,R.drawable.m13,R.drawable.m14,R.drawable.m15,R.drawable.m16};
    FrameLayout frame;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3, null);
        Log.d("load","==========loadImgStart===========");
        frame = view.findViewById(R.id.frame);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither=true;
        options.inSampleSize = 1;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        for(int i=0;i<17;i++){
            Bitmap bm = BitmapFactory.decodeResource(getResources(), rscid[i],options);
            Matrix m = new Matrix();
            m.postTranslate(0, 0);

            ImageView img = new ImageView(getContext());
            img.setScaleType(ImageView.ScaleType.MATRIX);
            img.setImageBitmap(bm);
            img.setImageMatrix(m);
            img.setLayoutParams(layoutParams);
            frame.addView(img);
            map[i] = img;
        }


        /*
        map[1] = view.findViewById(R.id.m1);
        for(int i=0;i<1;i++){
            map[i] = view.findViewWithTag("m"+i);
        }
        map[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map[1].setColorFilter(Color.argb(255,255,0,0));
            }
        });*/
        return view;
    }
}
