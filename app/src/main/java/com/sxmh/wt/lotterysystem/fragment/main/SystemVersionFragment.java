package com.sxmh.wt.lotterysystem.fragment.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.base.BaseFragment;
import com.sxmh.wt.lotterysystem.bean.response.VersionResponse;
import com.sxmh.wt.lotterysystem.download.DownLoadManager;
import com.sxmh.wt.lotterysystem.download.DownLoadObserver;
import com.sxmh.wt.lotterysystem.download.DownloadInfo;
import com.sxmh.wt.lotterysystem.util.Constants;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.ToastUtil;
import com.sxmh.wt.lotterysystem.util.VersionUtil;

import java.io.File;
import java.io.FilenameFilter;

import butterknife.InjectView;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

/**
 * Created by Wang Tao on 2018/4/9 0009.
 */

public class SystemVersionFragment extends BaseFragment {
    @InjectView(R.id.tv_version_code)
    TextView tvVersionCode;
    @InjectView(R.id.bt_check_version)
    Button btCheckVersion;
    @InjectView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_system_version;
    }

    @Override
    protected void initData() {
        tvVersionCode.setText(String.valueOf(VersionUtil.getVersionName()));
        progressBar.setMax(100);
    }

    @Override
    public void updateContent(int request, Object content) {
        if (request == Net.REQUEST_CHECK_VERSION) {
            VersionResponse response = (VersionResponse) content;
            VersionResponse.UpdateInfoBean updateInfo = response.getUpdateInfo();
            if (updateInfo != null) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setTitle("有新版本:" + updateInfo.getVersion() + "，要下载吗？")
                        .setPositiveButton("下载", (DialogInterface dialog, int which) -> {
                            progressBar.setVisibility(View.VISIBLE);
                            downloadApk(updateInfo.getUrl());
                        }).setNegativeButton("取消", null)
                        .create();
                alertDialog.show();
            }
        }
    }

    private void downloadApk(String url) {
        DownLoadManager.getInstance().download(url, new DownLoadObserver() {
            @Override
            public void onNext(DownloadInfo value) {
                super.onNext(value);
                float current = value.getProgress() / 1024 / 1024;
                float max = value.getTotal() / 1024 / 1024;
                Log.e(TAG, " ------>  " + current + "/" + max + "M");
                progressBar.setProgress((int) (current / max * 100));
            }

            @Override
            public void onComplete() {
                ToastUtil.newToast(getContext(), "下载完成");
                File file = new File(Constants.APP_PATH);
                File[] listFiles = file.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.contains(".apk");
                    }
                });
                install(listFiles[0].getPath());
            }
        });

    }

    private void install(String filePath) {
        File apkFile = new File(filePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.w(TAG, "版本大于 N ，开始使用 fileProvider 进行安装");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(
                    getContext()
                    , "com.sxmh.wt.lotterysystem.fileprovider"
                    , apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            Log.w(TAG, "正常进行安装");
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        startActivity(intent);
    }

    @OnClick(R.id.bt_check_version)
    public void onViewClicked() {
        net.queryupdate(getContext().getPackageName(), VersionUtil.getVersionName());
    }
}
