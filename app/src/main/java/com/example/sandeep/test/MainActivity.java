package com.example.sandeep.test;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    View focusView;
    private DatePicker datePicker;
    private Calendar calendar;
    private EditText dateView;
    private EditText name, email, mobile;
    private int year, month, day;
    private Button next;
    private ImageButton selectDate;
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    validateAge(arg1, arg2 + 1, arg3);
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        dateView = (EditText) findViewById(R.id.Dob);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        name = (EditText) findViewById(R.id.first_name);
        email = (EditText) findViewById(R.id.email_id);
        mobile = (EditText) findViewById(R.id.mobile_no_signup);
        next = (Button) findViewById(R.id.signup_button);
        selectDate = (ImageButton) findViewById(R.id.select_date);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
//        showDate(year, month + 1, day);

        /*dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
            }
        });*/
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cancel = false;
                String age = dateView.getText().toString();
                String arr[] = age.split("/", -1);
                try {

                    if (!validateAge(Integer.parseInt(arr[2]), Integer.parseInt(arr[1]), Integer.parseInt(arr[0]))) {
                        Log.d("Age is", "validateAge");
                        dateView.setError("Age must be greater than 13 years");
                        focusView = dateView;
                        cancel = true;
                    }
                } catch (Exception e) {
                    dateView.setError("Date Of Birth is wrong");
                    focusView = dateView;
                    cancel = true;
                }

                if (TextUtils.isEmpty(dateView.getText())) {
                    dateView.setError("Date of Birth cannot be empty");
                    focusView = dateView;
                    cancel = true;
                }

                if (TextUtils.isEmpty(name.getText())) {
                    name.setError("Name cannot be empty");
                    focusView = name;
                    cancel = true;
                }
                if (TextUtils.isEmpty(email.getText())) {
                    email.setError("Email be empty");
                    focusView = email;
                    cancel = true;
                } else if (!validateEmail(email.getText().toString())) {
                    email.setError("Email format is wrong");
                    focusView = email;
                    cancel = true;
                }
                if (!TextUtils.isEmpty(mobile.getText()) && !validateMobileFormat(mobile.getText().toString())) {
                    mobile.setError("Mobile no must be 10 digits");
                    focusView = mobile;
                    cancel = true;
                }
                if (cancel) {
                    focusView.requestFocus();
                } else {
                    dateView.setError(null);
                    writeToFirebase();
                }
            }
        });

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
                dateView.setError(null);
            }
        });

    }

    private void writeToFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        UserModel userModel = new UserModel(email.getText().toString()
                , name.getText().toString()
                , mobile.getText().toString()
                , dateView.getText().toString());
        myRef.setValue(userModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), " Success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private boolean validateEmail(String email) {
        return Pattern.matches("[\\w\\.]+@[\\w]+\\..+", email);
    }

    private boolean validateMobileFormat(String mobile) {
        return Pattern.matches("\\d{10}", mobile);
    }

    private boolean validateAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();
        Log.d(" age ", ageS);
        if (age >= 13) {
//            Toast.makeText(getApplicationContext(), " Success", Toast.LENGTH_SHORT).show();
            return true;
        } else {
//            showDialog(999);
            return false;
        }
    }

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
