package src;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public Date daysFromNow(int days) {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    public Date daysFrom(Date data, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }
}
