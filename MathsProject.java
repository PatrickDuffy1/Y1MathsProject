import java.util.*;

public class MathsProject
{
	static Random randNum = new Random();
	static Scanner keyboard = new Scanner(System.in);

	public static int x = 0, y = 0;
	public static int maxX = 101, maxY = 5;
	public static int playerXPosition = 3, playerYPosition = 2;
	public static int generationCounter = 1;
	public static int timeAlive, time = 0;
	public static int northChance = 25, southChance = 25, westChance = 25, eastChance = 25;
	public static int direction;
	public static int maximumMoves = 1000;
	public static int bestDistance = playerXPosition;
	public static int configurationSelection;
	public static int intervalTime = 10;
	public static boolean alive = true;
	public static boolean complete = false;
	public static boolean displayEnabled = true;
	public static String nextMove = "Manual";


	public static void main(String[] args)
	{

		System.out.println("=============================");
		System.out.println("        MATHS PROJECT        ");
		System.out.println("=============================\n");

		do
		{
			displayConfiguration();

		}while(configurationSelection != 0);

		for(int i = 0; i < maximumMoves && !complete; i++)
		{
			if(i > 0)
			{
				movement();
				collisionDetection();

				if(playerXPosition > bestDistance)
				{
					bestDistance = playerXPosition;
				}

				if(alive)
				{
					oddsPlusOne(direction);
				}
			}

			System.out.println("Generation " + generationCounter + "\tTime Alive " + timeAlive + "\tTime " + time + "\tBest Distance " + bestDistance + "\tCurrent Distance " + playerXPosition);
			System.out.println("Direction Odds: North Chance:" + northChance + ", South Chance:" + southChance + ", West Chance:" + westChance + ", East Chance:" + eastChance);

			alive = true;

			if(displayEnabled)
			{
				display();
			}

			System.out.println("\n\n\n");

			if(nextMove.equals("Automatic"))
			{
				wait(intervalTime);
			}
			else
			{
				keyboard.nextLine();
			}
		}
	}


	public static void movement()
	{
		int move = randNum.nextInt(100) + 1;

		direction = 0;
		moveCalculation(move);

		switch(direction)
		{
			case 1:
				playerYPosition--;
				break;

			case 2:
				playerYPosition++;
				break;

			case 3:
				playerXPosition--;
				break;

			case 4:
				playerXPosition++;
				break;

			default:
				System.out.println("\n\n\nMove Error:" + move);
				break;
		}

		timeAlive++;
		time++;
	}


	public static void display()
	{
		int typeOfCharacter = 0;
		char blankSpace = ' ';
		char player = 'A';
		char verticalWall = '|', horizontalWall = '-';

		while(x <= maxX || y < maxY)
		{
			typeOfCharacter = 0;

			if(x == (maxX + 1))
			{
				x = 0;
				y++;
				System.out.print("\n");
			}

			if(x == 0 || x == maxX)
			{
				typeOfCharacter = 1;
			}
			else if(y == 0 || y == maxY)
			{
				typeOfCharacter = 2;
			}
			else if(x == playerXPosition && y == playerYPosition)
			{
				typeOfCharacter = 3;
			}

			switch(typeOfCharacter)
			{
				case 0:
					System.out.print(blankSpace);
				break;

				case 1:
					System.out.print(verticalWall);
				break;

				case 2:
					System.out.print(horizontalWall);
				break;

				case 3:
					System.out.print(player);
				break;
			}

			x++;
		}

		x = y = 0;
	}


	public static void collisionDetection()
	{
		if(playerYPosition == maxY || playerXPosition == 0 || playerYPosition == 0)
		{
			oddsMinusTen(direction);
			timeAlive = 0;
			alive = false;
			playerXPosition = 3;
			playerYPosition = 2;
			generationCounter++;
			System.out.println("Hit");
		}
		else if(playerXPosition == maxX)
		{
			System.out.println("Complete");
			complete = true;
		}
	}


	public static void wait(int milliseconds)
	{
		try
		{
			Thread.sleep(milliseconds);
		}
		catch(InterruptedException ex)
		{
			Thread.currentThread().interrupt();
		}
	}


	public static void moveCalculation(int move)
	{

		if(move > 0 && move <= northChance)
		{
			direction = 1;
		}
		else if(move > northChance && move <= northChance + southChance)
		{
			direction = 2;
		}
		else if(move > (northChance + southChance) && move <= (northChance + southChance + westChance))
		{
			direction = 3;
		}
		else if(move > (northChance + southChance + westChance) && move <= (northChance + southChance + westChance + eastChance))
		{
			direction = 4;
		}
	}


	public static void oddsPlusOne(int direction)
	{
		switch(direction)
		{
			case 1:
				northChance++;
				break;

			case 2:
				southChance++;
				break;

			case 3:
				westChance++;
				break;

			case 4:
				eastChance++;
				break;

			default:
				System.out.println("DirectionOddsPlusOneError: " + direction);
				break;
		}

		oddsMinusOne(direction);
	}


	public static void oddsMinusOne(int direction)
	{
		int randomDirection;

		do
		{
			randomDirection = randNum.nextInt(4) + 1;

		}while(randomDirection == direction);

		switch(randomDirection)
		{
			case 1:
				northChance--;
				break;

			case 2:
				southChance--;
				break;

			case 3:
				westChance--;
				break;

			case 4:
				eastChance--;
				break;

			default:
				System.out.println("DirectionOddsMinusOneError: " + randomDirection);
				break;
		}

		greaterThanOneHundredTest();
	}


	public static void oddsPlusTen(int direction)
	{

		int randomDirection;

		for(int i = 0; i < 10; i++)
		{
			do
			{
				randomDirection = randNum.nextInt(4) + 1;

			}while(randomDirection == direction);

			switch(randomDirection)
			{
				case 1:
					northChance++;
					break;

				case 2:
					southChance++;
					break;

				case 3:
					westChance++;
					break;

				case 4:
					eastChance++;
					break;

				default:
					System.out.println("DirectionOddsPlusTenError: " + randomDirection);
					break;
			}
		}

		greaterThanOneHundredTest();
	}


	public static void oddsMinusTen(int direction)
	{

		switch(direction)
		{
			case 1:
				northChance -= 10;
				break;

			case 2:
				southChance -= 10;
				break;

			case 3:
				westChance -= 10;
				break;

			case 4:
				eastChance -= 10;
				break;

			default:
				System.out.println("DirectionOddsError: " + direction);
				break;
		}

		oddsPlusTen(direction);
	}


	public static void greaterThanOneHundredTest()
	{
		do
		{
			while((northChance + southChance + westChance + eastChance) < 100)
			{
				switch(randNum.nextInt(4) + 1)
				{
					case 1:
						northChance++;
						break;

					case 2:
						southChance++;
						break;

					case 3:
						westChance++;
						break;

					case 4:
						eastChance++;
						break;

					default:
						System.out.println("LessThan100ERROR");
						break;
				}
			}

			while(northChance + southChance + westChance + eastChance > 100)
			{
				switch(randNum.nextInt(4) + 1)
				{
					case 1:
						northChance--;
						break;

					case 2:
						southChance--;
						break;

					case 3:
						westChance--;
						break;

					case 4:
						eastChance--;
						break;

					default:
						System.out.println("GreaterThan100ERROR");
						break;
				}
			}

			if(northChance > 100)
			{
				northChance = 100;
			}
			else if(southChance > 100)
			{
				southChance = 100;
			}
			else if(westChance > 100)
			{
				westChance = 100;
			}
			else if(eastChance > 100)
			{
				eastChance = 100;
			}

			if(northChance < 0)
			{
				northChance = 0;
			}
			else if(southChance < 0)
			{
				southChance = 0;
			}
			else if(westChance < 0)
			{
				westChance = 0;
			}
			else if(eastChance < 0)
			{
				eastChance = 0;
			}

		}while(northChance + southChance + westChance + eastChance != 100);
	}


	public static void displayConfiguration()
	{
		int userChoice;

		System.out.println("0. Start");
		System.out.println("1. Max X: " + maxX + ", Max Y: " + maxY);
		System.out.println("2. Maximum amount of moves: " + maximumMoves);
		System.out.println("3. Display enabled: " + displayEnabled);
		System.out.print("4. Next move: " + nextMove);

		if(nextMove.equals("Automatic"))
		{
			System.out.println(", Interval time: " + intervalTime);
		}
		else
		{
			System.out.println();
		}

		System.out.print("\nSelect 0 to continue with current configuration. Select number 1 - 4 to change a setting: ");
		configurationSelection = keyboard.nextInt();

		switch(configurationSelection)
		{
			case 1:
				System.out.print("Enter max X: ");
				maxX = keyboard.nextInt();
				System.out.print("Enter max Y: ");
				maxY = keyboard.nextInt();
				break;

			case 2:
				System.out.print("Enter maximum moves: ");
				maximumMoves = keyboard.nextInt();
				break;

			case 3:
				if(displayEnabled)
				{
					displayEnabled = false;
				}
				else
				{
					displayEnabled = true;
				}
				break;

			case 4:
				System.out.print("Select 1 for manual, 2 for automatic: ");

				userChoice = keyboard.nextInt();

				if(userChoice == 1)
				{
					nextMove = "Manual";
				}
				else if(userChoice == 2)
				{
					do
					{
						System.out.print("Enter interval time in milliseconds: ");
						intervalTime = keyboard.nextInt();

						if(intervalTime < 0)
						{
							System.out.println("Please enter a number greater than or equal to 0");
						}

					}while(intervalTime < 0);

					nextMove = "Automatic";
				}
				break;
		}

		keyboard.nextLine();

		System.out.println("\n\n");
	}
}