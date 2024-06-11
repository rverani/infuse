package br.com.infuse.api.util;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class CalculateValue {

    public static BigDecimal calcDesconto(int quant, @NotNull BigDecimal valor) {
        BigDecimal returnValue = valor.multiply(new BigDecimal(quant));
        if (quant >= 10  ) {
            returnValue = returnValue.subtract(returnValue.multiply(new BigDecimal("0.10")));
            return round(returnValue, 2);
        } else if (quant < 10 &&  quant > 5 ) {
            returnValue = returnValue.subtract(returnValue.multiply(new BigDecimal("0.05")));
            return round(returnValue, 2);
        } else {
            return returnValue;
        }
    }

    public static BigDecimal round(BigDecimal value, int scale) {
        BigDecimal bd1 = value;
        BigDecimal bd2 = bd1.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return bd2;
    }

}
