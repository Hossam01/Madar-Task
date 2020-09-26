package com.example.roomtesting;

import io.reactivex.Observable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Insert(onConflict = REPLACE)
    void insertUser(User mUser);

    @Insert
    void insertAllUser(User... mUsersList);

    @Delete
    void delete(User mUser);

    @Update
    void updateUser(User mUser);

    @Query("SELECT * FROM user WHERE uid = :uId")
    User getUserById(int uId);


    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);


}
