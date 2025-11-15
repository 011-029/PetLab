package util;

import java.time.LocalDate;

public class DateUtil {
    public static boolean matchesInPeriod(LocalDate target, LocalDate start, LocalDate end){
       if(target == null) return false;
       if(start == null && end ==null)
           return true;
       if (start == null)
           return !target.isAfter(start);
       if (end == null)
           return !target.isBefore(end);
       return !target.isBefore(start) && !target.isAfter(end);
    }
}
