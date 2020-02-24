package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView fullnameText;
    private TextView emailText;
    private TextView homepageText;
    private TextView aboutText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullnameText = findViewById(R.id.label_fullname);
        emailText = findViewById(R.id.label_email);
        homepageText = findViewById(R.id.label_homepage);
        aboutText = findViewById(R.id.label_about);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            fullnameText.setText(getIntent().getStringExtra("FULLNAME_KEY"));
            emailText.setText(getIntent().getStringExtra("EMAIL_KEY"));
            homepageText.setText(getIntent().getStringExtra("HOMEPAGE_KEY"));
            aboutText.setText(getIntent().getStringExtra("ABOUT_KEY"));
        }
    }

    public void btnhomepage(View view) {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String homepageText = bundle.getString("HOMEPAGE_KEY");
            Intent iten = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+ homepageText));
            startActivity(iten);
        }
    }
}
