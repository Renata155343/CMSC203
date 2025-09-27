import java.util.Scanner;

public class MovieDriver_Test2 {
	public static void main(String[] args) {	
		Scanner keyboard = new Scanner(System.in);
		String keepGoing = "y";	
		do {
			Movie movie = new Movie();	
			System.out.println("Enter the name of a movie");
			String title = keyboard.nextLine();
			movie.setTitle(title);
		
			System.out.println("Enter the rating of the movie");
			String rating = keyboard.nextLine();
			movie.setRating(rating);
			
			System.out.println("Enter the number of tickets sold for this movie");
			int tickets = keyboard.nextInt();
			movie.setSoldTickets(tickets);
			
			System.out.println(movie);
			keyboard.nextLine(); 
			
			System.out.println("Do you want to enter another movie? (y/n)");
			keepGoing = keyboard.nextLine().toLowerCase();
		
		} while (keepGoing.equals("y"));
		keyboard.close();
	}
}
