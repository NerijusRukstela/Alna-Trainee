import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Clock's scale is 12 hours");
            System.out.println("Insert hour");
            double h = AngleCalculator.SetOrExit();
            System.out.println("Insert minute");
            double min = AngleCalculator.SetOrExit();
            System.out.println("Angle between hour and minute arrows " + AngleCalculator.MeasureAngleDegree(h, min) + " degree.");
        }
    }
}
