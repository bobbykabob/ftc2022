package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorControllerEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@Config
public class lift {
    public static double kp = 0.02;
    public static double bottom = 0;
    public static double stack1 = 0;
    public static double stack2 = 90;
    public static double stack3 = 110;
    public static double stack4 = 150;
    public static double stack5 = 170;
    public static double low = 450;
    public static double mid = 700;
    public static double up = 1000;
    public static double minP = -0.3;
    public static double minPBottom = -0.5;
    public static double minPThreshold = 350;
    public static double minminP = 0.05;
    private long start_time = System.currentTimeMillis();
    private long prevClick = System.currentTimeMillis();

    private DcMotor motor1;
    private DcMotor motor2;
    private Servo claw;
    private HardwareMap hw;
    public enum liftPos {
        BOTTOM,
        LOW,
        MID,
        UP,
        stack1,
        stack2,
        stack3,
        stack4,
        stack5
    }
    public static double p;

    private liftPos targetLiftPos;

    public lift(HardwareMap hw) {
        targetLiftPos = liftPos.BOTTOM;
        this.hw = hw;
        motor1 = hw.get(DcMotor.class, "motor1");
        motor2 = hw.get(DcMotor.class, "motor2");
        claw = hw.get(Servo.class, "servo");
        motor1.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void setMotorPower(double power) {
        double a_power = power;
        if (a_power < -0.5) {
            a_power = -0.5;
        }
        if (Math.abs(a_power) < 0.05) {
            a_power = 0;
        }
        motor1.setPower(a_power);
        motor2.setPower(a_power);
    }

    public double getMotorPosition() {
        return motor1.getCurrentPosition();
    }
    public void setServoPosition(double pos) {
        claw.setPosition(pos);
    }
    public void update() {

        double targetPos;
        switch (targetLiftPos) {
            case LOW:
                targetPos = low;
                break;
            case BOTTOM:
                targetPos = bottom;
                break;
            case MID:
                targetPos = mid;
                break;
            case UP:
                targetPos = up;
                break;
            case stack1:
                targetPos = stack1;
                break;
            case stack2:
                targetPos = stack2;
                break;
            case stack3:
                targetPos = stack3;
                break;
            case stack4:
                targetPos = stack4;
                break;
            case stack5:
                targetPos = stack5;
                break;
            default:
                targetPos = bottom;
                break;
        }

        double averagePos = motor1.getCurrentPosition();
        p = kp * (targetPos - averagePos);
        if (motor1.getCurrentPosition() < minPThreshold) {
            if (p < minPBottom) {
                p = minPBottom;
            }
        } else {
            if (p < minP) {
                p = minP;
            }
        }
        if (Math.abs(p) < minminP) {
            p = 0;
        }
        motor1.setPower(p);
    }

    public void resetMotorPos() {
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void setTargetLiftPos(liftPos pos) {
        targetLiftPos = pos;
    }

}
