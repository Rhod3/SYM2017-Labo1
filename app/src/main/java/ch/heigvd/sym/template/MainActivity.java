/**
 * File     : MainActivity.java
 * Project  : TemplateActivity
 * Author   : Markus Jaton 2 juillet 2014
 * Fabien Dutoit 20 septembre 2016
 * IICT / HEIG-VD
 * <p>
 * mailto:fabien.dutoit@heig-vd.ch
 * <p>
 * This piece of code reads a [email_account / password ] combination.
 * It is used as a template project for the SYM module element given at HEIG-VD
 * Target audience : students IL, TS, IE [generally semester 1, third bachelor year]
 * <p>
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package ch.heigvd.sym.template;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ch.heigvd.sym.template.global.Global;

public class MainActivity extends AppCompatActivity {

    // For logging purposes
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final Pair[] credentials = {
            Pair.create("toto@tutu.com", "tata"),
            Pair.create("nadir.benallal@heig-vd.ch", "blu"),
            Pair.create("nicolas.rod@heig-vd.ch", "humour"),
            Pair.create("basile.chatillon@heig-vd.ch", "sat")
    };
    private static final String AT = "@";

    // GUI elements
    private EditText email = null;
    private EditText password = null;
    private Button signIn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show the welcome screen / login authentication dialog
        setContentView(R.layout.authent);

        Toast.makeText(MainActivity.this, MainActivity.this.getClass().getSimpleName() + " : onCreate()", Toast.LENGTH_SHORT).show();

        // Link to GUI elements
        this.email = (EditText) findViewById(R.id.email);
        this.password = (EditText) findViewById(R.id.password);
        this.signIn = (Button) findViewById(R.id.buttOk);

        // Then program action associated to "Ok" button
        signIn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                /*
				 * There you have to check out if the email/password
				 * combination given is valid or not
				 */
                String mail = email.getText().toString();
                String passwd = password.getText().toString();

                if (!mail.contains(AT)) {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.wrong_email_format), Toast.LENGTH_LONG).show();

                } else
                    if (isValid(mail, passwd)) {
					/* Ok, valid combination, do something or launch another activity...
					 * The current activity could be finished, but it is not mandatory.
					 * To launch activity MyActivity.class, try something like :
					 * 
					 * 			Intent intent = new Intent(this, ch.heigvd.sym.MyActivity.class);
					 * 			intent.putExtra("emailEntered", mail);
					 *			intent.putExtra("passwordGiven", passwd);
					 *			this.startActivity(intent); 
					 *
					 * Alternately, you could also startActivityForResult if you are awaiting a result.
					 * In the latter case, you have to indicate an int parameter to identify MyActivity
					 * 
					 * If you haven't anything more to do, you may finish()...
					 * But just display a small message before quitting...
					 */

					Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(Global.EMAIL_KEY, mail);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    Toast.makeText(MainActivity.this, getResources().getString(R.string.good), Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    // Wrong combination, display pop-up dialog and stay on login screen
                    showErrorDialog(mail, passwd);
                }
            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(MainActivity.this, MainActivity.this.getClass().getSimpleName() + " : onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(MainActivity.this, MainActivity.this.getClass().getSimpleName() + " : onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(MainActivity.this, MainActivity.this.getClass().getSimpleName() + " : onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(MainActivity.this, MainActivity.this.getClass().getSimpleName() + " : onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Toast.makeText(MainActivity.this, MainActivity.this.getClass().getSimpleName() + " : onRestart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(MainActivity.this, MainActivity.this.getClass().getSimpleName() + " : onDestroy()", Toast.LENGTH_SHORT).show();
    }

    /**
     * Modifié par rapport au projet Template: Vérifie que la combinaison mail/password passé
     * en paramètre existent dans l'application et vérifie leur validité.
     * @param mail Le mail de l'utilisateur
     * @param passwd Le mot de passe
     * @return True si l'authentification a réussi, False sinon
     */
    private boolean isValid(String mail, String passwd) {
        if (mail == null || passwd == null) {
            Log.w(TAG, "isValid(mail, passwd) - mail and passwd cannot be null !");
            return false;
        }

        // Return true if combination valid, false otherwise
        for (int i = 0; i < credentials.length; i++)
        {
            if (credentials[i].first.equals(mail) && credentials[i].second.equals(passwd))
                return true;
        }

        return false;
    }

    protected void showErrorDialog(String mail, String passwd) {
		/*
		 * Pop-up dialog to show error
		 */
        AlertDialog.Builder alertbd = new AlertDialog.Builder(this);
        alertbd.setIcon(R.drawable.ic_email_fail);
        alertbd.setTitle(R.string.wronglogin);
        alertbd.setMessage(R.string.wrong);
        alertbd.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // we do nothing...
                // dialog close automatically
                clearText(email);
                clearText(password);
            }
        });
        alertbd.create().show();
    }

    /**
     * @brief Permet de vider le text d'un EditText
     * @param edit EditText à vider
     */
    private void clearText(EditText edit) {
        edit.getText().clear();
    }

}
