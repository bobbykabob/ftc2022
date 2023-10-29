package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class hardware {

    public lift lift;
    public camera camera;
    private HardwareMap hw;

    public hardware(HardwareMap ahw) {
        hw = ahw;
        camera = new camera(hw);
        lift = new lift(hw);

    }


    public void update() {

    }

}