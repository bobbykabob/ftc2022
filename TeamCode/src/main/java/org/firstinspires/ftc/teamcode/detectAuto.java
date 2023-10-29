package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.trajectorysequence.sequencesegment.TrajectorySegment;

@Autonomous
@Config
public class detectAuto extends LinearOpMode {
    public static double inchesBack = 10.0;
    @Override
    public void runOpMode() throws InterruptedException {
        hardware robit = new hardware(hardwareMap);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d initialPosition = new Pose2d(36, 66, Math.toRadians(270));
        drive.setPoseEstimate(initialPosition);

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

        TrajectorySequence a_sequence;
        if (robit.camera.getPosition() == camera.position.LEFT) {
            a_sequence= drive.trajectorySequenceBuilder(initialPosition)
                    .forward(32)
                    .strafeLeft(28)
                    .build();
        } else if (robit.camera.getPosition() == camera.position.MIDDLE) {
            a_sequence= drive.trajectorySequenceBuilder(initialPosition)
                    .forward(32)

                    .build();
        } else {
            a_sequence= drive.trajectorySequenceBuilder(initialPosition)
                    .forward(32 )
                    .strafeRight(28)
                    .build();
        }
        drive.followTrajectorySequenceAsync(
                a_sequence
        );
        while (opModeIsActive()) {
            robit.update();
            drive.update();
        }




    }
}
