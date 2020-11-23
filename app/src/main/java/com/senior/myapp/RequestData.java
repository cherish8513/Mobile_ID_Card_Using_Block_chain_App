package com.senior.myapp;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import org.json.JSONObject;

public class RequestData {

    public RequestQueue queue;
    public int requestType;
    public String requestUrl;
    public JSONObject requestParams;

    public RequestData(RequestQueue queue, int requestType, String requestUrl,JSONObject requestParams){
        this.queue = queue;
        this.requestParams = requestParams;
        this.requestType = requestType;
        this.requestUrl = requestUrl;
    }

    public static class Builder {
        private RequestQueue queue;
        private int requestType;
        private String requestUrl;
        private JSONObject requestParams;

        public Builder(){ }

        public Builder setQueue(RequestQueue queue) {
            this.queue = queue;
            return this;
        }

        public Builder setRequestType(int requestType){
            this.requestType = requestType;
            return this;
        }

        public Builder setRequestUrl(String requestUrl){
            this.requestUrl = requestUrl;
            return this;
        }

        public Builder setRequestParams(JSONObject requestParams){
            this.requestParams = requestParams;
            return this;
        }

        public RequestData build() {
            if (queue == null) {
                throw new IllegalStateException("RequestQueue is null");
            }
            if (requestType < 0) {
                throw new IllegalStateException("RequestType is null");
            }
            if (requestUrl == null) {
                throw new IllegalStateException("RequestUrl is null");
            }
            return new RequestData(queue, requestType, requestUrl, requestParams);
        }
    }
}
