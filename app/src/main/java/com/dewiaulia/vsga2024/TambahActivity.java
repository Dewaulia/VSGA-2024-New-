package com.dewiaulia.vsga2024;

import static android.app.ProgressDialog.show;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TambahActivity extends AppCompatActivity {

    private EditText namaEditText;
    private EditText catatanEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tambah);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        namaEditText = findViewById(R.id.namaEditText);
        catatanEditText = findViewById(R.id.catatanEditText);
    }

    public void simpan(View view) {
        // Sanity check
        String namaFile = namaEditText.getText().toString();
        if (namaFile.isEmpty()) {
            Toast.makeText(this, R.string.nama_file_tidak_boleh_kosong,
                    Toast.LENGTH_LONG).show();
            return;
        }

        String isiCatatan = catatanEditText.getText().toString();
        if (isiCatatan.isEmpty()) {
            Toast.makeText(this, R.string.isi_catatan_tidak_boleh_kosong,
                    Toast.LENGTH_LONG).show();
            return;
        }

        //TODO: simpan ke file
        buatFile(namaFile, isiCatatan);

        // kembali ke tampilan utama
        finish();

    }

    private void buatFile(String namaFile, String isiCatatan) {
        File file = new File(getFilesDir(), namaFile);
        FileOutputStream fos;
        try {
            file.createNewFile();
            fos = new FileOutputStream(file, false);
            fos.write(isiCatatan.getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
