package com.example.roomtesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.roomtesting.databinding.ActivityAddUserBinding;
import com.example.roomtesting.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.ObservableOnSubscribe;

public class MainActivity extends AppCompatActivity implements UserAdapter.Callback  {


    AppDatabase database;

    UserAdapter mUserAdapter;
    LinearLayoutManager mLayoutManager;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database=AppDatabase.getDatabaseInstance(this);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onViewClicked();
            }
        });
        setUp();
    }

    private void setUp() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerView.setLayoutManager(mLayoutManager);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        mUserAdapter = new UserAdapter(new ArrayList<User>());
        mUserAdapter.setCallback(this);
        prepareDemoContent();
        binding.recyclerView.setAdapter(mUserAdapter);

    }

    private void prepareDemoContent() {
        User mUser1 = new User("Ahmed", "26", "Engineer", "male");
        database.userDao().insertUser(mUser1);

        User mUser2 = new User("Hossam", "24", "android developer", "male");
        database.userDao().insertUser(mUser2);

        User mUser3 = new User("Mahmoud", "22", "Accountant", "male");
        database.userDao().insertUser(mUser3);

        User mUser4 = new User("Omar", "20", "ios developer", "male");
        database.userDao().insertUser(mUser4);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mUserAdapter.addItems((List<User>) database.userDao().getAll());

    }

    public void onViewClicked() {
        AddUserActivity.startActivity(this);
        makeToast();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDatabase.destroyInstance();
    }

    @Override
    public void onDeleteClick(User mUser) {
        database.userDao().delete(mUser);
        mUserAdapter.addItems((List<User>) database.userDao().getAll());
    }

    public void makeToast() {
        Toast toast = new Toast(this);
        View customToastView = getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.customToastContainer));
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(customToastView);
        toast.show();
    }
}
