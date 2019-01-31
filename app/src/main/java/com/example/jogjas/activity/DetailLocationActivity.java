package com.example.jogjas.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jogjas.R;
import com.example.jogjas.pojo.DataLocation;

public class DetailLocationActivity extends AppCompatActivity {
    private ImageView imgDetail;
    private TextView tvNama,tvLoc,tvDesc;
    private DataLocation datas;
    private ImageButton imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);
        imgDetail = (ImageView) findViewById(R.id.imgDetail);
        tvNama = (TextView) findViewById(R.id.tvLocNameDet);
        tvLoc = (TextView) findViewById(R.id.tvLocStreetDet);
        tvDesc = (TextView) findViewById(R.id.tvDetDesc);
        imageButton = (ImageButton) findViewById(R.id.btnBack);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            datas = bundle.getParcelable("data");
            tvNama.setText(datas.getNama_pariwisata());
            tvLoc.setText(datas.getAlamat_pariwisata());
            tvDesc.setText(datas.getDetail_pariwisata());

            Glide.with(this).load(datas.getGambar_pariwisata()).into(imgDetail);
        }
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
