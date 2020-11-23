package com.senior.myapp;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

public class ToServer {
    static RequestQueue requestQueue;

    private String filePath = null;
    private String textData = null;
    private String fileName = null;
    private Context mContext = null;
    private Handler handler;

    public void setData(String filePath, String fileName, String textData, Context mContext, Handler handler) {
        this.filePath = filePath;
        this.textData = textData;
        this.fileName = fileName;
        this.mContext = mContext;
        this.handler = handler;
    }

    public void sendPhotoToServer() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Map<String, String> params = new HashMap();
                params.put("imgName", fileName);
                params.put("imgUrl", filePath);

                JSONObject parameters = new JSONObject(params);

                if (filePath == null || textData == null) {
                    Log.println(Log.INFO, "Fail to send", "file path or text data is null");
                    return;
                } else {
                    if (requestQueue == null)
                        requestQueue = Volley.newRequestQueue(mContext);
                    //String url = "http://ec2-13-209-39-224.ap-northeast-2.compute.amazonaws.com:8080/api/idCard/save";
                    String url = "http://192.168.104.129:8080/api/idCard/save/photo";
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.println(Log.INFO, "응답 => ", "성공");
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    request.setShouldCache(false);
                    requestQueue.add(request);
                    Log.println(Log.INFO, "정보 => ", "request set");
                }
            }
        };
        handler.postDelayed(runnable, 500);
    }

    public void sendTextToServer() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Map<String, String> params = new HashMap();
                params.put("textData", textData);

                JSONObject parameters = new JSONObject(params);

                if (filePath == null || textData == null) {
                    Log.println(Log.INFO, "Fail to send", "file path or text data is null");
                    return;
                } else {
                    if (requestQueue == null)
                        requestQueue = Volley.newRequestQueue(mContext);
                    //String url = "http://ec2-13-209-39-224.ap-northeast-2.compute.amazonaws.com:8080/api/idCard/save";
                    String url = "http://192.168.104.129:8080/api/idCard/save/text";
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.println(Log.INFO, "응답 => ", "성공");
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    request.setShouldCache(false);
                    requestQueue.add(request);
                    Log.println(Log.INFO, "정보 => ", "request set");
                }
            }
        };
        handler.postDelayed(runnable,2500);
    }

    public void severToChain() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (filePath == null || textData == null) {
                    Log.println(Log.INFO, "Fail to send", "file path or text data is null");
                    return;
                } else {
                    if (requestQueue == null)
                        requestQueue = Volley.newRequestQueue(mContext);
                    //String url = "http://ec2-13-209-39-224.ap-northeast-2.compute.amazonaws.com:8080/api/idCard/save";
                    String url = "http://192.168.104.129:8080/api/idCard/userBlock";
                    StringRequest request = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("result", "[" + response + "]");
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d("error", "[" + error.getMessage() + "]");
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            return null;
                        }
                    };
                    request.setShouldCache(false);
                    requestQueue.add(request);
                    Log.println(Log.INFO, "정보 => ", "request set");
                }
            }
        };
        handler.postDelayed(runnable,4500);
    }
}
