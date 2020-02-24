package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullnameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText confirmpasswordInput;
    private EditText homepageInput;
    private EditText aboutInput;

    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;

    private ImageView changeProfil;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullnameInput = findViewById(R.id.text_fullname);
        emailInput = findViewById(R.id.text_email);
        passwordInput = findViewById(R.id.text_password);
        confirmpasswordInput = findViewById(R.id.text_confirm_password);
        homepageInput = findViewById(R.id.text_homepage);
        aboutInput = findViewById(R.id.text_about);

        changeProfil = findViewById(R.id.image_profile);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED){
            return;
        }

        if (requestCode == GALLERY_REQUEST_CODE){
            if (data != null){
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
                    changeProfil.setImageBitmap(bitmap);
                }catch (IOException e) {
                    Toast.makeText(this,"Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
    public void handleRegister(View view) {
        String fullnameText = fullnameInput.getText().toString();
        String emailText = emailInput.getText().toString();
        String passwordText = passwordInput.getText().toString();
        String confirmText = confirmpasswordInput.getText().toString();
        String homepageText = homepageInput.getText().toString();
        String aboutText = aboutInput.getText().toString();

        if(!(fullnameText).equals("") && !(emailText).equals("") && !(passwordText).equals("") && !(confirmText).equals("") && !(homepageText).equals("") && !(aboutText).equals("")){
            if ((passwordText).equals(confirmText)){
                Intent iten = new Intent(this,ProfileActivity.class);
                iten.putExtra("FULLNAME_KEY",fullnameText);
                iten.putExtra("EMAIL_KEY",emailText);
                iten.putExtra("PASSWORD_KEY",passwordText);
                iten.putExtra("CONFIRM_KEY",confirmText);
                iten.putExtra("HOMEPAGE_KEY",homepageText);
                iten.putExtra("ABOUT_KEY",aboutText);
                startActivity(iten);
            }else{
                Toast.makeText(this,"Password and Confirm password must same !",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"Please insert all data !",Toast.LENGTH_SHORT).show();
        }
    }

    public void changeProfil (View view){
        Intent iten = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(iten,GALLERY_REQUEST_CODE);
    }
}
