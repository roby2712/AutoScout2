package autoscout.it.autoscout2;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MailActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.buttonEmail)
    Button buttonEmail;

    @Bind(R.id.editTextNome)
    EditText editTextNome;

    @Bind(R.id.editTextEmail)
    EditText editTextEmail;

    @Bind(R.id.editTextMessaggio)
    EditText editTextMessaggio;

    @Bind(R.id.editTextTelefono)
    EditText editTextTelefono;

    @Bind(R.id.textInputLayoutNome)
    TextInputLayout textInputLayoutNome;

    @Bind(R.id.textInputLayoutEmail)
    TextInputLayout textInputLayoutEmail;

    @Bind(R.id.textInputLayoutTelefono)
    TextInputLayout textInputLayoutTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mail);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);

        //getSupportActionBar().setTitle(getR);

        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonEmail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!validateNome()) {
                    return;
                }

                if (!validateEmail()) {
                    return;
                }

                if (!validateTelefono()) {
                    return;
                }

                Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();

            }

        });

    }

    private boolean validateNome() {

        editTextNome.clearFocus();

        if (editTextNome.getText().toString().trim().isEmpty()) {

            textInputLayoutNome.setError(getString(R.string.string_email_error_nome));

            requestFocus(editTextNome);

            return false;

        } else {

            textInputLayoutNome.setErrorEnabled(false);

        }

        return true;
    }

    private boolean validateEmail() {

        editTextEmail.clearFocus();

        String email = editTextEmail.getText().toString().trim();

        if (email.isEmpty()) {

            textInputLayoutEmail.setError(getString(R.string.string_email_error_empty_email));

            requestFocus(editTextEmail);

            return false;

        }

        if (!isValidEmail(email)) {

            textInputLayoutEmail.setError(getString(R.string.string_email_error_email));

            requestFocus(editTextEmail);

            return false;

        } else {

            textInputLayoutEmail.setErrorEnabled(false);

        }

        return true;
    }

    private boolean validateTelefono() {

        editTextTelefono.clearFocus();

        if (editTextTelefono.getText().toString().trim().isEmpty()) {

            textInputLayoutTelefono.setError(getString(R.string.string_email_error_telefono));

            requestFocus(editTextTelefono);

            return false;

        } else {

            textInputLayoutTelefono.setErrorEnabled(false);

        }

        return true;

    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_activity_mail, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}