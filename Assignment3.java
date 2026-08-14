import java.util.Scanner;


class Assignment3 {

    static char getIpClass(int firstOctet) {
        if (firstOctet >= 0 && firstOctet <= 127)
            return 'A';
        else if (firstOctet >= 128 && firstOctet <= 191)
            return 'B';
        else if (firstOctet >= 192 && firstOctet <= 223)
            return 'C';
        else if (firstOctet >= 224 && firstOctet <= 239)
            return 'D';
        else if (firstOctet >= 240 && firstOctet <= 255)
            return 'E';
        else
            return 'X';
    }

    static String getDefaultSubnetMask(char ipClass) {
        switch (ipClass) {
            case 'A':
                return "255.0.0.0";
            case 'B':
                return "255.255.0.0";
            case 'C':
                return "255.255.255.0";
            default:
                return "N/A";
        }
    }

    static String getNumberOfNetworks(char ipClass) {
        switch (ipClass) {
            case 'A':
                return "2^7 (128 possible networks, 126 usable)";
            case 'B':
                return "2^14 (16,384 possible networks)";
            case 'C':
                return "2^21 (2,097,152 possible networks)";
            default:
                return "N/A";
        }
    }

    static long getUsableHosts(char ipClass) {
        switch (ipClass) {
            case 'A':
                return (1L << 24) - 2;
            case 'B':
                return (1L << 16) - 2;
            case 'C':
                return (1L << 8) - 2;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter an IP address (format: x.x.x.x): ");
        String input = sc.next();

        String[] parts = input.split("\\.");

        if (parts.length != 4) {
            System.out.println("Invalid IP address format.");
            return;
        }

        int octet1 = Integer.parseInt(parts[0]);
        int octet2 = Integer.parseInt(parts[1]);
        int octet3 = Integer.parseInt(parts[2]);
        int octet4 = Integer.parseInt(parts[3]);

        if (octet1 < 0 || octet1 > 255 ||
            octet2 < 0 || octet2 > 255 ||
            octet3 < 0 || octet3 > 255 ||
            octet4 < 0 || octet4 > 255) {

            System.out.println("Invalid IP address format or octets out of bounds (0-255).");
            return;
        }

        System.out.println("IP Address : " +
                octet1 + "." + octet2 + "." + octet3 + "." + octet4);

        if (octet1 == 127) {

            System.out.println("Class : A (Reserved)");
            System.out.println("Notes : Loopback address");
            System.out.println("Starting IP : 127.0.0.0");
            System.out.println("Last IP : 127.255.255.255");

        } 
        else {

            char ipClass = getIpClass(octet1);

            System.out.println("Class : " + ipClass);

            if (ipClass == 'A' || ipClass == 'B' || ipClass == 'C') {

                System.out.println("Subnet Mask : " +
                        getDefaultSubnetMask(ipClass));

                System.out.println("No. of Networks : " +
                        getNumberOfNetworks(ipClass));

                System.out.println("Usable Hosts : " +
                        getUsableHosts(ipClass) +
                        " (calculated using 2^n - 2)");

                if (ipClass == 'A') {
                    System.out.println("Starting IP : " + octet1 + ".0.0.0");
                    System.out.println("Last IP : " + octet1 + ".255.255.255");
                } 
                else if (ipClass == 'B') {
                    System.out.println("Starting IP : " +
                            octet1 + "." + octet2 + ".0.0");
                    System.out.println("Last IP : " +
                            octet1 + "." + octet2 + ".255.255");
                } 
                else {
                    System.out.println("Starting IP : " +
                            octet1 + "." + octet2 + "." + octet3 + ".0");
                    System.out.println("Last IP : " +
                            octet1 + "." + octet2 + "." + octet3 + ".255");
                }

            } 
            else if (ipClass == 'D') {

                System.out.println("Notes : Multicast address");

            } 
            else if (ipClass == 'E') {

                System.out.println("Notes : Experimental address");
            }
        }

        sc.close();
    }
}