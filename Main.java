import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner input;//changed in to input-Zeeshan 13/8
	private static Library library;//Changed LIB to library-Zeeshan 13/8
	private static String menu;//Changed MENU to menu-Zeeshan 13/8
	private static Calendar calender;//Changed Calender to calender
	private static SimpleDateFormat SDF;
	
	
	private static String getMenu() {//Changed function name to getMenu-Zeeshan 13/8
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return sb.toString();
	}


	public static void main(String[] args) {		
		try {			
			input = new Scanner(System.in);//changed in to input-Zeeshan 13/8
			library = library.INSTANCE();//Changed LIB to library-Zeeshan 13/8
			calender = Calendar.INSTANCE();//Changed MENU to menu-Zeeshan 13/8
			SDF = new SimpleDateFormat("dd/MM/yyyy");
	
			for (member m : library.members()) {//Changed LIB to library and MEmbers to members-Zeeshan 13/8
				output(m);
			}
			output(" ");
			for (book b : library.books()) {//Changed LIB to library and BOOKS to books-Zeeshan 13/8
				output(b);
			}
						
			MENU = getMenu();//Changed GET_MENU to getMEnu-Zeeshan 13/8
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + SDF.format(calender.date()));//Changed claneder to calender and DATE to date-Zeeshan 13/8
				String c = input(menu);//Changed MENU to menu-Zeeshan 13/8
				
				switch (c.toUpperCase()) {
				
				case "M": 
					addMember();//Changed ADD_MEMBER to addMember-Zeeshan 13/8
					break;
					
				case "LM": 
					members();//Changed MEMBERS to members-Zeeshan 13/8
					break;
					
				case "B": 
					addBook();//Changed ADD_BOOK-Zeeshan 13/8
					break;
					
				case "LB": 
					books();////Changed BOOKS to books-Zeeshan 13/8
					break;
					
				case "FB": 
					fixBooks();//Changed  FIX_BOOKS to fixBooks-Zeeshan 13/8
					break;
					
				case "L": 
					borrowBook();//Changed BORROW_BOOK to borrowBook-Zeeshan 13/8
					break;
					
				case "R": 
					returnBook();//Changed RETURN_BOOK to returnBook-Zeeshan 13/8
					break;
					
				case "LL": 
					currntLoan();//Changed CURRENT_LOANS to currntLoan-Zeeshan 13/8
					break;
					
				case "P": 
					fines();//Changed FINES TO fines -Zeeshan 13/8
					break;
					
				case "T": 
					incrementDate();//Changed INCREMENT_DATE to incrementDate-Zeeshan 13/8
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.toSave();//Changed SAVE to toSave-Zeeshan 13/8
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

		private static void fines() {//Changed FINES TO fines -Zeeshan 13/8
		new payFineUI(new payFineControl()).run();//Changed payFineControl, payFineUI and run-Zeeshan 13/8		
	}


	private static void currntLoan() {//Changed CURRENT_LOANS to currntLoan-Zeeshan 13/8
		output("");
		for (loan loan : library.currentLoan()) {//Changed LIB to library and CURRENT_LOANS to currentLoan-Zeeshan 13/8
			output(loan + "\n");
		}		
	}



	private static void books() {//Changed BOOKS to book -Zeeshan 13/8
		output("");
		for (book book : library.books()) {//Changed LIB to library and CURRENT_LOANS to currentLoan-Zeeshan 13/8
			output(book + "\n");
		}		
	}



	private static void members() {//Changed MEMEBERS to member-Zeeshan 13/8
		output("");
		for (member member : library.members()) {//Changed LIB to library and MEMEBERS to member-Zeeshan 13/8 
			output(member + "\n");
		}		
	}



	private static void borrowBook() {//Changed BORROW_BOOK to borrowBook-Zeeshan 13/8
		new borrowBookUI(new borrowBookControl()).run();//Changed borrowBookUI and borrowBookControl-Zeeshan 13/8		
	}


	private static void returnBook() {//Changed returnBook//Zeeshan 13/8
		new returnBookUI(new returnBookControl()).run();//Changed returnBookUI ,returnBookControl and run-Zeeshan 13/8		
	}


	private static void fixBooks() {//Changed FixBooks to fixBooks-Zeeshan 13/8
		new fixBookUI(new fixBookControl()).RuN();		//Changed fixBookUi and FixBookControl and run -Zeeshan 13/8
	}


	private static void incremnetDate() {//Changed function name to incrementDate-Zeeshan 13/8
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			calender.incrementDate(days);//Changed CAL to calender-Zeeshan 13/8
			library.checkCurrentLoans();//Changed LIB to library-Zeeshan 13/8
			output(SDF.format(CAL.Date()));
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() {//Changed ADD_BOOK to addBook-Zeeshan 13/8
		
		String A = input("Enter author: ");
		String T  = input("Enter title: ");
		String C = input("Enter call number: ");
		book B = library.addBook(A, T, C);//changed LIB and ADD_BOOK-Zeeshan 13/8
		output("\n" + B + "\n");
		
	}

	
	private static void addMember() {//Changed ADD_MEMBER to addMember-Zeeshan 13/8
		try {
			String lastName = input("Enter last name: ");//Changed LN to lastname -Zeeshan 13/8
			String firstName  = input("Enter first name: ");//Changed FN to firstNAme-Zeeshan 13/8
			String email = input("Enter email: ");//Changed en to email -Zeeshan 13/8
			int phoneNumber = Integer.valueOf(input("Enter phone number: ")).intValue(); //changed pn to phoneNUmber -Zeeshan 13/8
			member M = library.addMember(LN, FN, EM, PN);//Changed LIB to library -Zeeshan 13/8
			output("\n" + M + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String getInput(String prompt) {//Changed function name to getInput
		System.out.print(prompt);
		return IN.nextLine();
	}
	
	
	
	private static void displayOutput(Object object) {//Chanded function name to displayOUtput-Zeeshan 13/8
		System.out.println(object);
	}

	
}
