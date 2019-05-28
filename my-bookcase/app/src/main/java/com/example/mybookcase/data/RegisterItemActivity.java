package com.example.mybookcase.data;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mybookcase.R;
import com.example.mybookcase.data.model.Item;
import com.example.mybookcase.data.persistence.ItemDAO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class RegisterItemActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int PICK_IMAGE_REQUEST = 234;
    public static final int MY_PERMISSIONS_REQUEST = 0;
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    ImageView fieldImage;
    Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_item);
        getSupportActionBar().hide();
        final EditText fieldName = findViewById(R.id.nomeItem);
        final EditText fieldDesc = findViewById(R.id.descItem);
        fieldImage = findViewById(R.id.imgItem);
        final Button buttonRegister = findViewById(R.id.insertItem);
        final ImageButton captureImage = findViewById(R.id.captureImage);
        final ImageButton chooseImage = findViewById(R.id.chooseImage);
        final Bundle details = getIntent().getBundleExtra("details");

        captureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
                    if (ContextCompat.checkSelfPermission(RegisterItemActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(RegisterItemActivity.this, Manifest.permission.CAMERA)) {

                        } else {
                            ActivityCompat.requestPermissions(RegisterItemActivity.this, new String[]{Manifest.permission.CAMERA},REQUEST_IMAGE_CAPTURE);
                        }
                    }
                    else{
                        dispatchTakePictureIntent(v);
                    }
                }else {
                    dispatchTakePictureIntent(v);
                }
            }
        });

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
                    if (ContextCompat.checkSelfPermission(RegisterItemActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(RegisterItemActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                        } else {
                            ActivityCompat.requestPermissions(RegisterItemActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PICK_IMAGE_REQUEST);
                        }
                    }
                    else{
                        showFileChooser();
                    }
                }else {
                    showFileChooser();
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Item item = new Item();
                item.setName(fieldName.getText().toString());
                item.setDescription(fieldDesc.getText().toString());
                item.setType(details.getString("type"));
                item.setIsAcervo(details.getString("isAcervo"));
                item.setImageByte(imgViewToByte(fieldImage));

                try{
                    ItemDAO itemDAO = new ItemDAO(getApplicationContext());
                    long retorno = itemDAO.insertItem(item);
                    if(retorno != -1){
                        Toast.makeText(getApplicationContext(), "Item inserido com sucesso", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public byte[] imgViewToByte(ImageView img){
        Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            fieldImage.setImageDrawable(new BitmapDrawable(getResources(), imageBitmap));
        }

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK){
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                fieldImage.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*if ((requestCode == PICK_IMAGE_REQUEST || requestCode == REQUEST_IMAGE_CAPTURE) && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            if (requestCode == PICK_IMAGE_REQUEST) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    fieldImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_IMAGE_CAPTURE) {
                filePath = data.getData();
                Uri selectedImageUri = data.getData();
                fieldImage.setImageURI(selectedImageUri);

            }
        }*/
    }

    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PICK_IMAGE_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(RegisterItemActivity.this,"Permissao garantida", Toast.LENGTH_SHORT).show();
                } else {

                }
                return;
            }
        }
    }

}
