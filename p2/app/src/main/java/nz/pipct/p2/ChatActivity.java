package nz.pipct.p2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


public class ChatActivity extends ActionBarActivity {
    ArrayList<Message> messages;
    ListView messageList;
    ChatAdapter adapter;

    private Boolean loggedIn() {
        //TODO: Check if logged in
        return false;
    }

    private void displayMessage(String post, String name, String time) {
        Message message = new Message();
        message.post = post;
        message.name = name;
        message.time = time;
        messages.add(message);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (loggedIn()) {
            setContentView(R.layout.activity_chat);

            /* Setup List View */
            messages = new ArrayList<>();
            messageList = (ListView) findViewById(R.id.list_chat);
            adapter = new ChatAdapter(this, messages);
            messageList.setAdapter(adapter);

            /* On send button click */
            ImageButton btnSend = (ImageButton) findViewById(R.id.btn_send);
            btnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText text = (EditText) findViewById(R.id.edit_message);
                    displayMessage(text.getText().toString(), "Wally West", "π seconds ago");
                    text.setText("");
                }
            });
        } else {
            //Take to login screen
            Intent intent = new Intent(ChatActivity.this, login.class);
            startActivity(intent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
