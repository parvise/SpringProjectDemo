package com.journaldev.spring.controller;

import java.util.Calendar;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getDatesForPeriods(18, 0, true);
	}

	 private static Date[] getDatesForPeriods(int pNbFuturePeriods, int pNbPastPeriod, boolean pIsFirstOfMonth) {
		    Date[] theDates = new Date[pNbPastPeriod + pNbFuturePeriods];
		    Calendar theCalendar = Calendar.getInstance();
		    theCalendar.set(Calendar.DAY_OF_MONTH, 1);
		    theCalendar.add(Calendar.MONTH, -1 * pNbPastPeriod);

		    for (int i=0;i<theDates.length;i++) {
		      if (i != 0)
		        theCalendar.add(Calendar.MONTH, 1);

		      if (pIsFirstOfMonth) {
		        theDates[i] = theCalendar.getTime();
		      } else {
		        theCalendar.set(Calendar.DAY_OF_MONTH, theCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		        theDates[i] = theCalendar.getTime();
		      }
		    }
		    

		    return theDates;
		  }

}
