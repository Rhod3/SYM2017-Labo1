package ch.heigvd.sym.template;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import ch.heigvd.sym.template.global.Global;

import static java.security.AccessController.getContext;

public class George extends AppCompatActivity {

    private TextView email = null;
    private TextView imei = null;
    private ImageView photo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_george);

        //Tracé du cycle de vie de l'activité à l'étape onCreate
        Toast.makeText(George.this, George.this.getClass().getSimpleName() + " : onCreate()", Toast.LENGTH_SHORT).show();

        email = (TextView) findViewById(R.id.email_textview);
        imei = (TextView) findViewById(R.id.imei);
        photo = (ImageView) findViewById(R.id.photo);

        //Affichage de l'adresse email récupérée depuis MainActivity
        Bundle mainActivityExtras = getIntent().getExtras();
        email.setText(mainActivityExtras.getString(Global.EMAIL_KEY));

        //Affichage de l'IMEI
        String androidID = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        if (androidID == null)
        {
            androidID = "IMEI";
        }

        imei.setText(androidID);

        //Affichage de l'image associée à l'adresse email précédemment affichée
        File downloadsSD = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        if (downloadsSD.isDirectory())
        {
            String path = downloadsSD.getAbsolutePath() + "/fabdut.jpg";
            photo.setImageBitmap(BitmapFactory.decodeFile(path));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(George.this, George.this.getClass().getSimpleName() + " : onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(George.this, George.this.getClass().getSimpleName() + " : onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(George.this, George.this.getClass().getSimpleName() + " : onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(George.this, George.this.getClass().getSimpleName() + " : onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Toast.makeText(George.this, George.this.getClass().getSimpleName() + " : onRestart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(George.this, George.this.getClass().getSimpleName() + " : onDestroy()", Toast.LENGTH_SHORT).show();
    }

}
