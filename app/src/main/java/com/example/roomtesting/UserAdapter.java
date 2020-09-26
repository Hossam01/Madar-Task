package com.example.roomtesting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Callback mCallback;
    private List<User> mUserList;

    public UserAdapter(List<User> usersList) {
        mUserList = usersList;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (mUserList != null && mUserList.size() > 0) {
            return mUserList.size();
        } else {
            return 0;
        }
    }

    public interface Callback {
        void onDeleteClick(User mUser);
    }

    public void addItems(List<User> userList) {
        mUserList = userList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.imageViewDelete)
        ImageView imageViewDelete;

        @BindView(R.id.textViewDisplayName)
        TextView textViewName;

        @BindView(R.id.textViewJobtitle)
        TextView textViewTitle;

        @BindView(R.id.textViewAge)
        TextView textViewAge;

        @BindView(R.id.textViewGender)
        TextView textViewGender;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void clear() {
            textViewTitle.setText("");
            textViewName.setText("");
            textViewAge.setText("");
            textViewGender.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            final User mUser = mUserList.get(position);


            if (mUser.getUserName() != null) {
                textViewName.setText( mUser.getUserName());
            }

            if (mUser.getTitle() != null) {
                textViewTitle.setText( mUser.getTitle());
            }
            if (mUser.getAge() != null) {
                textViewAge.setText( mUser.getAge());
            }

            if (mUser.getGender() != null) {
                textViewGender.setText( mUser.getGender());
            }
            imageViewDelete.setOnClickListener(v -> mCallback.onDeleteClick(mUser));
        }
    }
}
