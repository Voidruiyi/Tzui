package com.ry.tzui;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ry.tzui.img.GlideUtil;
import com.ry.tzui.net.module.ServiceMoudle;
import com.ry.tzui.net.service.IServiceApi;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView = findViewById(R.id.img);
        findViewById(R.id.test).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        String url = "http://simtest.mobile.taikang.com/udfile/download?serverName=qs&videoTime=2&n=Video_1590721206730.mp4&s=141759&filePath=F6E8B1AEA3A2A8E8F5F7F5F7E8F7F2E8F5FEE8F6F2FEF7F0F5F6F5F4F7F1F4F3E7ABB2A8ADB0F6F7E791AEA3A2A898F6F2FEF7F0F5F6F5F7F1F0F4F7E9AAB7F3.mp4";
        IServiceApi api = ServiceMoudle.getApi();


        api.downloadFile(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
//                        File file = new File(getDir("download"), "test");
//                        writeCache(responseBody, file);
                        if (responseBody != null) {
                            BufferedSource source = responseBody.source();
                            BufferedSink sink = null;

                            try {

                                File file = new File(getDir("download"), "test.mp4");
                                sink = Okio.buffer(Okio.sink(file));
                                byte[] buf = new byte[1024];
                                boolean var8 = true;

                                int len;
                                while ((len = source.read(buf)) != -1) {
                                    sink.write(buf, 0, len);
                                }

                                Log.e("eee", "success");


                            } catch (FileNotFoundException var14) {
                            } catch (IOException var15) {
                            } catch (Exception var16) {
                            } finally {
                                closess(source);
                                closess(sink);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("eee", "ss="+throwable.getMessage());
                    }
                });
    }


    public File getDir(String path) {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }

        String dir = Environment.getExternalStorageDirectory() + "/taikang_tkim" + path;
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }

        return file;
    }

    public void closess(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException var2) {
                var2.printStackTrace();
            }

        }
    }

    public static void writeCache(ResponseBody responseBody, File file) throws IOException {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        long allLength = responseBody.contentLength();


        FileChannel channelOut = null;
        RandomAccessFile randomAccessFile = null;
        randomAccessFile = new RandomAccessFile(file, "rwd");
        channelOut = randomAccessFile.getChannel();
        MappedByteBuffer mappedBuffer = channelOut.map(FileChannel.MapMode.READ_WRITE, 0, allLength);
        byte[] buffer = new byte[1024 * 4];
        int len;
        int record = 0;
        while ((len = responseBody.byteStream().read(buffer)) != -1) {
            mappedBuffer.put(buffer, 0, len);
            record += len;
        }
        responseBody.byteStream().close();
        if (channelOut != null) {
            channelOut.close();
        }
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
    }

}
