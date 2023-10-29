package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class lifttest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        hardware robit = new hardware(hardwareMap);
        robit.lift.resetMotorPos();
        waitForStart();
        while (opModeIsActive()) {

            if (gamepad1.a) {
                robit.lift.setTargetLiftPos(lift.liftPos.BOTTOM);
            } else if (gamepad1.b) {
                robit.lift.setTargetLiftPos(lift.liftPos.LOW);
            } else if (gamepad1.x) {
                robit.lift.setTargetLiftPos(lift.liftPos.MID);
            } else if (gamepad1.y) {
                robit.lift.setTargetLiftPos(lift.liftPos.UP);
            } else if (gamepad1.dpad_down) {
                robit.lift.setTargetLiftPos(lift.liftPos.stack1);
            } else if (gamepad1.dpad_left) {
                robit.lift.setTargetLiftPos(lift.liftPos.stack2);
            } else if (gamepad1.dpad_right) {
                robit.lift.setTargetLiftPos(lift.liftPos.stack3);
            } else if (gamepad1.dpad_up) {
                robit.lift.setTargetLiftPos(lift.liftPos.stack4);
            } else if (gamepad1.left_stick_button) {
                robit.lift.setTargetLiftPos(lift.liftPos.stack5);
            }

            telemetry.addData("pos", robit.lift.getMotorPosition());
            telemetry.update();
            robit.lift.update();
        }
    }
}
