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
            File file = new File(path);
            if (file.exists())
                Toast.makeText(this, "IT WORKED BITCH !", Toast.LENGTH_LONG).show();

            Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
            photo.setImageBitmap(bmp);

            System.out.println("blu : " + bmp == null ? " pute" : " alola");
        }
    }

}
