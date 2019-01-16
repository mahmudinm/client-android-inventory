package com.example.mahmudinm.androidcodeigniterinventory.view.barang.editor;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.mahmudinm.androidcodeigniterinventory.R;
import com.example.mahmudinm.androidcodeigniterinventory.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BarangActivity extends AppCompatActivity {

    SessionManager session;
    ProgressDialog progressDialog;
    Uri uri;

    String currentPhotoPath;
    String selectImagePath;
    static final String folder = "AndroidInventory";
    static final int type_foto_code = 1;
    static final int REQUEST_GALLERY = 1;
    static final int REQUEST_CAMERA = 2;

    @BindView(R.id.kode)
    EditText kode;
    @BindView(R.id.nama)
    EditText nama;
    @BindView(R.id.stock)
    EditText stock;
    @BindView(R.id.harga)
    EditText harga;
    @BindView(R.id.ukuran)
    EditText ukuran;
    @BindView(R.id.gambar)
    ImageView gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.select) void selectImage() {
        permission();

        new MaterialDialog.Builder(this)
                .title("Select Image")
                .items(R.array.uploadImages)
                .itemsIds(R.array.itemIds)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        switch (position) {
                            case 0:
                                Intent intent = new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(intent.createChooser(intent, "Select Image"), REQUEST_GALLERY);
                                break;
                        }
                    }
                })
                .show();

    }

    private void permission() {
        if (
                Build.VERSION.SDK_INT >= 23
                        && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED
                        && checkSelfPermission(Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED
                        && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED
                        && checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED
                ) {
            requestPermissions(new String[]{
                    Manifest.permission.RECORD_AUDIO
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE
                    , Manifest.permission.READ_EXTERNAL_STORAGE
                    , Manifest.permission.CAMERA
            }, 0);
        } else {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY && resultCode != 0) {
            uri = data.getData();
            gambar.setImageURI(uri);
        }
    }


}
