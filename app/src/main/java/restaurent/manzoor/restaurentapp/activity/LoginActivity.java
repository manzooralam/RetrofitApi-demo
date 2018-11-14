package restaurent.manzoor.restaurentapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.digitsmigrationhelpers.AuthMigrator;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Collections;

import restaurent.manzoor.restaurentapp.R;
import restaurent.manzoor.restaurentapp.common.ClsGeneral;
import restaurent.manzoor.restaurentapp.common.ConstantValue;
import restaurent.manzoor.restaurentapp.common.Utilz;
import restaurent.manzoor.restaurentapp.retrofitnetwork.RetrofitDataProvider;

/**
 * Created by Shankar on 4/1/2018.
 */

public class LoginActivity extends AppCompatActivity {
    //google login
    // Request sing in code. Could be anything as you required.
    public static final int RequestSignInCode = 7;
    // Firebase Auth Object.
    public FirebaseAuth firebaseAuth;
    // Google API Client object.
    public GoogleApiClient googleApiClient;
    // Sing out button.
    Button SignOutButton;
    // Google Sign In button .
    com.google.android.gms.common.SignInButton signInButton;
    // TextView to Show Login User Email and Name.
    TextView LoginUserName, LoginUserEmail;

    //end here

    public static final int RC_SIGN_IN = 111;

    private RetrofitDataProvider retrofitDataProvider;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        retrofitDataProvider = new RetrofitDataProvider(this);
        //initWidgit();
        /* signInButton = (com.google.android.gms.common.SignInButton)findViewById(R.id.sign_in_button);*/

        firebaseAuth = FirebaseAuth.getInstance();

// Creating and Configuring Google Sign In object
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
// Creating and Configuring Google Api Client.
        googleApiClient = new GoogleApiClient.Builder(LoginActivity.this)
                .enableAutoManage(LoginActivity.this , new GoogleApiClient.OnConnectionFailedListener() {
                            @Override
                            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                            }
                        }
                        /* OnConnectionFailedListener */
                )
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

    }
    public void phoneSign(View view) {
        initWidgit();
    }
    public void gmailSign(View view) {
        // Passing Google Api Client into Intent.
        Intent AuthIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);

        startActivityForResult(AuthIntent, RequestSignInCode);
    }
    public void goSignUpActivity(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }


    private void initWidgit() {
        checkSession();
    }

    private void checkSession() {
        AuthMigrator.getInstance().migrate(true).addOnSuccessListener(this,
                new OnSuccessListener() {

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onSuccess(Object o) {
                        FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
                        if (u != null) {
                            // StaticConfig.UID = u.getUid();
                            callApi(u.getPhoneNumber());

                        } else {
                            startActivityForResult(
                                    AuthUI.getInstance()
                                            .createSignInIntentBuilder()
                                            .setProviders(
                                                    //  Arrays.asList(
                                                    Collections.singletonList(
                                                            new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build()
                                                    )
                                            )
                                            .setTheme(R.style.LoginTheme)
                                            .setLogo(R.mipmap.logo)
                                            .build(),
                                    RC_SIGN_IN);
                        }
                    }
                }).addOnFailureListener(this,
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                });
    }
   //if user not null then callApi() called
    private void callApi(String phoneNumber) {
        String mobile = phoneNumber.replace("+91", "");
        ClsGeneral.setPreferences(LoginActivity.this, ConstantValue.MOBILE, mobile);
        if (Utilz.isInternetConnected(LoginActivity.this)) {
            Utilz.showProgress(LoginActivity.this);
            ClsGeneral.setPreferences(this,ConstantValue.USERID, mobile);
            ClsGeneral.setPreferences(this,ConstantValue.MOBILE, mobile);

            if (ClsGeneral.getPreferences(this, ConstantValue.EMAIL).equals("")){
               Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                      intent.putExtra("fromLoginActivity",ConstantValue.MOBILE);
                      startActivity(intent);
                       finish();
            }else {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

        } else {
            Utilz.displayMessageAlert(getResources().getString(R.string.nointernetconnection), LoginActivity.this);
        }
    }
    private void callApi(String email,String name) {
        /*String mobile = phoneNumber.replace("+91", "");*/

        ClsGeneral.setPreferences(LoginActivity.this, ConstantValue.EMAIL, email);
        ClsGeneral.setPreferences(LoginActivity.this, ConstantValue.USERID, email);
        if (Utilz.isInternetConnected(LoginActivity.this)) {
            Utilz.showProgress(LoginActivity.this);
            ClsGeneral.setPreferences(this,ConstantValue.EMAIL, email);
            ClsGeneral.setPreferences(this,ConstantValue.USERID, email);
            ClsGeneral.setPreferences(this,ConstantValue.NAME,name);
            if (ClsGeneral.getPreferences(this, ConstantValue.MOBILE).equals("")){
                Intent i= new Intent(LoginActivity.this, ProfileActivity.class);
                i.putExtra("fromLoginActivity",ConstantValue.EMAIL);
                i.putExtra("fromLoginActivity",ConstantValue.NAME);
                startActivity(i);
                finish();
            }else {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

        } else {
            Utilz.displayMessageAlert(getResources().getString(R.string.nointernetconnection), LoginActivity
                    .this);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == ResultCodes.OK) {
                FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
                //StaticConfig.UID = u.getUid();
                callApi(response.getPhoneNumber());

            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Log.e("Login", "Login canceled by User");
                }
                /*if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                }*/
               /* if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    Log.e("Login", "Unknown Error");
                }*/
            }
            Log.e("Login", "Unknown sign in response");

        }else if (requestCode == RequestSignInCode){
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (googleSignInResult.isSuccess()){

                GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();

                FirebaseUserAuth(googleSignInAccount);
            }
        }
    }

    public void FirebaseUserAuth(GoogleSignInAccount googleSignInAccount) {

        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);

        Toast.makeText(LoginActivity.this,""+ authCredential.getProvider(),Toast.LENGTH_LONG)
                .show();

        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> AuthResultTask) {

                        if (AuthResultTask.isSuccessful()){

                            // Getting Current Login user details.
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                            // Hiding Login button

                         /*  // Setting up name into TextView.
                            LoginUserName.setText("NAME = "+ firebaseUser.getDisplayName().toString());

                             // Setting up Email into TextView.
                            LoginUserEmail.setText("Email = "+ firebaseUser.getEmail().toString()+
                                    ", phoneNo "+firebaseUser.getPhoneNumber());*/

                         /*   ClsGeneral.setPreferences(GPlusActivity.this, ConstantValue.EMAIL,
                                    firebaseUser.getEmail().toString());
                            ClsGeneral.setPreferences(GPlusActivity.this, ConstantValue.NAME, firebaseUser.getDisplayName().toString());*/

                            callApi(firebaseUser.getEmail().toString(),firebaseUser.getDisplayName().toString());

                        }else {
                            Toast.makeText(LoginActivity.this,"Something Went Wrong",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
