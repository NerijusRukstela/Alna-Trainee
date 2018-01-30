import java.util.Scanner;
    public class AngleCalculator {
        private static double FindHourArrow(double h, double min) {
            return h * (360 / 12) + min * 0.5;
        }

        private static double FindMinuteArrow(double min) {
            return min * (360 / 60);
        }

        public static double MeasureAngleDegree(double hour, double minutes) {
            double degreeBetweenArrow = Math.abs(FindHourArrow(hour, minutes) - FindMinuteArrow(minutes));
            double smallestAngle;
            if (degreeBetweenArrow > 180) {
                smallestAngle = Math.abs(degreeBetweenArrow - 360);
            } else {
                smallestAngle = degreeBetweenArrow;
            }
            return smallestAngle;
        }

        public static double SetOrExit() {
            Scanner input = new Scanner(System.in);
            String hOrMinArrow = input.next().toUpperCase();
            double time = 0;
            if (hOrMinArrow.equals("Q")) {
                System.exit(0);
            } else {
                time = Double.parseDouble(hOrMinArrow);
            }
            double setTime = time;
            return setTime;
        }
    }

