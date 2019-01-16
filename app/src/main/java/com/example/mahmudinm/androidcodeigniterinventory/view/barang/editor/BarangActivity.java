package com.example.mahmudinm.androidcodeigniterinventory.view.barang.editor;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.mahmudinm.androidcodeigniterinventory.BuildConfig;
import com.example.mahmudinm.androidcodeigniterinventory.R;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.BarangResponse;
import com.example.mahmudinm.androidcodeigniterinventory.utils.FileUtils;
import com.example.mahmudinm.androidcodeigniterinventory.utils.SessionManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class BarangActivity extends AppCompatActivity implements BarangView {

    SessionManager session;
    ProgressDialog progressDialog;
    BarangPresenter presenter;
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

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");

        session = new SessionManager(this);
        presenter = new BarangPresenter(this);

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
                                Intent intentGallery = new Intent();
                                intentGallery.setType("image/*");
                                intentGallery.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(intentGallery.createChooser(intentGallery, "Select Image"), REQUEST_GALLERY);
                                break;
                            case 1:
                                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                uri = ambilOutputMediaFileUri(type_foto_code);
                                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                startActivityForResult(intentCamera, REQUEST_CAMERA);
                        }
                    }
                })
                .show();
    }

    @OnClick(R.id.simpan) void simpan() {
        File file = FileUtils.getFile(BarangActivity.this, uri);
        RequestBody gambarBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part gambarPart = MultipartBody.Part.createFormData("gambar", file.getName
                (), gambarBody);
        RequestBody kodeBody = RequestBody.create(MediaType.parse("text/plain"), kode.getText()
                .toString());
        RequestBody namaBody = RequestBody.create(MediaType.parse("text/plain"), nama.getText()
                .toString());
        RequestBody stockBody = RequestBody.create(MediaType.parse("text/plain"), stock.getText()
                .toString());
        RequestBody hargaBody = RequestBody.create(MediaType.parse("text/plain"), harga.getText()
                .toString());
        RequestBody ukuranBody = RequestBody.create(MediaType.parse("text/plain"), ukuran.getText()
                .toString());

        presenter.saveBarang(
                session.getKeyToken(),
                gambarPart,
                kodeBody,
                namaBody,
                stockBody,
                hargaBody,
                ukuranBody
        );

    }

    @Override
    public void statusSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void statusError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY && resultCode != 0) {
            uri = data.getData();
            gambar.setImageURI(uri);
        } else if (requestCode == REQUEST_CAMERA && resultCode != 0) {
            uri = Uri.parse(currentPhotoPath);
            selectImagePath = uri.getPath();
            gambar.setImageURI(uri);
        }
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

    private Uri ambilOutputMediaFileUri(int type_foto_code) {
        // mengambil alamat directory file
        // return Uri.fromFile(ambilOutputMediaFile(type_foto_code));
        return FileProvider.getUriForFile(BarangActivity.this,
                BuildConfig.APPLICATION_ID + ".provider",
                ambilOutputMediaFile());
    }

    private File ambilOutputMediaFile() {
        // atur alamat penyimpanan (SDCard/Pictures/folder_foto)
        File penyimpananMediaDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                , folder
        );
        Log.d("Directory Fileku", penyimpananMediaDir.getAbsolutePath());


        // cek keberadaan folder
        if (!penyimpananMediaDir.exists()) {
            // cek jika tidak bisa membuat folder baru
            if (!penyimpananMediaDir.mkdir()) {
                Toast.makeText(this, "Gagal membuat folder "
                        + folder, Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        // simpan format tanggal saat pengambilan foto
        String waktu = new SimpleDateFormat("yyyyMMdd_hhMss"
                , Locale.getDefault()).format(new Date());
        Log.d("Waktu Pengambilan", waktu);

        // variabel penampung nama file
        File mediaFile;
        // pasang nama foto dengan waktu
        if (type_foto_code == type_foto_code) {
            mediaFile = new File(penyimpananMediaDir.getPath() + File.separator
                    + "IMG" + waktu + ".jpg");
            Log.d("Nama FIle", mediaFile.getAbsolutePath());
        } else {
            return null;
        }
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = "file:" + mediaFile.getAbsolutePath();
        Log.d("currentPhotoPath : ", currentPhotoPath);
        return mediaFile;
    }

}
