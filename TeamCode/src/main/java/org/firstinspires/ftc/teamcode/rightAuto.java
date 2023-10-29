package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.opencv.core.Mat;

@Autonomous
@Config
public class rightAuto extends LinearOpMode {
    public static Pose2d initialRightPosition = new Pose2d(-32, 70, Math.toRadians(270));
    public static Pose2d vec0Right = new Pose2d(-6, 6, Math.toRadians(0));
    public static Pose2d vec1Right = new Pose2d(-34, 6, Math.toRadians(0));
    public static Pose2d vec2Right = new Pose2d(-64, 6, Math.toRadians(0));

    @Override
    public void runOpMode() throws InterruptedException {
        hardware robit = new hardware(hardwareMap);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        double position = 0;
        drive.setPoseEstimate(initialRightPosition);

        robit.lift.setServoPosition(1);
        TrajectorySequence b_sequence =  drive.trajectorySequenceBuilder(initialRightPosition)
                .setReversed(false)


                .lineToLinearHeading(new Pose2d(-32, 30, Math.toRadians(315)))
                .UNSTABLE_addTemporalMarkerOffset(1, ()-> {
                    robit.lift.setTargetLiftPos(lift.liftPos.UP);
                })
                .lineToLinearHeading(new Pose2d(-30, 0, Math.toRadians(315)))

                .UNSTABLE_addTemporalMarkerOffset(1, ()-> {
                    robit.lift.setTargetLiftPos(lift.liftPos.stack5);

                })
                .UNSTABLE_addTemporalMarkerOffset(1.5, ()-> {
                    robit.lift.setServoPosition(0);
                })
                .waitSeconds(3)
                .setReversed(true)
                .lineToLinearHeading(new Pose2d(-48, 4, Math.toRadians(180)))
                .setReversed(false)
                .splineTo(new Vector2d(-60, 4), Math.toRadians(180))
                .UNSTABLE_addTemporalMarkerOffset(0, ()-> {
                    robit.lift.setServoPosition(1);
                })
                .UNSTABLE_addTemporalMarkerOffset(1, ()-> {
                    robit.lift.setTargetLiftPos(lift.liftPos.LOW);
                })
                .waitSeconds(1.5)

                .setReversed(true)

                .UNSTABLE_addTemporalMarkerOffset(1, ()-> {
                    robit.lift.setTargetLiftPos(lift.liftPos.UP);
                })

                .lineToLinearHeading(new Pose2d(-27, -2, Math.toRadians(315)))
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(0, ()-> {
                    robit.lift.setTargetLiftPos(lift.liftPos.stack4);

                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, ()-> {
                    robit.lift.setServoPosition(0);
                })

                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(0, ()-> {
                    robit.lift.setTargetLiftPos(lift.liftPos.stack3);

                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, ()-> {
                    robit.lift.setServoPosition(0);
                })
                .waitSeconds(1)
                .setReversed(true)
                .lineToLinearHeading(new Pose2d(-48, 3, Math.toRadians(180)))
                .setReversed(false)
                .splineTo(new Vector2d(-60, 3), Math.toRadians(180))
                .UNSTABLE_addTemporalMarkerOffset(0, ()-> {
                    robit.lift.setServoPosition(1);
                })
                .UNSTABLE_addTemporalMarkerOffset(1, ()-> {
                    robit.lift.setTargetLiftPos(lift.liftPos.LOW);
                })
                .waitSeconds(1.5)
                .setReversed(true)

                .UNSTABLE_addTemporalMarkerOffset(1, ()-> {
                    robit.lift.setTargetLiftPos(lift.liftPos.UP);
                })

                .lineToLinearHeading(new Pose2d(-25, -3, Math.toRadians(315)))
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(0, ()-> {
                    robit.lift.setTargetLiftPos(lift.liftPos.stack2);

                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, ()-> {
                    robit.lift.setServoPosition(0);
                    robit.lift.setTargetLiftPos(lift.liftPos.BOTTOM);
                })
                .build();


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

        //robit.lift.resetMotorPos();
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

        TrajectorySequence final_sequence;

        if (position == 0) {
            final_sequence = drive.trajectorySequenceBuilder(b_sequence.end())
                    .setReversed(true)
                    .lineToLinearHeading(vec0Right)
                    .build();
        } else if (position == 1) {
            final_sequence = drive.trajectorySequenceBuilder(b_sequence.end())
                    .setReversed(true)
                    .lineToLinearHeading(vec1Right)
                    .build();
        } else {
            final_sequence = drive.trajectorySequenceBuilder(b_sequence.end())
                    .setReversed(true)
                    .lineToLinearHeading(vec2Right)
                    .build();
        }


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
