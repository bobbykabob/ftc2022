package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

/**
 * This is a simple teleop routine for testing localization. Drive the robot around like a normal
 * teleop routine and make sure the robot's estimated pose matches the robot's actual pose (slight
 * errors are not out of the ordinary, especially with sudden drive motions). The goal of this
 * exercise is to ascertain whether the localizer has been configured properly (note: the pure
 * encoder localizer heading may be significantly off if the track width has not been tuned).
 */
@TeleOp(group = "drive")
public class teleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        hardware robit = new hardware(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double speed = 1.0;
        waitForStart();

        while (!isStopRequested()) {

            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y * speed,
                            -gamepad1.left_stick_x * speed,
                            -gamepad1.right_stick_x * speed
                    )
            );

            drive.update();
            if (gamepad1.left_bumper == true) {
                speed = 0.3;
            } else if (gamepad1.right_bumper == true) {
                speed = 1.0;
            }
            if (gamepad1.left_stick_button) {
                robit.lift.setServoPosition(0);
            } else if (gamepad1.right_stick_button) {
                robit.lift.setServoPosition(1);
            }
            if (gamepad2.left_stick_button) {
                robit.lift.setServoPosition(0);
            } else if (gamepad2.right_stick_button) {
                robit.lift.setServoPosition(1);
            }
            if (gamepad1.a) {
                robit.lift.setTargetLiftPos(lift.liftPos.BOTTOM);
            } else if (gamepad1.b) {
                robit.lift.setTargetLiftPos(lift.liftPos.LOW);
            } else if (gamepad1.x) {
                robit.lift.setTargetLiftPos(lift.liftPos.MID);
            } else if (gamepad1.y) {
                robit.lift.setTargetLiftPos(lift.liftPos.UP);
            }

            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.update();
            robit.lift.update();
        }
    }
}
