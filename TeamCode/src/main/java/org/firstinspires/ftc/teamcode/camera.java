package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import org.firstinspires.ftc.teamcode.pipelines.AprilTagDetectionPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;


public class camera {
    public OpenCvCamera webcam;
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;
    HardwareMap hw;
    public AprilTagDetectionPipeline aprilTagDetectionPipeline = null;

    public enum position {
        LEFT,
        MIDDLE,
        RIGHT,
        OTHER
    }
    public camera(HardwareMap hw) {
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize,fx,fy,cx,cy);

        this.hw = hw;
        int cameraMonitorViewId = hw.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hw.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hw.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        webcam.setPipeline(aprilTagDetectionPipeline);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
                    @Override
                    public void onOpened() {
                        webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
                    }

                    @Override
                    public void onError(int errorCode) {

                    }

                });

            }

            @Override
            public void onError(int errorCode) {

            }
        });

        FtcDashboard.getInstance().startCameraStream(webcam, 0);

    }



    public position getPosition() {
        ArrayList<Integer> ids = aprilTagDetectionPipeline.getLastestID();
        if (ids.size() != 1)
        {
            return position.OTHER;
        }
        switch (ids.get(0)) {
            case 1:
                return position.LEFT;
            case 2:
                return position.MIDDLE;
            case 3:
                return position.RIGHT;
            default:
                return position.OTHER;
        }
    }


}