import java.util.*;

class Assignment2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the data bits : ");
        String data = sc.next();

        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) != '0' && data.charAt(i) != '1') {
                System.out.println("Invalid input. Enter only 0 and 1.");
                return;
            }
        }

        int m = data.length();
        System.out.println("Number of data bits = " + m);
        int r = 0;
        while (Math.pow(2, r) < m + r + 1) {
            r++;
        }
        int n = m + r;
        System.out.println("Number of parity bits required : " + r);
        System.out.println("The the total bits required : " + n);

        int hamming[] = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            hamming[i] = -1;
        }
        int p = 1;

        while (p <= n) {
            hamming[p] = 0;
            p = p * 2;
        }

        int index = 0;

        for (int i = n; i >= 1; i--) {

            if ((i & (i - 1)) == 0) {
                continue;
            }

            hamming[i] = data.charAt(index) - '0';
            index++;
        }

        p = 1;

        while (p <= n) {
            int value = 0;
            for (int i = 1; i <= n; i++) {
                if ((i & p) != 0 && i != p) {
                    value = value ^ hamming[i];
                }
            }
            hamming[p] = value;
            p = p * 2;
        }

        System.out.print("\nGenerated Hamming Code : ");
        for (int i = n; i >= 1; i--) {
            System.out.print(hamming[i]);
        }
        System.out.println();

        System.out.print("\nEnter the position to introduce error (1-" + n + "): ");
        int position = sc.nextInt();

        if (hamming[position] == 0) {
            hamming[position] = 1;
        } else {
            hamming[position] = 0;
        }

        System.out.print("\nReceived Code : ");
        for (int i = n; i >= 1; i--) {
            System.out.print(hamming[i]);
        }

        int errorPosition = 0;
        p = 1;

        while (p <= n) {

            int value = 0;

            for (int i = 1; i <= n; i++) {

                if ((i & p) != 0) {
                    value = value ^ hamming[i];
                }
            }

            if (value != 0) {
                errorPosition = errorPosition + p;
            }

            p = p * 2;
        }
        if (errorPosition == 0) {

            System.out.println("\nNo Error Detected.");

        } else {

            System.out.println("\nError Detected at Position : "
                    + errorPosition);

            // Correct the error
            hamming[errorPosition] = hamming[errorPosition] ^ 1;

            System.out.print("\nCorrected Code : ");

            for (int i = n; i >= 1; i--) {
                System.out.print(hamming[i]);
            }

            System.out.println();
        }
    }
}