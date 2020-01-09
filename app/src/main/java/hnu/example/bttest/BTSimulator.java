package hnu.example.bttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Random;

public class BTSimulator {
    /*
    int i;
*/
    public static int INTERVALL_MS = 1000; //simulate data every 1000ms
   /* protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        Button button = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.textView);
        textdreh = (TextView) findViewById(R.id.textdreh);
        texttemp = (TextView) findViewById(R.id.texttemp);
        textlamb = (TextView) findViewById(R.id.textlamb);

        drehzahl = (EditText) findViewById(R.id.typeDrehzahl);
        temperatur = (EditText) findViewById(R.id.typeTemperatur);
        lambda = (EditText) findViewById(R.id.typeLambda);

        int i;
        i=0;
        button.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){

                        while (i == 0){
                            processData();
                        }
                        int i=0;
                    }
                }
        );
        button2.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        int i=1;
                        stopTransmission();


                    }
                }
        );


    }


    private void stopTransmission() {

    }

    private void processData() {
        String DrehzahlValue = drehzahl.getText().toString();
        String TemperaturValue = temperatur.getText().toString();
        String LambdaValue = lambda.getText().toString();
        String f1 = "&field1=";
        String f2 = "&field2=";
        String f3 = "&field3=";


        if(DrehzahlValue.equals("") || TemperaturValue.equals("") ||
                LambdaValue.equals("")){
            text.setText("");
            if(DrehzahlValue.equals("")){
                textdreh.setText("Hier bitte Wert einfügen");
            }else{
                textdreh.setText("");
            }
            if(TemperaturValue.equals("")){
                texttemp.setText("Hier bitte Wert einfügen");
            }else{
                texttemp.setText("");
            }
            if(LambdaValue.equals("")){
                textlamb.setText("Hier bitte Wert einfügen");
            }else{
                textlamb.setText("");
            }


        } else {

            String url = "https://api.thingspeak.com/update?api_key=7SNVCPWKYOUT5Y9U"
                    +f1+DrehzahlValue+f2+TemperaturValue+f3+LambdaValue;
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    text.setText("Übertragung erfolgreich");
                    textlamb.setText("");
                    texttemp.setText("");
                    textdreh.setText("");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    text.setText("Übertragung fehlgeschlagen");
                }
            });

            Volley.newRequestQueue(BTSimulator.this).add(stringRequest);
        }

    }


    */

    public static String simulateValue() {
        Random rand = new Random();

        String prefix;
        String postfix = ";";





        if (rand.nextBoolean()==true) {
            int value = rand.nextInt(7000); // Obtain a number between [0 - 6999].
            prefix= "R";
            String aErgebnis = prefix + value + postfix;
            return aErgebnis;
        } else if(rand.nextBoolean()==true){
            double value = rand.nextInt(30); // Obtain a number between [0 - 29].
            prefix= "A";
            value = value/10;
            String bErgebnis = prefix + value + postfix;
            return bErgebnis;
        }else{
            int value = rand.nextInt(180); // Obtain a number between [0 - 179].
            prefix= "T";
            String cErgebnis = prefix + value + postfix;
            return cErgebnis;
        }






    }

}
