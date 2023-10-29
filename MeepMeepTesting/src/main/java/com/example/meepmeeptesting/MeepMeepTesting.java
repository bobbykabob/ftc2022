package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        Pose2d initialLeftPosition = new Pose2d(36, 66, Math.toRadians(270));
        Pose2d initialRightPosition = new Pose2d(-36, 66, Math.toRadians(270));

        double position = 2;
        Vector2d vec0Left = new Vector2d(56, 12);
        Vector2d vec1Left = new Vector2d(36, 12);
        Vector2d vec2Left = new Vector2d(12, 12);
        Vector2d vec0Right = new Vector2d(-12, 12);
        Vector2d vec1Right = new Vector2d(-36, 12);
        Vector2d vec2Right = new Vector2d(-56, 12);
        RoadRunnerBotEntity leftBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(0), 15)
                .setDimensions(12, 12)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(initialLeftPosition)
                                .setReversed(false)
                                .splineTo(new Vector2d(36, 24), Math.toRadians(270))
                                .splineTo(new Vector2d(26, 3), Math.toRadians(225))
                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(60, 18, Math.toRadians(-45)))
                                .setReversed(false)
                                .lineToLinearHeading(new Pose2d(64, 12, Math.toRadians(-45)))
                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(60, 18, Math.toRadians(-45)))
                                .turn(Math.toRadians(180))
                                .forward(4)
                                .forward(-4)
                                .turn(Math.toRadians(180))
                                //.splineTo(position == 0? vec0Left: position == 1? vec1Left: vec2Left, Math.toRadians(180))
                                .build()

                );
        RoadRunnerBotEntity rightBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(0), 15)
                .setDimensions(12, 12)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(initialRightPosition)
                                .setReversed(false)
                                .splineTo(new Vector2d(-36, 24), Math.toRadians(270))
                                .splineTo(new Vector2d(-25, 2), Math.toRadians(315))
                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(-48, 12, Math.toRadians(180)))
                                .setReversed(false)
                                .splineTo(new Vector2d(-64, 12), Math.toRadians(180))
                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(-25, 2, Math.toRadians(315)))
                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(-48, 12, Math.toRadians(180)))
                                .setReversed(false)
                                .splineTo(new Vector2d(-64, 12), Math.toRadians(180))
                                .setReversed(true)

                                .lineToLinearHeading(new Pose2d(-25, 2, Math.toRadians(315)))
                                //.splineTo(position == 0? vec0Right: position == 1? vec1Right: vec2Right, Math.toRadians(0))
                                .build()
                );
        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(leftBot)
                .addEntity(rightBot)
                .start();
    }
}