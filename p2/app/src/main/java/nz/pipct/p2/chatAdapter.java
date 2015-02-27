package nz.pipct.p2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class chatAdapter extends ArrayAdapter<Map<String, Object>> {
    private final Activity context;
    private final ArrayList<Map<String, Object>> messages;

    public chatAdapter(Activity context, ArrayList<Map<String, Object>> messages) {
        super(context, R.layout.chat_message, messages);
        this.context = context;
        this.messages = messages;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        /* Setup the row */
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.chat_message, null, true);
        ImageView imgProfile = (ImageView)rowView.findViewById(R.id.img_profile);
        TextView txtPost = (TextView)rowView.findViewById(R.id.txt_post);
        TextView txtName = (TextView)rowView.findViewById(R.id.txt_name);
        TextView txtTime = (TextView)rowView.findViewById(R.id.txt_time);

        /* Put data in the row */
        Map<String, Object> message = messages.get(position);
        imgProfile.setImageDrawable(context.getResources().getDrawable(R.mipmap.test_profile)); //TODO: Get image from ByteArray in map
        txtPost.setText(message.get("post").toString());
        txtName.setText(message.get("name").toString()+" | ");
        txtTime.setText(message.get("time").toString());

        return rowView;
    }
}
