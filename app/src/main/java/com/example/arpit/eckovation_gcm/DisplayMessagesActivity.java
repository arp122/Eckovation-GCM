package com.example.arpit.eckovation_gcm;

/**
 * Created by arpit on 24-10-2015.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class DisplayMessagesActivity extends AppCompatActivity {


    private ListView listView;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_message_activity);

        listView = (ListView) findViewById(R.id.lvMessages);

        sharedpreferences = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putInt("number", 0);
        editor.commit();


        DatabaseHandler db= new DatabaseHandler(this);
        db.createTable();

        List<Details> details=db.getDetails();
        List<Message> rowItems = new ArrayList<Message>();

        for (Details cn : details)
        {
            Message item = new Message(cn.getName(),cn.getTime(),cn.getMessge());
            rowItems.add(item);

        }

        MessageAdapter adapter = new MessageAdapter(getApplicationContext(),rowItems);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    public class MessageAdapter extends BaseAdapter
    {
        Context context;
        List<Message> rowItems;
        int pos;

        public MessageAdapter(Context context, List<Message> items) {
            this.context = context;
            this.rowItems = items;

        }

        private class ViewHolder {

            TextView tvTitle;
            TextView tvDesc;
            TextView tvTimestamp;

        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            pos = position;
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_row, null);
                holder = new ViewHolder();

                holder.tvTitle = (TextView) convertView.findViewById(R.id.tvFrom);
                holder.tvDesc = (TextView) convertView.findViewById(R.id.tvMessage);
                holder.tvTimestamp=(TextView) convertView.findViewById(R.id.tvTimestamp);

                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            Message rowItem = (Message) getItem(position);


            holder.tvTitle.setText(rowItem.getName());
            holder.tvDesc.setText(rowItem.getMessage());
            CharSequence ago = DateUtils.getRelativeTimeSpanString(Long.parseLong(rowItem.getTimestamp()), System.currentTimeMillis(),0L, DateUtils.FORMAT_ABBREV_ALL);
            holder.tvTimestamp.setText(ago);

            //Log.d("Values entered", "" + rowItem.getName() + rowItem.getTimestamp() +rowItem.getMessage());
            return convertView;
        }

        @Override
        public int getCount() {
            return rowItems.size();
        }

        @Override
        public Object getItem(int position) {
            return rowItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return rowItems.indexOf(getItem(position));
        }


        @Override
        public int getViewTypeCount() {
            return 2;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i= new Intent(this,SendMessage.class);
        startActivity(i);
    }
}