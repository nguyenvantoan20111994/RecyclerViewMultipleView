package framgia.vn.messagesimi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by toannguyen201194 on 04/05/2017.
 */
public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int MESSAGE_BOSS = 1;
    public static final int MESSAGE_USER = 2;
    private List<Object> mMessageList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    // ham contrustor sẽ được vào đầu tiên
    public ChatAdapter(List<Object> messageList, Context context) {
        mMessageList = messageList;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    // hàm này được gọi thứ tư. khi biết nó thuộc loại nào rồi thì thực hiên vẽ view item
    // vi du o vị trí position ==0 thì view luôn là con boss nen ta vẽ viewholder là con boss
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MESSAGE_BOSS:
                View bossView = mLayoutInflater.inflate(R.layout.item_recycler_boss_message, parent,
                    false);
                return new MessageBossViewHolder(bossView);
            default:
                View userView = mLayoutInflater.inflate(R.layout.item_recycler_user_message, parent,
                    false);
                return new MessageUserViewHolder(userView);
        }
    }
    // hàm này được gọi cuối cùng khi view Holder vẽ xong sẽ settext hay làm gì đó
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case MESSAGE_BOSS:
                Boss boss = (Boss) mMessageList.get(position);
                MessageBossViewHolder messageBossViewHolder = (MessageBossViewHolder) holder;
                messageBossViewHolder.mTxtMessageBoss.setText(boss.getMessage());
                break;
            default:
                User user = (User) mMessageList.get(position);
                MessageUserViewHolder messageUserViewHolder = (MessageUserViewHolder) holder;
                messageUserViewHolder.mTxtMessageUser.setText(user.getMessage());
                break;
        }
    }
    // hàm này được gọi thứ 2 mục đích là get tông các item để recyclerview hien thi ra
    @Override
    public int getItemCount() {
        return mMessageList == null ? 0 : mMessageList.size();
    }
    // ham nay se duoc goi thu 3 , check xem nó là object nào thì return loại đấy
    @Override
    public int getItemViewType(int position) {
        if (mMessageList.get(position) instanceof Boss) {
            return MESSAGE_BOSS;
        } else {
            return MESSAGE_USER;
        }
    }

    // cai nay la viewHolder cua thang boss la cai thang co cai mat cuoi day. message cua no
    class MessageBossViewHolder extends RecyclerView.ViewHolder {
        protected TextView mTxtMessageBoss;
        protected ImageView mImageViewIconBoss;

        public MessageBossViewHolder(View itemView) {
            super(itemView);
            mTxtMessageBoss = (TextView) itemView.findViewById(R.id.text_boss);
            mImageViewIconBoss = (ImageView) itemView.findViewById(R.id.image_boss);
        }
    }

    // cai nay la viewHolder cua thang user la cai item k co cai mat cuoi day. message cua no
    class MessageUserViewHolder extends RecyclerView.ViewHolder {
        protected TextView mTxtMessageUser;

        public MessageUserViewHolder(View itemView) {
            super(itemView);
            mTxtMessageUser = (TextView) itemView.findViewById(R.id.text_user);
        }
    }
}
