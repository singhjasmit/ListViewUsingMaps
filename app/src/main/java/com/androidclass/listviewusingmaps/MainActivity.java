package com.androidclass.listviewusingmaps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String NAME = "NAME";
    private static final String PHONE = "PHONE";
    private static final String TAG = MainActivity.class.getSimpleName();


    String[] from = {NAME, PHONE};
    int[] to = {R.id.txt_name, R.id.txt_phone};

    Button addBtn;
    EditText addName, addPhone;
    ListView myListView;
    SimpleAdapter myAdapter;


    private int[] colors = new int[] {
            0x30ffffff, 0x30808080
    };




    private ArrayList<HashMap<String, String>> contactsList = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createContactsList();


        addBtn = (Button) findViewById(R.id.btn_add);
        addBtn.setOnClickListener(this);

        addName = (EditText) findViewById(R.id.usr_name);
        addPhone = (EditText) findViewById(R.id.usr_phone);


        myListView = (ListView) findViewById(R.id.myListView);

        // declare the simple adpater and overide the getView method to change row colors

        myAdapter = new SimpleAdapter(this, contactsList, R.layout.item_row, from, to) {
            @Override
            public View getView (int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                int colorPos = position % colors.length;
                view.setBackgroundColor(colors[colorPos]);
                return view;
            }
        };


        myListView.setAdapter(myAdapter);


        // React to user clicks on item
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long id) {
                // We know the View is a TextView so we can cast it
                Toast.makeText(MainActivity.this, "Item with id [" + id + "] - Position [" + position + "]  clicked", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void createContactsList() {
        contactsList.add(createContact("James Hardy", "206-111-2222"));
        contactsList.add(createContact("Jane Eyre", "206-123-2424"));
        contactsList.add(createContact("John Balding", "206-333-2424"));

    }


    private HashMap<String, String> createContact(String vName, String vPhone) {
        HashMap<String, String> contact = new HashMap<String, String>();
        contact.put(NAME, vName);
        contact.put(PHONE, vPhone);
        return contact;
    }


    @Override
    public void onClick(View view) {
        String name = addName.getText().toString();
        String phone = addPhone.getText().toString();

        if ((name != null) && (phone != null)) {
            contactsList.add(createContact(name, phone));
            myAdapter.notifyDataSetChanged();

        } else {
            Toast.makeText(getBaseContext(), "Name and Phone number are required", Toast.LENGTH_SHORT).show();
        }
    }


    
}
