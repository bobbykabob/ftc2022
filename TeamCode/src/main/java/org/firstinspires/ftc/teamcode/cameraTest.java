package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class cameraTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {


        hardware robit = new hardware(hardwareMap);


        waitForStart();
        while (opModeIsActive()) {
            robit.update();
            telemetry.addData("positions:", robit.camera.getPosition());
            telemetry.update();
        }
    }
}