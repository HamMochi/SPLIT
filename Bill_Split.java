import static java.lang.System.out;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Bill_Split {
	public static void main(String args[]) {
		Scanner keyboard = new Scanner(System.in);
	     
		//How to Split
		out.println("Share or Individual?");
		out.println();
		String selectMethod = keyboard.next();
		
		switch(selectMethod) {
			case "Share": {
				
				//Number of People
				out.println("Please enter the number of people");
	
				globalVar.numPeople = keyboard.nextInt();
				out.println();
				
				//Split Bill
				out.println("Enter price, including tax");
				double price = keyboard.nextDouble();
				out.println();
				out.println("Enter tip");
				double tip = keyboard.nextDouble();
				out.println();
				double Total = price + tip;
				globalVar.pricePerPerson = Total/globalVar.numPeople;
				DecimalFormat moneyFormatter = new DecimalFormat("0.##");
				out.println("Price per Person: $" + moneyFormatter.format(globalVar.pricePerPerson));
				out.println();
				
				//Array of People
				ArrayList<Person> names = new ArrayList<Person>();
				addItem(names);
				
				//Get Change
				double sum = 0;
				for (Person list : names) {
					sum = sum + list.getamt();	
				}
				double change = Math.abs(sum);
				out.println("Change: " + moneyFormatter.format(change));
				
				/*Give Change
				if(change != 0) {
					for (Person list : names) {
						list.getamt();
						if(list.getamt() < 0) {
							addItem.amt += list.getamt() + change;
							out.println(list.getName() + "gets the change, " + amt);
							change = 0;
						}
					}
				} */
			}
		}
		keyboard.close();
	}
	
	public static class globalVar{
		public static int numPeople;
		public static double pricePerPerson;
	}
	
	public static void addItem(ArrayList<Person> names) {

		//Add People
		for (int i = 0; i < globalVar.numPeople; i++) {
    	
			//Name
			System.out.println("Enter name");
			Scanner addName = new Scanner(System.in);
			String name = (addName.nextLine());

			//amt
			out.println("Enter amount that this person will pay");
			Scanner addamt = new Scanner(System.in);
			double amt = (addamt.nextDouble());
			amt = globalVar.pricePerPerson - amt;
        
			//Add object to ArrayList
			names.add(new Person(name, amt));
		}
		DecimalFormat moneyFormatter2 = new DecimalFormat("0.##");
		for (Person list : names) {
			out.println("Name: " + list.getName() + ", Amount: $" + moneyFormatter2.format(list.getamt()));
			out.println();
		}
	}
}

class Person
{
    private String name;
    private double amt;
    
    public Person(String name, double amt) {
    	this.name = name;
    	this.amt = amt;
    }
    
    public String getName() {
        return name;
    }    

    public double getamt() {
        return amt;
    }
}

//amt (+: Need to pay, -: Owed money)