import java.util.Scanner;

public class cinemabooking {
	public static void main(String args[]) {
		Scanner console = new Scanner (System.in);
        //declaration
		int option;
		String choice;
		//Initialization of array
		String[][] movies = new String[][] {
			{"S.No ","Showtimes ","    Movie Title ",   "Vacancy"},
			{"1","13:00 - 2 Oct", "    UNDERCOVERS   ", "SOLD OUT"},
			{"2","12:00 - 3 Oct", "    LA LA LAND    ", "YES"},
			{"3","19:00 - 5 Oct", "    BURN THE STAGE", "YES"},
			{"4","14:00 - 4 Oct", "    VENOM         ", "SOLD OUT"},
			{"5","15:00 - 3 Oct", "    THE SHINING   ", "YES"},
			{"6","18:00 - 6 Oct", "    HALLOWEEN     ", "YES"},
			{"7","18:00 - 6 Oct", "    THE DEATH CURE", "SOLD OUT"},
		};
		//Initialization of array
		String[][] cinema = new String[][] {
			{"X", "1", "2", "3", "4"},
			{"1", " ", " ", " ", " "},
			{"2", " ", " ", " ", "X"},
			{"3", " ", "X", "X", " "},
			{"4", " ", "X", " ", "X"},
		};
		do {
			System.out.println("JACKY Theatre’s Self-service Ticketing");
			System.out.println("Choose one of the following: \n1) Name of the movies available");
			System.out.println("2) Fast booking (where system will automatically select best seat)");
			System.out.println("3) To select theatre’s seat manually");
			System.out.println("4) Quit");
			option = console.nextInt();
			//validation
			option=validate(option,4,1);
			switch(option) {
			case 1:
				displayArray(movies);
				break;
			case 2:
				fastBooking(cinema,movies);
				break;
			case 3:
				bookSeats(cinema, movies);
				break;
			case 4:
				System.out.println("Thank you for using our service");
				break;
			default: 
				System.out.println("Invalid Option.");
			}

			System.out.println("Do you want to repeat the program? Enter yes or no");
			choice=console.next().toLowerCase();
			//validation
			while (!((choice.equals("yes"))||(choice.equals("no")))) {
				System.out.println("Invalid Entry,Try again");
				choice = console.next().toLowerCase();
			}

		}while(choice.equals("yes"));

	}//end main method

    //method for displaying the cinema array
	public static void displayArray(String [][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.printf(array[i][j] + " \t ");
			}
			System.out.println();
		}
	}//end method displayArray
	//method for booking theatre seats manually
	public static void bookSeats(String [][] array, String [][] movies) {
		Scanner console = new Scanner (System.in);
        //declaration
		int numberofseats=0, a, b;
		String confirm = null;
		int nameofmovie;
		System.out.println("To select theatre’s seat manually");
		System.out.println("The movies are:");
		//displaying movies
		for(int i=1;i<movies.length;i++) {
			System.out.println(i+" "+movies[i][2]);}
		//end for loop

		System.out.println("Enter the number of the movie you want to see, For Example: Enter 1 to see UNDERCOVERS");
		nameofmovie=console.nextInt();
		//validation
		nameofmovie = validate(nameofmovie, 7, 1);

		while(movies[nameofmovie][3]=="SOLD OUT") {
			System.out.println("the seats for the movie entered are sold out,Enter the number of another movie you want to see");
			nameofmovie=console.nextInt();
			//validation
			nameofmovie = validate(nameofmovie, 7, 1);		
		}//end while loop
        System.out.println("The seats for the movie entered are available");
		System.out.println("How many seats do you want to book?");
		numberofseats = console.nextInt();
		//validation
		numberofseats = validate(numberofseats, 10, 1);

		System.out.println("Cinema now");
		//cinema seating before the seats have been booked by the user
		displayArray(array);

		for(int i = 0; i < numberofseats; i++) {
			System.out.println("Enter the row and column in which you want a seat");
			a = console.nextInt();
			//validation
			a = validate(a, 4,1);
			b = console.nextInt();
			//validation
			b = validate(b, 4,1);

			while(array[a][b]=="X") {
				System.out.println("The seat is unavailable,Enter another seat number");

				System.out.println("Enter the row and column in which you want a seat");
				a = console.nextInt();
				//validation
				a = validate(a, 4,1);
				b = console.nextInt();
				//validation
				b = validate(b, 4,1);
			}//end while loop
			array [a][b] = "O";
		}//end for loop
		System.out.println("Cenima after booking");
		//cinema seating after seats have been booked by the user
		displayArray(array);

		System.out.println("To confirm your seats press Y and to cancel your seats press N:");
		confirm = console.next().toUpperCase();
        //validation 
		while (!((confirm.equals("Y"))||(confirm.equals("N")))) {
			System.out.println("Invalid Entry,Try again");
			confirm = console.next().toUpperCase();
		}
		//if the user confirms the seat
		if(confirm.equals("Y")) {
			System.out.println("Your seats have been booked");
			for(int i=0;i<array.length;i++) {
				for(int j=0;j<array[i].length;j++) {
					if(array[i][j]=="O") {
						array[i][j]="X";
					}
				}
			}//end for loop
			//displays the total price of the tickets purchased
			System.out.println("Total ticket(s) price for "+numberofseats+" ticket(s) is $" + numberofseats*5);
		}
        //if the user cancels the seat
		else if(confirm.equals("N")){
			System.out.println("The seats have been cancelled");
			for(int i=0;i<array.length;i++) {
				for(int j=0;j<array[i].length;j++) {
					if(array[i][j]=="O") { 
						array[i][j]=" ";
					}
				}
			}//end for loop
		}
	}//end method bookSeats

   //method for validating input from the user
	public static int validate(int num, int max, int min) {
		Scanner console = new Scanner (System.in);
		while ((num > max) || (num<min)) {
			System.out.println("Invalid Entry,Try Again");
			num = console.nextInt();
		}
		return num;
	}//end method validate
	
    //method for automatically booking theatre seats
	public static void fastBooking(String [][] array,String [][]movies) {
		Scanner console = new Scanner(System.in);
        //declaration
		int numberofseats,counter = 0;
		int nameofmovie;
		System.out.println("The movies are:");
		//displaying movies
		for(int i=1;i<movies.length;i++) {
			System.out.println(i+" "+movies[i][2]);}
		//end for loop

		System.out.println("Enter the number of the movie you want to see, For Example: Enter 1 to see UNDERCOVERS");
		nameofmovie=console.nextInt();
		//validation
		nameofmovie = validate(nameofmovie, 7, 1);

		while(movies[nameofmovie][3]=="SOLD OUT") {
			System.out.println("the movie entered is sold out,Enter the number of another movie you want to see");
			nameofmovie=console.nextInt();
			//validation
			nameofmovie = validate(nameofmovie, 7, 1);		
		}

		System.out.println("How many seats do you want to book?");
		numberofseats = console.nextInt();
		//validation
		numberofseats=validate(numberofseats,10,1);

       //to book seats automatically
		for (int i = 0; (i < array.length); i++) {
			for (int j = 0; (j < array[i].length); j++) {
				if(array [i][j].equals(" ") && (counter < numberofseats)) {
					System.out.println("The seat(s) is/are " + i + "," + j);
					array[i][j] = "O";
					counter++;
				}
			}
		}//end for loop
		
        //cinema seating after seats have been booked
		displayArray(array);
		//displays the total price of the tickets purchased
		System.out.println("Total ticket(s) price for "+numberofseats+" ticket(s) is $" + numberofseats*5);
		for(int i=0;i<array.length;i++) {
			for(int j=0;j<array[i].length;j++) {
				if(array[i][j]=="O") { 
					array[i][j]="X";
				}
			}
		}//end for loop
		
	}//end method fastBooking
}//end class cinemabooking
