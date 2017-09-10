package analytics;
import beans.Country;
import beans.Index;
import beans.ResultValue;
import beans.Value;
import services.ValueService;

import java.util.ArrayList;
import java.util.List;





/**
 * Ветошкин А.В. РИС-16бзу
 * */

public class Analytics {

    private Analytics() {

    }


/*    public static List<ResultValue> minSquare(Country country, Index index) {
        List<Value> values = ValueService.getValues(country, index);
        values.sort(Value::compareTo);
        return minSquare(values);
    }
*/

    public static List<ResultValue> minSquare(List<Value> list) {
        double midX = getSumX(list) / list.size();
        double midY = getSumY(list) / list.size();
        double b = (list.size() * getCompXY(list) - getSumX(list) * getSumY(list)) / (list.size() * getSumCompX(list) - Math.pow(getSumX(list), 2));
        double a = midY - b * midX;

        List<ResultValue> result = new ArrayList<>();
        for (Value stdValue : list)
            result.add(new ResultValue(stdValue.getValueX(), a + b * stdValue.getValueX()));

        return result;
    }


    private static double getSumX(List<Value> list) {
        double result = 0;
        for (Value value : list)
            result += value.getValueX();

        return result;
    }


    private static double getSumY(List<Value> list) {
        double result = 0;
        for (Value value : list)
            result += value.getValueY();

        return result;
    }


    private static double getCompXY(List<Value> list) {
        double result = 0;
        for (Value value : list)
            result += (value.getValueX() * value.getValueY());

        return result;
    }


    private static double getSumCompX(List<Value> list) {
        double result = 0;
        for (Value value : list)
            result += (Math.pow(value.getValueX(), 2));

        return result;
    }

}
