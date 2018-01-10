package mindcraft.scouting3495test;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONObject;

import java.lang.Throwable;


public class RegisterActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etPassword;

    private Button bRegister;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();


        final EditText etName = findViewById(R.id.etName);
        final EditText etPassword = findViewById(R.id.etPassword);
        final Button bRegister = findViewById(R.id.bRegister);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {

                    startActivity(new Intent(RegisterActivity.this, AccountActivity.class));
                }
            }
        };

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignIn(){
        String name = etName.getText().toString();
        String password = etPassword.getText().toString();

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {

            Toast.makeText(RegisterActivity.this, "Fields Are Empty", Toast.LENGTH_LONG).show();

        }else{

            mAuth.signInWithEmailAndPassword(name, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Sign In Problem", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}



