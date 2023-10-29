package org.firstinspires.ftc.teamcode.autos;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware;


@Autonomous
public class parkAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        hardware robit = new hardware(hardwareMap);
        Pose2d startPose = new Pose2d(-36, 66, Math.toRadians(90));
        Pose2d hubPose = new Pose2d(-35, 22, Math.toRadians(0));
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);


        drive.setPoseEstimate(startPose);

        while (!isStarted()) {
            telemetry.addData("pos", robit.camera.getPosition());
            telemetry.update();
        }




        while (opModeIsActive()) {
            telemetry.addLine("running");
            telemetry.update();
        }

    }
}