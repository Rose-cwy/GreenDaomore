package com.bawei.greendaomore;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bawei.greendaomore.common.PlayerManager;
import com.bawei.greendaomore.widget.media.IRenderView;
import com.bawei.greendaomore.widget.media.IjkVideoView;

public class MainActivity extends AppCompatActivity implements PlayerManager.PlayerStateListener{
    private static final String TAG = MainActivity.class.getSimpleName();
    private ProgressBar mProgressBar;
    private Button start;
    private Button pause;


    private TextView total;
    private int max;
    private DownloadUtil mDownloadUtil;
    private IjkVideoView videoView;

    private PlayerManager player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        total = (TextView) findViewById(R.id.textView);
        start = (Button) findViewById(R.id.start);
        pause = (Button) findViewById(R.id.delete);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        String urlString = "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4";
        final String localPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/local";

        videoView = (IjkVideoView) findViewById(R.id.video_view);


        mDownloadUtil = new DownloadUtil(3, localPath, "adc.mp4", urlString,
                this);
        mDownloadUtil.setOnDownloadListener(new DownloadUtil.OnDownloadListener() {

            @Override
            public void downloadStart(int fileSize) {
                // TODO Auto-generated method stub
                Log.w(TAG, "fileSize::" + fileSize);
                max = fileSize;
                mProgressBar.setMax(fileSize);
            }

            @Override
            public void downloadProgress(int downloadedSize) {
                // TODO Auto-generated method stub
                Log.w(TAG, "downloadProgress::" + downloadedSize);
                mProgressBar.setProgress(downloadedSize);
                total.setText((int) downloadedSize * 100 / max + "%");

            }

            @Override
            public void downloadEnd() {
                // TODO Auto-generated method stub
                //下载完成之后播放
                videoView.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
                videoView.setVideoURI(Uri.parse(localPath+"/adc.mp4"));
                videoView.start();

                Log.w(TAG, "End");
            }
        });
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                mDownloadUtil.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                mDownloadUtil.pause();
            }
        });


    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onPlay() {

    }
}