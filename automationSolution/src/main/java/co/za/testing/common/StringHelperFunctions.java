package co.za.testing.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class StringHelperFunctions {

    private static final Logger logger
            = LoggerFactory.getLogger(StringHelperFunctions.class);

    public static double computeListTotal(List<String> stringAmountList){

        logger.info("Computing sum for {}",stringAmountList);

        List<Double> newList  = stringAmountList.stream()
                .map(stringAmount -> getDoubtAmount(stringAmount))
                .collect(Collectors.toList());

        return newList.stream().reduce(0.0, Double::sum);
    }

    public static double getDoubtAmount(String stringAmount){
        logger.info("Converting string amount {} to double",stringAmount);
        return Double.parseDouble(stringAmount.
                replace("$",""));
    }

    public static String generateScreenshotName(){
       return UUID.randomUUID().toString().replace("-","")+".png";
    }
}
