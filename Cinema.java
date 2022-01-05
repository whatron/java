package cinema;
import java.util.Scanner;
import java.util.Arrays;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int column = scanner.nextInt();
        System.out.println();

        int purchased = 0;
        int income = 0;
        int total = 0;

        char seats[][] = createSeating(row, column); //creates the seating array

        while (true) {
            menu();
            int menuOption = scanner.nextInt();
            System.out.println();

            if (menuOption == 0) {
                break;
            }

            switch (menuOption) {
                case 1:
                    initialize(row, column, seats); //prints the seating arrangement
                    break;

                case 2:
                    int seatX;
                    int seatY;
                    while (true) {
                        while (true) {
                            System.out.println("Enter a row number:");
                            seatX = scanner.nextInt();
                            System.out.println("Enter a seat number in that row:");
                            seatY = scanner.nextInt();
                            System.out.println();
                            if (seatX > seats.length || seatY > seats[0].length || seatX < 0 || seatY < 0) {
                                System.out.println("Wrong input!");
                            } else {
                                break;
                            }
                        }

                        if (seats[seatX - 1][seatY - 1] == 'B') {
                            System.out.println("That ticket has already been purchased!");
                            System.out.println();
                        } else {
                            int seatPrice = seatPrice(row, column, seatX); //prints pricing
                            System.out.println("Ticket price: $" + seatPrice);
                            income += seatPrice;
                            seats[seatX - 1][seatY - 1] = 'B';
                            purchased++;

                            initialize(row, column, seats); //prints the new seating arrangement

                            break;
                        }
                    }
                    break;

                case 3:
                    double percentage = ((double) purchased * 100 / (double) (row * column));
                    System.out.printf("Number of purchased tickets: %d %n", purchased);
                    System.out.printf("Percentage: %.2f%% %n", percentage);
                    System.out.printf("Current income: $%d %n", income);
                    totalSeatPrice(row, column);
                    System.out.println();
            }
        }

    }

    public static void menu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static char[][] createSeating(int rows, int columns) {
        char[][] seats = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int n = 0; n < columns; n++) {
                seats[i][n] = 'S';
            }
        }
        return seats;
    }

    public static void initialize(int rows, int columns, char seats[][]) {

        System.out.println("Cinema:");
        for (int n = 0; n <= columns; n++) {
            if (n == 0) {
                System.out.print("  ");
            } else {
                System.out.print(n + " ");
            }
        }

        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print((i + 1) + " ");
            for (int a = 0; a < columns; a++) {
                System.out.print(seats[i][a] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int seatPrice(int rows, int columns, int seatX) {
        int totalSeats = rows * columns;
        int seatPrice;
        if (totalSeats < 60) {
            seatPrice = 10;
        } else {
            seatPrice = seatX > rows / 2 ? 8 : 10;
        }
        return seatPrice;
    }

    public static void totalSeatPrice(int rows, int columns) {
        int totalSeats = rows * columns;
        int totalSeatPrice;
        if (totalSeats < 60) {
            totalSeatPrice = 10 * totalSeats;
        } else if (totalSeats % 2 == 0) {
            totalSeatPrice = (totalSeats / 2 * 8) + (totalSeats / 2 * 10);
        } else {
            totalSeatPrice = (totalSeats / 2) * 8 + totalSeats / 2 * 10;
        }
        System.out.printf("Total income: $%d %n", totalSeatPrice);
    }
}
