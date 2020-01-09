package hnu.example.bttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.sql.Connection;

public class Activity2 extends AppCompatActivity implements IBTMsgClient {
    private TextView mTextView1, mTextView2, mTextView3, connection;

    private ImageButton button, button2;

    private boolean b1 = true;
    private String Umdrehung="";
    private String Ansauglufttemperatur="";
    private String Temperatur="";
    private boolean b2 = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        mTextView1 = (TextView) findViewById(R.id.textView2);
        mTextView2 = (TextView) findViewById(R.id. Ansauglufttemp);
        mTextView3 = (TextView) findViewById(R.id.temp);
        button = (ImageButton) findViewById(R.id.imageButton);
        button2 = (ImageButton) findViewById(R.id.imageButton2);
        connection = (TextView) findViewById(R.id.Connection);
        TextView text;
        connection.setText("Please Connect");


        BTManager.addBTMsgClient(this);
        button.setOnClickListener(
                new ImageButton.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        b1 = false;


                    }
                }
        );
        button2.setOnClickListener(
                new ImageButton.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        b2=false;


                    }
                }
        );

    }


    @Override
    public void receiveMessage(final String msg) {
        if (b2==false){
            mTextView1.setText("-");
            mTextView2.setText("-");
            mTextView3.setText("-");
            b2=true;
        }



        if (b1 == false) {
            if (msg.length() >= 3) {
                System.out.println("------>" + msg);

                if(Umdrehung.equals("") || Ansauglufttemperatur.equals("") || Temperatur.equals("")) {

                    System.out.println("------> in IF");

                    if (msg.startsWith("R")) {
                        mTextView1.setText(msg);
                        Umdrehung = msg.substring(1, msg.length() - 1);
                    }
                    if (msg.startsWith("A")) {

                        mTextView2.setText(msg);
                        Ansauglufttemperatur = msg.substring(1, msg.length() - 1);
                    }
                    if (msg.contains("T")) {

                        mTextView3.setText(msg);
                        Temperatur = msg.substring(1, msg.length() - 1);
                    }

                }
                if(Umdrehung != "" && Ansauglufttemperatur != "" && Temperatur != ""){



                    String f1 = "&field1=";
                    String f2 = "&field2=";
                    String f3 = "&field3=";
                    String url = "https://api.thingspeak.com/update?api_key=7SNVCPWKYOUT5Y9U" + f1 + Umdrehung + f2 + Temperatur + f3 + Ansauglufttemperatur;


                    StringRequest stringRequest = new StringRequest(Request.Method.GET,
                            url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            connection.setText("Übertragung erfolgreich");
                            b1 = true;
                            Umdrehung = "";
                            Temperatur = "";
                            Ansauglufttemperatur = "";

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            connection.setText("Übertragung fehlgeschlagen");
                            b1 = true;
                            Umdrehung = "";
                            Temperatur = "";
                            Ansauglufttemperatur = "";
                        }
                    });


                Volley.newRequestQueue(Activity2.this).add(stringRequest);
                }
            }

        }

        }


            /*
            if(msg.startsWith("R")) {

                mTextView1.setText(msg);
                mTextView2.setText("-");
                mTextView3.setText("-");
            } else if(msg.contains("L")){
                mTextView1.setText("-");
                mTextView2.setText(msg);
                mTextView3.setText("-");
            } else if(msg.contains("T")){
                mTextView3.setText(msg);
                mTextView1.setText("-");
                mTextView2.setText("-");
            } else{
                mTextView1.setText("-");
                mTextView2.setText("-");
                mTextView3.setText("-");
            }


            */


    //System.out.println("jetzt könnte an ThingSpeak senden");











    @Override
    public void receiveConnectStatus(boolean isConnected) {
    }

    @Override
    public void handleException(Exception e) {
    }
}
