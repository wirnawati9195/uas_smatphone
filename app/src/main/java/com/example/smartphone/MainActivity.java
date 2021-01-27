package com.example.smartphone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartphone.model.Phone;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnupdate;
    ImageButton btnedit;
    ListView lvdaftar;
    TextView txnodata, txusername;
    DaftarPhoneAdapter adapter;
    List<Phone> DaftarSmartphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialisasiView();
        loadDataTransaksi();
        setupListview();
    }

    private void inisialisasiView() {
        btnupdate = findViewById(R.id.btntambah);
        btnupdate.setOnClickListener(view -> bukaFormTambahPhone());
        btnedit = findViewById(R.id.btn_change_username);
        btnedit.setOnClickListener(view -> changeUserName());
        lvdaftar = findViewById(R.id.lv_list);
        txnodata = findViewById(R.id.tx_nodata);
        txusername = findViewById(R.id.tx_user_name);
        txusername.setText(SharePereferenceUtility.getUserName(this));
        txusername = findViewById(R.id.tx_user_name);
    }

    private void setupListview() {
        adapter = new DaftarPhoneAdapter(this, DaftarSmartphone);
        lvdaftar.setAdapter(adapter);

    }

    private void loadDataTransaksi() {
        DaftarSmartphone = SharePereferenceUtility.getAllLis(this);
        if (DaftarSmartphone.size() > 0) {
            txnodata.setVisibility(View.GONE);
        } else {
            txnodata.setVisibility(View.VISIBLE);
        }

    }

    private void refreshListView() {
        adapter.clear();
        loadDataTransaksi();
        adapter.addAll(DaftarSmartphone);
    }

    private void bukaFormTambahPhone() {
        Intent intent = new Intent(this, FormPhoneActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }

    private void changeUserName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ganti nama");

        final EditText input = new EditText(this);
        builder.setView(input);

    }
}