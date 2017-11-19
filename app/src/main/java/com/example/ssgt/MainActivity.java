package com.example.ssgt;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    Button btn_login;
    Button btn_signin;

    private GoogleMap mMap;
    String tmp;
    String tpw;
    JSONArray retJson;

    EditText eid;
    EditText epw;

    private static final int READ_CONTACTS_PERMISSIONS_REQUEST = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        eid = (EditText)findViewById(R.id.eid);
        epw = (EditText)findViewById(R.id.epw);



        tpw = "";


        final Intent i = new Intent(getApplicationContext(), MenuActivity.class);

        final CustomMapFragment map = (CustomMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        map.getMapAsync(this);

        //메인메뉴 가는 코드
//        Toast.makeText(getApplicationContext(), "dd", Toast.LENGTH_LONG);
//        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);



        //startActivity(intent);

        btn_login = (Button) findViewById(R.id.login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //hi branch되나볼까나 흠 합쳐지나


                try {

                    DownloadWebPageTask dwTask = new DownloadWebPageTask(new DownloadWebPageTask.AsyncResponse() {   //로그인 작업
                        @Override
                        public void processFinish(JSONArray ret) throws JSONException {

                            Log.e("err","processFinish");
                            Toast.makeText(getApplicationContext(),"processFinish:",Toast.LENGTH_SHORT).show();
                            retJson = ret;

                            while(retJson.get(0).equals(null)); //jsonArray가 NULL이면 대기

                            for(int i = 0 ; i< retJson.length(); i++){

                                JSONObject json = retJson.getJSONObject(i);

                                String id = json.getString("ID");
                                String pw = json.getString("PW");
                                tpw = pw;
                                //  String quantity = json.getString("Name");



                                Toast.makeText(getApplicationContext(),"되나:"+id+pw,Toast.LENGTH_SHORT).show();
                            }

                            Toast.makeText(getApplicationContext(),"tpw:"+tpw+"epw:"+epw.getText().toString(),Toast.LENGTH_SHORT).show();

                            if(tpw.equals(epw.getText().toString()))
                                startActivity(i);
                            else
                                Toast.makeText(getApplicationContext(),"비밀번호 오류",Toast.LENGTH_SHORT).show();

                        }
                    });

                    tmp = dwTask.execute("http://13.124.85.122:52273/search").get();
                    Log.i("tmp",tmp);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(),"되나1:",Toast.LENGTH_SHORT).show();
                Log.e("err","retJson NULL?");

               // while(!tpw.equals(""));



            }
        });

        btn_signin = (Button)findViewById(R.id.signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), InterestActivity.class);

                startActivity(intent);
            }
        });

        getPermissionToReadUserContacts();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setTrafficEnabled(true);
        mMap.setIndoorEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == READ_CONTACTS_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Read Contacts permission granted", Toast.LENGTH_SHORT).show();

                   setUserNumber();

            } else {
                Toast.makeText(this, "Read Contacts permission denied", Toast.LENGTH_SHORT).show();

                Toast.makeText(this, "Read Contacts permission is required.", Toast.LENGTH_SHORT).show();

                closeNow();

            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }


}

    public void getPermissionToReadUserContacts() {
        // 1) Use the support library version ContextCompat.checkSelfPermission(...) to avoid
        // checking the build version since Context.checkSelfPermission(...) is only available
        // in Marshmallow
        // 2) Always check for permission (even if permission has already been granted)
        // since the user can revoke permissions at any time through Settings
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {



            // The permission is NOT already granted.
            // Check if the user has been asked about this permission already and denied
            // it. If so, we want to give more explanation about why the permission is needed.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_CONTACTS)) {
                    // Show our own UI to explain to the user why we need to read the contacts
                    // before actually requesting the permission and showing the default UI
                }
            }

            // Fire off an async request to actually get the permission
            // This will show the standard permission request dialog UI
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                        READ_CONTACTS_PERMISSIONS_REQUEST);
            }
        }
        else
        {
            setUserNumber();
        }
    }

    private void closeNow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        } else {
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

       // setUserNumber();

    }

    void setUserNumber(){  // 번호 읽어서 국제번호로 변환 후 id란에 Setting


        TelephonyManager t = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNumber = "";

        String main_data[] = {"data1", "is_primary", "data3", "data2", "data1", "is_primary", "photo_uri", "mimetype"}; //번호 가져오기
        Object object = getContentResolver().query(Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI, "data"),
                main_data, "mimetype=?",
                new String[]{"vnd.android.cursor.item/phone_v2"},
                "is_primary DESC");
        if (object != null) {
            do {
                if (!((Cursor) (object)).moveToNext())
                    break;
                phoneNumber = ((Cursor) (object)).getString(4);
                Toast.makeText(getApplicationContext(),phoneNumber,Toast.LENGTH_SHORT).show();

            } while (true);
            ((Cursor) (object)).close();
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            phoneNumber = PhoneNumberUtils.normalizeNumber(phoneNumber);
            phoneNumber= PhoneNumberUtils.formatNumberToE164(phoneNumber,t.getNetworkCountryIso().toUpperCase());
            Toast.makeText(getApplicationContext(),phoneNumber,Toast.LENGTH_SHORT).show();
            eid.setText(phoneNumber);
            eid.setEnabled(false);
        }




    }

}
