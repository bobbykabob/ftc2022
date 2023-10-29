package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class lifttest2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        hardware robit = new hardware(hardwareMap);
        robit.lift.resetMotorPos();
        waitForStart();
        while (opModeIsActive()) {

            robit.lift.setMotorPower(0.75 * (gamepad1.left_trigger - gamepad1.right_trigger));
            telemetry.addData("pos", robit.lift.getMotorPosition());
            telemetry.update();
        }
    }
}
