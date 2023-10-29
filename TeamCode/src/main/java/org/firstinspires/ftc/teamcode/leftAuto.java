package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
@Config
public class leftAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        hardware robit = new hardware(hardwareMap);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d initialLeftPosition = new Pose2d(36, 66, Math.toRadians(270));
        Vector2d vec0Left = new Vector2d(56, 12);
        Vector2d vec1Left = new Vector2d(36, 12);
        Vector2d vec2Left = new Vector2d(12, 12);
        double position = 0;
        drive.setPoseEstimate(initialLeftPosition);
        robit.lift.setServoPosition(1);
        while (!isStarted()) {
            if (gamepad1.left_stick_button) {
                robit.lift.setServoPosition(0);
            } else if (gamepad1.right_stick_button) {
                robit.lift.setServoPosition(1);
            }
            telemetry.addData("positions:", robit.camera.getPosition());
            telemetry.update();
            robit.update();
        }


        TrajectorySequence b_sequence = drive.trajectorySequenceBuilder(initialLeftPosition)
                .setReversed(false)
                .splineTo(new Vector2d(36, 24), Math.toRadians(270))
                .splineTo(new Vector2d(26, 3), Math.toRadians(225))
                .setReversed(true)
                .splineTo(new Vector2d(36, 24), Math.toRadians(90))
                .setReversed(false)
                .splineTo(new Vector2d(48, 12), Math.toRadians(0))
                .splineTo(new Vector2d(64, 12), Math.toRadians(0))
                .build();

        drive.followTrajectorySequenceAsync(
                b_sequence
        );
        while (opModeIsActive() && drive.isBusy()) {
            robit.update();
            drive.update();
        }


        if (robit.camera.getPosition() == camera.position.LEFT) {
            position = 0;
        } else if (robit.camera.getPosition() == camera.position.MIDDLE) {
            position = 1;
        } else {
            position = 2;
        }
        drive.followTrajectorySequenceAsync(
                b_sequence
        );
        while (opModeIsActive() && drive.isBusy()) {
            robit.update();
            robit.lift.update();
            drive.update();
        }


        TrajectorySequence final_sequence = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .setReversed(true)
                .splineTo(position == 0? vec0Left: position == 1? vec1Left: vec2Left, Math.toRadians(180))
                .build();


        drive.followTrajectorySequenceAsync(
                final_sequence
        );
        while (opModeIsActive() && drive.isBusy()) {
            robit.update();
            robit.lift.update();
            drive.update();
        }


    }
}
