import java.util.StringTokenizer;

public class MyDate{
	private int day;
	private int month;
	private int year;

	public MyDate(String unformattedDate){
		StringTokenizer splitDate = new StringTokenizer(unformattedDate, "/");

		this.day = Integer.parseInt(splitDate.nextToken());
		this.month = Integer.parseInt(splitDate.nextToken());
		this.year = Integer.parseInt(splitDate.nextToken());
	}

	public MyDate(MyDate myDate){
		this.day = myDate.day;
		this.month = myDate.month;
		this.year = myDate.year;
	}

	public String toString(){
		String twoDigitYear = String.valueOf(this.year).substring(2,4);
		String monthName [] = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December"};
	
		return monthName[month-1] + " " + day + ", " + twoDigitYear;
		
	}

	public boolean lessThan(MyDate myDate){
		if(this.year < myDate.year){
			return true;
		}
		else if (this.year > myDate.year){
			return false;
		}
		else if (this.month < myDate.month){
			return true;
		}
		else if (this.month > myDate.month){
			return false;
		}
		else if (this.day < myDate.day){
			return true;
		}
		else
			return false;
	}

	public boolean equals(MyDate myDate) {
		if (myDate == null) return false;
		if((this.day == myDate.day) && (this.month == myDate.month) && (this.year == myDate.year)){
			return true;
		}
		else
			return false;
	}
}