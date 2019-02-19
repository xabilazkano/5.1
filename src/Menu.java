import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import com.zubiri.user.*;

public class Menu {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Users users = null;
		try {
			// the next line can throw an exception
			users = new Users();
		} catch (IOException e) {
			System.out.println("Error creating the file");
		}
		boolean repeat = true;
		while (repeat) {
			System.out.println("Press one of the following numbers:");
			System.out.println("[1] Login");
			System.out.println("[2] Register");
			System.out.println("[0] Exit");
			String choice = sc.next().trim();
			sc.nextLine();
			switch (choice) {
			case "0":
				System.out.println("Bye!");
				repeat = false;
				break;
			case "1":
				for (int i = 0; i < 10; ++i)
					System.out.println();
				try {
					// the next line can throw an exception
					if (users.hasUser() == false) {
						System.out.println("No user registered yet");
						System.out.println("----------------------");
					} else {
						System.out.println("Username:");
						String username = sc.next();
						sc.nextLine();
						User user = users.getUser(username);
						if (username.equals(user.getName())) {
							System.out.println("Password:");
							String password = sc.nextLine();
							if (password.equals(user.getPassword())) {
								System.out.println("Hi " + username + "!");
								System.out.println();
								System.out.println(
										"Sorry, there are no options avaiable yet, so you are being logged out.");
								System.out.println("----------------------");
							} else
								System.out.println("Incorrect password");
						} else
							System.out.println("Incorrect username");

					}
				} catch (FileNotFoundException e) {
					System.err.println("The file wasn't found");
				}
				break;
			case "2":
				User user = new User();
				for (int i = 0; i < 10; ++i)
					System.out.println();
				boolean askUsername = true;
				while (askUsername) {
					System.out.println("Username:");
					String name = sc.nextLine();
					try {
						// the next line can throw an exception
						if (users.getUser(name).getName().equals("null")) {
							// the next line can throw an exception
							if (user.setName(name)) {
								askUsername = false;
								boolean askPassword = true;
								while (askPassword) {
									System.out.println("Password:");
									String password = sc.nextLine();
									if (user.setPassword(password)) {
										System.out.println("Done");
										System.out.println("----------------------");
										// the next line can throw an exception
										users.setUser(user);
										askPassword = false;
									} else {
										System.out.println("Enter a valid password");
										System.out.println(
												"It has to have at least a letter,  a number, a symbol and 8 or more characters long");
									}
								}
							} else
								System.out.println("Enter a valid username (no numbers or digits)");
						} else
							System.out.println("That username is alrady taken, try with another one.");
					} 	catch (FileNotFoundException f) {

						System.err.println("The file does not exist");
					}
					
					catch (IOException a) {

						System.err.println("Error while interactiong with the file");
					}

				}
				break;
			default:
				System.out.println("Enter a valid number");
				break;
			}
		}
		sc.close();
	}
}
