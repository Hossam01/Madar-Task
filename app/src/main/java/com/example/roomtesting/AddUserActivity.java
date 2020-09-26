package com.example.roomtesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.roomtesting.databinding.ActivityAddUserBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddUserActivity extends AppCompatActivity {


    AppDatabase database;
    ActivityAddUserBinding binding;

    public static void startActivity(Context mContext) {
        Intent mIntent = new Intent(mContext, AddUserActivity.class);
        mContext.startActivity(mIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = AppDatabase.getDatabaseInstance(this);
        binding.buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onViewClicked();
            }
        });
    }

    public void onViewClicked() {
        if (binding.textname.getText().toString().trim().isEmpty()) {
            binding.textInputLayoutFirstName.setError(getString(R.string.error_msg_firstname));
            return;
        }

        if (binding.textTitle.getText().toString().trim().isEmpty()) {
            binding.textInputLayoutEmail.setError(getString(R.string.error_msg_email));
            return;
        }
        User mUser = new User(binding.textname.getText().toString(),binding.textAge.getText().toString(),binding.textTitle.getText().toString() ,binding.textGender.getText().toString());
        database.userDao().insertUser(mUser);
        finish();
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();

    }
}
