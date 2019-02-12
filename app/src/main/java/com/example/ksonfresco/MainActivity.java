package com.example.ksonfresco;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button one,two,san,si,wu,six,qi,ba;
    SimpleDraweeView simpledraweeview;
    private Uri uri;
    int width=1000;
    int height=699;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        uri = Uri.parse("http://pic1.win4000.com/mobile/2018-11-15/5bed347fc089f.jpg");
    }

    private void initview() {
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_san).setOnClickListener(this);
        findViewById(R.id.btn_si).setOnClickListener(this);
        findViewById(R.id.btn_wu).setOnClickListener(this);
        findViewById(R.id.btn_six).setOnClickListener(this);
        findViewById(R.id.btn_qi).setOnClickListener(this);
        findViewById(R.id.btn_ba).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_one:
                GenericDraweeHierarchy yjhierarchy=GenericDraweeHierarchyBuilder.newInstance(getResources())
                        .setRoundingParams(RoundingParams.fromCornersRadius(20))
                        .build();
                simpledraweeview.setHierarchy(yjhierarchy);
                DraweeController controller= Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .build();
                simpledraweeview.setController(controller);
                break;
            case R.id.btn_two:
                RoundingParams roundingParams = new RoundingParams();
                roundingParams.setRoundAsCircle(true);
                GenericDraweeHierarchy hierarchy=GenericDraweeHierarchyBuilder.newInstance(getResources())
                        .setRoundingParams(roundingParams)
                        .build();
                simpledraweeview.setHierarchy(hierarchy);
                DraweeController yjcontroller= Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .build();
                simpledraweeview.setController(yjcontroller);

                break;
            case R.id.btn_san:
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setResizeOptions(new ResizeOptions(width, height))
                        .build();
                PipelineDraweeController bilicontroller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                        .setOldController(simpledraweeview.getController())
                        .setImageRequest(request)
                        .build();
                simpledraweeview.setController(bilicontroller);


                break;
            case R.id.btn_si:
                ImageRequest jianjinrequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse("http://pooyak.com/p/progjpeg/jpegload.cgi"))
                        .setProgressiveRenderingEnabled(true) //设置支持渐进式JPEG
                        .build();
                DraweeController progressiveJPEGController = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(jianjinrequest)
                        .setOldController(simpledraweeview.getController())
                        .build();
                simpledraweeview.setController(progressiveJPEGController);
                break;
            case R.id.btn_wu:

                break;
            case R.id.btn_six:
                ControllerListener controllerListener = new BaseControllerListener() {
                    @Override
                    public void onFinalImageSet(String id, Object imageInfo, Animatable animatable) {
                        if (animatable != null) {
                            animatable.start();
                        }
                    }

                    @Override
                    public void onFailure(String id, Throwable throwable) {
                        Toast.makeText(MainActivity.this, "图片加载失败", Toast.LENGTH_SHORT).show();
                    }
                };
                DraweeController gifController = Fresco.newDraweeControllerBuilder()
                        .setUri(Uri.parse("http://img01.sogoucdn.com/app/a/100520021/c56315c4951cea9ec4ab8643facc658a"))
                        .setOldController(simpledraweeview.getController())
                        .setControllerListener(controllerListener)
                        .build();
                simpledraweeview.setController(gifController);

                break;
            case R.id.btn_qi:
                break;
        }
    }
}
