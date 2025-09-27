import java.util.Scanner;
public class MovieDriver_Test1 {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		Movie movie = new Movie();
		//collect info
		System.out.println("Enter the title of a movie: ");
		String title = keyboard.nextLine();
		movie.setTitle(title);
		System.out.println("Enter the rating of the movie: ");
		String rating = keyboard.nextLine();
		movie.setRating(rating);
		System.out.println("Enter the number of tickets sold for this movie: ");
		int tickets = keyboard.nextInt();
		movie.setSoldTickets(tickets);
		keyboard.nextLine(); 
		//output info
		System.out.println(movie);

		keyboard.close();
	}

}
