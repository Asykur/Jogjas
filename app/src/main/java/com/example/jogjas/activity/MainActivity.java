package com.example.jogjas.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.jogjas.R;
import com.example.jogjas.adapter.DataAdapter;
import com.example.jogjas.pojo.DataLocation;
import com.example.jogjas.retrofit.ApiResponse;
import com.example.jogjas.retrofit.ServicesFactory;
import com.example.jogjas.utils.AppPreference;
import com.google.firebase.auth.FirebaseAuth;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DataLocation> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImageButton btnMenu;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView =(RecyclerView) findViewById(R.id.rvLocation);
        btnMenu = (ImageButton) findViewById(R.id.btnMenu);
        firebaseAuth = FirebaseAuth.getInstance();
        initRecycler();
        getData();

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, btnMenu);
                popup.getMenuInflater().inflate(R.menu.main_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.about_menu){
                            startActivity(new Intent(MainActivity.this,AboutActivity.class));
                        }else {
                            AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                            builder.setCancelable(false);
                            builder.setMessage(R.string.wording_signout);
                            builder.setPositiveButton(R.string.ya, null);
                            builder.setNegativeButton(R.string.tidak,null);
                            final AlertDialog alertDialog = builder.create();
                            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                                @Override
                                public void onShow(final DialogInterface dialog) {
                                    Button buttonPositive = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                                    Button buttonNegative = ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_NEGATIVE);

                                    buttonPositive.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            firebaseAuth.signOut();
                                            AppPreference.setPreferenceBoolean(MainActivity.this,AppPreference.LOGIN,false);
                                            finishAffinity();
                                        }
                                    });

                                    buttonNegative.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            alertDialog.dismiss();
                                        }
                                    });
                                }
                            });
                            alertDialog.show();
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
    }

    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        DataAdapter ar = new DataAdapter(dataList,MainActivity.this);
        recyclerView.setAdapter(ar);
    }

    private void getData() {
        Call<ApiResponse<List<DataLocation>>> call = ServicesFactory.getService().getData();
        call.enqueue(new Callback<ApiResponse<List<DataLocation>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<DataLocation>>> call, Response<ApiResponse<List<DataLocation>>> response) {
                if (response.isSuccessful()&& response.body().isSuccessfull()){
                    List<DataLocation> datas = response.body().data;
                    if (datas.size()!=0){
                        dataList.clear();
                        dataList.addAll(datas);
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<DataLocation>>> call, Throwable t) {

            }
        });
    }
    
}
