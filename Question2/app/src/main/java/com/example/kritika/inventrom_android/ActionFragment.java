package com.example.kritika.inventrom_android;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.System.out;

/**
 * A simple {@link Fragment} subclass.

 */
public class ActionFragment extends Fragment  {

    private ImageSurfaceView mImageSurfaceView;
    private Camera camera;

    private FrameLayout cameraPreviewLayout;
    private ImageView capturedImageHolder;
    Button Capture;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_action, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);

        cameraPreviewLayout = (FrameLayout)view.findViewById(R.id.camera_preview);
        capturedImageHolder = (ImageView)view.findViewById(R.id.captured_image);

        camera = checkDeviceCamera();
        mImageSurfaceView = new ImageSurfaceView(getActivity(), camera);
        cameraPreviewLayout.addView(mImageSurfaceView);


        Capture = (Button)view.findViewById(R.id.button);

        Capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Please wait ...", Toast.LENGTH_LONG).show();
                camera.takePicture(null, null, pictureCallback);

            }
        });
        return view;

    }
    private Camera checkDeviceCamera(){
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mCamera;
    }
    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            if(bitmap==null){
                Toast.makeText(getActivity(), "Capture image is empty", Toast.LENGTH_LONG).show();
                return;
            }
            else{

                capturedImageHolder.setImageBitmap(scaleDownBitmapImage(bitmap, 300, 200 ));
                try {
                    String dir = saveToInternalStorage(data,bitmap);
                    Toast.makeText(getActivity(),dir, Toast.LENGTH_LONG).show();
                    Intent i=new Intent(getActivity(),FormActivity.class);
                    startActivity(i);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }


        }
    };

    private Bitmap scaleDownBitmapImage(Bitmap bitmap, int newWidth, int newHeight){
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
        return resizedBitmap;
    }

    private String saveToInternalStorage(byte[] data,Bitmap bitmapImage) throws IOException {

        // Create an image file

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "InventromJPEG_" + timeStamp + "_"+".JPEG";

        Toast.makeText(getActivity(),imageFileName, Toast.LENGTH_LONG).show();

        ContextWrapper cw = new ContextWrapper(getActivity());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("InventromimageDir", Context.MODE_PRIVATE);
        // Create imageDir

        if (!directory.exists())
            directory.mkdirs();

        File mypath=new File(directory,imageFileName);


      //  File mypath = File.createTempFile(
        //        imageFileName,  /* prefix */
          //       ".jpg",         /* suffix */
            //     directory      /* directory */
              //    );

                FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.write(data);
            Toast.makeText(getActivity(), "Picture saved in internal storage", Toast.LENGTH_LONG).show();
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Parse the gallery image url to uri
       // Uri savedImageURI = Uri.parse(mypath.getAbsolutePath());

        // Display the saved image to ImageView
        //capturedImageHolder.setImageURI(savedImageURI);

        // Display saved image uri to TextView
        //tv_saved.setText("Image saved in internal storage.\n" + savedImageURI);
        return directory.getAbsolutePath();
    }



}