package com.example.networktest;

/**
 * Created by john on 2018/1/10.
 */

public interface HttpCallbackListener {

    void onFinish(String reponse);

    void onError(Exception e);

}
