package ch.heigvd.sym.template;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import ch.heigvd.sym.template.global.Global;

public class ProfileActivity extends AppCompatActivity {

    private TextView email = null;
    private TextView imei = null;
    private ImageView photo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_george);

        // Tracé du cycle de vie de l'activité à l'étape onCreate
        Toast.makeText(ProfileActivity.this, ProfileActivity.this.getClass().getSimpleName() + " : onCreate()", Toast.LENGTH_SHORT).show();

        email = (TextView) findViewById(R.id.email_textview);
        imei = (TextView) findViewById(R.id.imei);
        photo = (ImageView) findViewById(R.id.photo);

        // Affichage de l'adresse email récupérée depuis MainActivity
        Bundle mainActivityExtras = getIntent().getExtras();
        email.setText(mainActivityExtras.getString(Global.EMAIL_KEY));

        // Affichage de l'IMEI
        String androidID = null;
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        // Gérer les différentes versions de SDK
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            androidID = telephonyManager.getImei();
        } else {
            androidID = telephonyManager.getDeviceId();
        }

        // Gestion d'un affichage par défaut si jamais l'IMEI est null (ce qui peut arriver dans un device virtuel)
        if (androidID == null)
            androidID = "IMEI";

        imei.setText(androidID);

        // Affichage de l'image associée à l'adresse email précédemment affichée
        File downloadsSD = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        if (downloadsSD.isDirectory())
        {
            String path = downloadsSD.getAbsolutePath() + "/pic.jpg";
            photo.setImageBitmap(BitmapFactory.decodeFile(path));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(ProfileActivity.this, ProfileActivity.this.getClass().getSimpleName() + " : onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(ProfileActivity.this, ProfileActivity.this.getClass().getSimpleName() + " : onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(ProfileActivity.this, ProfileActivity.this.getClass().getSimpleName() + " : onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(ProfileActivity.this, ProfileActivity.this.getClass().getSimpleName() + " : onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Toast.makeText(ProfileActivity.this, ProfileActivity.this.getClass().getSimpleName() + " : onRestart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(ProfileActivity.this, ProfileActivity.this.getClass().getSimpleName() + " : onDestroy()", Toast.LENGTH_SHORT).show();
    }

}
