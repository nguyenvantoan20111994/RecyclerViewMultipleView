package framgia.vn.messagesimi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String AB =
        "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();
    private RecyclerView mRecyclerChat;
    private ImageButton mImageSend;
    private EditText mEditUserMessage;
    private List<Object> mMessageList;
    private ChatAdapter mChatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        stepUpDate();
        stepUpAdapter();
    }

    private void initView() {
        mRecyclerChat = (RecyclerView) findViewById(R.id.recycler_message);
        mImageSend = (ImageButton) findViewById(R.id.button_send);
        mEditUserMessage = (EditText) findViewById(R.id.edit_message);
        mImageSend.setOnClickListener(this);
    }

    private void stepUpDate() {
        mMessageList = new ArrayList<>();
        mMessageList.add(new Boss("Hello"));
    }

    private void stepUpAdapter() {
        mChatAdapter = new ChatAdapter(mMessageList, getApplicationContext());
        mRecyclerChat.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerChat.setAdapter(mChatAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_send:
                // neu null thi return luon k thuc hen ham duoi
                if (TextUtils.isEmpty(mEditUserMessage.getText().toString())) return;
                mMessageList.add(new User(mEditUserMessage.getText().toString()));
                // cái này a không muốn viết hàm random e có thể truyền text vào và công với a
                // chuỗi string random của e
                mMessageList.add(new Boss(mEditUserMessage.getText().toString() + "dasdasdasdasd"));
                mEditUserMessage.setText("");
                mChatAdapter.notifyDataSetChanged();
                // scroll vi tri cuoi
                mRecyclerChat.scrollToPosition(mMessageList.size() - 1);
                break;
        }
    }
}
