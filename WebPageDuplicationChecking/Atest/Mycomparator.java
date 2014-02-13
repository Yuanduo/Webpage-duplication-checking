package Atest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Mycomparator  implements Comparator{
    public int compare(Object o1,Object o2) {
        Resources p1=(Resources)o1;
        Resources p2=(Resources)o2;
        
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d1 = new Date();
		Date d2 = new Date();
		
		try {
			d1 = date.parse(p1.getTime());
			d2 = date.parse(p2.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       if(d1.after(d2))
           return 1;
       else
           return 0;
       }

}