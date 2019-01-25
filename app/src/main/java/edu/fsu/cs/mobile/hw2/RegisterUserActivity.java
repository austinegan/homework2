package edu.fsu.cs.mobile.hw2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;

public class RegisterUserActivity extends AppCompatActivity {
    private static final String TAG = "registerActivity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        Log.i(TAG, "This would be the first log AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        Button resetButton = (Button) findViewById(R.id.resetButton);
        Button registerButton = (Button) findViewById(R.id.registerButton);
        Log.i(TAG, "Log numero 2 ----------------------------------");
        final EditText emailText = findViewById(R.id.emailText);
        final EditText passwordText = findViewById(R.id.passwordText);
        final EditText confirmText = findViewById(R.id.confirmText);
        Log.i(TAG, "Log numero 3 ----------------------------------");
        final EditText nameText = findViewById(R.id.nameText);
        final Spinner classSpinner = findViewById(R.id.spinner);
        final RadioGroup roleGroup = findViewById(R.id.roleGroup);
        final CheckBox termsCheckbox = findViewById(R.id.checkBox);
        Log.i(TAG, "Log numero 4 ----------------------------------");


        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailText.setText("");
                passwordText.setText("");
                confirmText.setText("");
                nameText.setText("");
                classSpinner.setSelection(0);
                roleGroup.clearCheck();
                termsCheckbox.setChecked(false);
                Log.i(TAG, "Log numero 4.5 ----------------------------------");

            }
        });
        Log.i(TAG, "Log numero 5 ----------------------------------");

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Log numero 5.1 ----------------------------------");
                boolean passableInfo = true;
                String errors = "";
//                int errorCount = 0;
                if(emailText.getText().toString().isEmpty() || !emailText.getText().toString().contains("@") || !emailText.getText().toString().contains(".com")){
                    //Toast.makeText(getApplicationContext(), "Must enter a valid email address", Toast.LENGTH_LONG).show();
                    errors += "\nInvalid email address";
                    passableInfo = false;
                }
                if(passwordText.getText().toString().isEmpty()){
//                    Toast.makeText(getApplicationContext(), "Password field cannot be empty", Toast.LENGTH_LONG).show();
                    errors += "\nPassword field cannot be left empty";
                    passableInfo = false;
                }
                if(confirmText.getText().toString().isEmpty()){
//                    Toast.makeText(getApplicationContext(), "Confirm Password field cannot be empty", Toast.LENGTH_LONG).show();
                    errors += "\n'Confirm Password' field cannot be left empty";
                    passableInfo = false;
                }
                if(nameText.getText().toString().isEmpty()){
//                    Toast.makeText(getApplicationContext(), "Name field cannot be empty", Toast.LENGTH_LONG).show();
                    errors += "\nName cannot be left empty";
                    passableInfo = false;
                }
                if(classSpinner.getSelectedItem().toString().equals("Select a class...")){
//                    Toast.makeText(getApplicationContext(), "Must choose a class", Toast.LENGTH_LONG).show();
                    errors += "\nMust choose a class";
                    passableInfo = false;
                }
                if(roleGroup.getCheckedRadioButtonId() == -1){
//                    Toast.makeText(getApplicationContext(), "Must choose a role (Student or Instructor)", Toast.LENGTH_LONG).show();
                    errors += "\nMust choose a role (Student or Instructor)";
                    passableInfo = false;
                }
                if(!termsCheckbox.isChecked()){
                    errors += "\nMust agree to terms to proceed)";
                    passableInfo = false;
                }
                else{
                    int selectedRadioID = roleGroup.getCheckedRadioButtonId();
                    RadioButton rb = (RadioButton) findViewById(selectedRadioID);
                    //Toast.makeText(getApplicationContext(), rb.getText().toString() + " role selected", Toast.LENGTH_LONG).show();
                }
                if (passableInfo) {
                    String[] adminEmails = getResources().getStringArray(R.array.admins);
                    if(!passwordText.getText().toString().equals(confirmText.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Password  and Confirm Password must match", Toast.LENGTH_LONG).show();
                        passableInfo = false;
                    }
                    else if (Arrays.asList(adminEmails).contains(emailText.getText().toString())){
                        Log.i(TAG, "email is taken");
                        Toast.makeText(getApplicationContext(), "This email is taken", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Log.i(TAG, "Log numero 5.2 ----------------------------------");
                    Toast.makeText(getApplicationContext(), "Thank you for your acceptable information", Toast.LENGTH_LONG).show();
                    //go to display user information
                    loadNewActivity(emailText, passwordText, nameText, classSpinner, roleGroup);
                        }
                } else {
                    Log.i(TAG, "Log numero 5.3 ----------------------------------");
                    Toast.makeText(getApplicationContext(), "Encountered the following errors: " + errors, Toast.LENGTH_LONG).show();
                }
                Log.i(TAG, "Log numero 6 ----------------------------------");

            }
        });
        Log.i(TAG, "Log numero 7 ----------------------------------");

    }
    public void loadNewActivity(EditText emailText, EditText passwordText, EditText nameText, Spinner classSpinner, RadioGroup roleGroup){
        Intent myIntent = new Intent(RegisterUserActivity.this, DisplayUserActivity.class);
        myIntent.putExtra("email", emailText.getText().toString());
        myIntent.putExtra("password", passwordText.getText().toString());
        myIntent.putExtra("name", nameText.getText().toString());
        myIntent.putExtra("class", classSpinner.getSelectedItem().toString());
        int selectedRadioID = roleGroup.getCheckedRadioButtonId();
        RadioButton rb = findViewById(selectedRadioID);
        myIntent.putExtra("role", rb.getText().toString());
        Log.i(TAG, "hello log");
        RegisterUserActivity.this.startActivity(myIntent);
    }

//        boolean isAcceptableInfo(){
//        boolean passableInfo = true;
//        if(emailText.getText().toString().isEmpty() || !emailText.getText().toString().contains("@") || !emailText.getText().toString().contains(".com")){
//            Toast.makeText(this, "Must enter a valid email address", Toast.LENGTH_SHORT).show();
//            passableInfo = false;
//        }
//        if(passwordText.getText().toString().isEmpty()){
//            Toast.makeText(this, "Password field cannot be empty", Toast.LENGTH_SHORT).show();
//            passableInfo = false;
//        }
//        if(confirmText.getText().toString().isEmpty()){
//            Toast.makeText(this, "Confirm Password field cannot be empty", Toast.LENGTH_SHORT).show();
//            passableInfo = false;
//        }
//        if(nameText.getText().toString().isEmpty()){
//            Toast.makeText(this, "Name field cannot be empty", Toast.LENGTH_SHORT).show();
//            passableInfo = false;
//        }
//        if(!classSpinnner.getSelectedItem().toString().equals("Select a class...")){
//            Toast.makeText(this, "Must choose a class", Toast.LENGTH_SHORT).show();
//            passableInfo = false;
//        }
//        if(roleGroup.getCheckedRadioButtonId() == -1){
//            Toast.makeText(this, "Must choose a role (Student or Instructor)", Toast.LENGTH_SHORT).show();
//            passableInfo = false;
//        }
//        else{
//        int selectedRadioID = roleGroup.getCheckedRadioButtonId();
//            RadioButton rb = (RadioButton) findViewById(selectedRadioID);
//            Toast.makeText(this, rb.getText().toString() + " role selected", Toast.LENGTH_LONG).show();
//        }
//    if (passableInfo == false)
//        return false;
//    return true;
//    }



}
