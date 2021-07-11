package com.example.scratches;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Working {
    Context context;

    public Working(Context context){
        this.context = context;
    }

    public void values(int room,int device,int status)
    {
        /*room-> receives room number
        * device-> receives device number
        * status ->receives status 0->off 1->on*/

        room = (int)Math.pow(2, room-1);
        room = room<<9;
        device = (int)Math.pow(2, device-1);
        device = device<<1;
        int sendData = room + device + status;
        String url = "https://api.thingspeak.com/update?api_key=HZ7S7UQH6NFKKO59&field1=" + sendData;
        new MyAsyncTask().execute(url);

//        Toast.makeText(context,"Device is turned on", Toast.LENGTH_LONG).show();

    }
    public class MyAsyncTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            try {
                String NewsData;
                //define the url we have to connect with
                URL url = new URL(params[0]);
                //make connect with url and send request
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
//                urlConnection.setConnectTimeout(7000);//set timeout to 5 seconds

                int responseCode = urlConnection.getResponseCode();
                //waiting for 7000ms for response

                urlConnection.disconnect();
            }catch (Exception ex){}
            return null;
        }
    }
}
