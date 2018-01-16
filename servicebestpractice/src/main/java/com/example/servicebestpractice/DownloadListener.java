package com.example.servicebestpractice;

/**
 * Created by john on 2018/1/10.
 */

public interface DownloadListener {

    void onProgess(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();

}
