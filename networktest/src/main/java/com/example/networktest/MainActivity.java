package com.example.networktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView responseText;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendRequest = (Button) findViewById(R.id.send_request);
        Button sendHttpUtil = (Button) findViewById(R.id.send_httputil);
        Button sendOkHttp = (Button) findViewById(R.id.send_okhttp);
        responseText = (TextView) findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
        sendHttpUtil.setOnClickListener(this);
        sendOkHttp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_request){
//            sendRepuestWithHttpURLConnection();
            sendRequestWithOkHttp();
        }

        if (v.getId() == R.id.send_httputil){
            HttpUtil.sendHttpRequest("https://www.baidu.com/", new HttpCallbackListener() {
                @Override
                public void onFinish(String reponse) {
                    showResponse(reponse);
                }

                @Override
                public void onError(Exception e) {
                    //在这里对异常情况进行处理
                }
            });
        }

        if (v.getId() == R.id.send_okhttp){
            HttpUtil.sendOkHttpRequest("https://www.baidu.com", new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //在这里对异常情况进行处理
                    Log.d(TAG, "onFailure: ------------------------------");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

//                    showResponse(response.body().string().toString());
                    Log.d(TAG, "onResponse: "+response);
                    Log.d(TAG, "onResponse: "+response.body());
                    Log.d(TAG, "onResponse: "+response.body().string());
                    Log.d(TAG, "onResponse: "+response.body().toString());
                    Log.d(TAG, "onResponse: "+response.toString());
//                    Log.d(TAG, "onResponse: "+response.body().string().toString());
                }
            });
        }

    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //发起一条 POST 请求
//                RequestBody requestBody = new FormBody.Builder()
//                        .add("username", "admin")
//                        .add("paddword", "123456")
//                        .build();
//                Request request = new Request.Builder()
//                        .url("http://www.baidu.com")
//                        .post(requestBody)
//                        .build();
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
//                            .url("http://www.baidu.com")
//                            .url("http://172.20.10.9/apps")
                            //http://172.20.10.9/get_data.json 这个地址是这台mac的，sony Lt26ii 和 这台mac 同时连接 5s手机的热点。通过手机访问这笔记本的/Sites 也就是这台笔记本Apache服务器的根目录里的东西
                            .url("http://172.20.10.9/get_data.json")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
//                    showResponse(responseData);
//                    parseXMLWithPull(responseData);
//                    parseXMLWithSax(responseData);
//                    parseJSONWithJSONObject(responseData);
                    parseJSONWithGson(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGson(String jsonData) {

        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>() {
        }.getType());
        for (App app : appList) {
//            Log.d(TAG, "id -------- " + app.getId());
            Log.d(TAG, "name --------------- " + app.getName());
            Log.d(TAG, "version --------------------------- " + app.getVersion());
            Log.d(TAG, "app  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + app.toString());
        }

    }

    private void parseJSONWithJSONObject(String jsonData) {

        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.d(TAG, "id --------- " + id);
                Log.d(TAG, "name -------------- " + name);
                Log.d(TAG, "version ------------------------------ " + version);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void parseXMLWithSax(String xmlData) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            Myhandler handler = new Myhandler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void parseXMLWithPull(String xmlData) {

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            String id = "";
            String name = "";
            String version = "";
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT){
                String nodeName = xmlPullParser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG :
                        if ("id".equals(nodeName)){
                            id = xmlPullParser.nextText();
                        }else if ("name".equals(nodeName)){
                            name = xmlPullParser.nextText();
                        }else if ("version".equals(nodeName)){
                            version = xmlPullParser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG :
                        if ("app".equals(nodeName)){
                            Log.d(TAG, "id -------" + id);
                            Log.d(TAG, "name -----" + name);
                            Log.d(TAG, "version --" + version);
                        }
                        break;
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void sendRepuestWithHttpURLConnection() {
        new Thread(new Runnable() {

            private HttpURLConnection connection;
            private BufferedReader reader;

            @Override
            public void run() {
                try {
                    URL url = new URL("http://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

//                    //提交数据给服务器
//                    connection.setRequestMethod("POST");
//                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//                    out.writeBytes("username=admin&password=123456");

                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    showResponse(response.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (reader != null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(response);
            }
        });
    }
}
