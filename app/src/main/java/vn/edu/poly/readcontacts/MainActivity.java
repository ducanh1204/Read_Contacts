package vn.edu.poly.readcontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readContact();
    }

    public void readContact() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        List<String> list = new ArrayList<>();
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            String s=null;

            s=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))+"\n";
            s+=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            list.add(s);
            cursor.moveToNext();
        }
        cursor.close();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        ListView lvList = (ListView) findViewById(R.id.lvList);
        lvList.setAdapter(arrayAdapter);

    }
}