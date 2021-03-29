import java.math.BigDecimal;
import java.math.RoundingMode;

public class MethodTools extends VariableClass {

    void prx(Object line) {
        System.out.print(line);
    }

    void pr(Object line) {
        System.out.println(line);
    }

    public int monthToInt(String input) {
        for (int i = 0; i < 12; i++) {
            if (list_month[i].compareTo(input) == 0) {
                return i + 1;
            }
        }
        return 0;
    }

    public int xint(String input) {
        return Integer.parseInt(input);
    }

    public float xflt(String input) {
        return Float.parseFloat(input);
    }

    public String yint(int input) {
        return Integer.toString(input);
    }

    public String yflt(float input) {
        if (Math.signum(input) == 0) {
            return Float.toString(input);
        }
        BigDecimal bd = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);
        input = bd.floatValue();
        return Float.toString(input);
    }
}
