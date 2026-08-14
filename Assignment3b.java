import java.util.Scanner;


class Assignment3b {
        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int o1, o2, o3, o4;
        int cidr;

        System.out.print("Enter base IP address (e.g., 192.168.10.0): ");
        String ip = sc.next();

        String[] parts = ip.split("\\.");
        o1 = Integer.parseInt(parts[0]);
        o2 = Integer.parseInt(parts[1]);
        o3 = Integer.parseInt(parts[2]);
        o4 = Integer.parseInt(parts[3]);

        System.out.print("Enter CIDR prefix (e.g., 26 for /26): ");
        cidr = sc.nextInt();

        if (cidr < 24 || cidr > 30) {
            System.out.println("Error: This program currently supports Class C subnetting (CIDR 24 to 30).");
            return;
        }

        int hostBits = 32 - cidr;
        int blockSize = (int) Math.pow(2, hostBits);
        int numSubnets = 256 / blockSize;
        int usableHosts = blockSize - 2;
        int lastOctetMask = 256 - blockSize;

        System.out.println("\n--- General Subnet Information ---");
        System.out.println("Calculated Subnet Mask : 255.255.255." + lastOctetMask);
        System.out.println("Total Usable Hosts     : " + usableHosts + " per subnet");
        System.out.println("Total Subnets Created  : " + numSubnets + "\n");

        for (int i = 0; i < numSubnets; i++) {
            int network = i * blockSize;
            int firstHost = network + 1;
            int lastHost = network + usableHosts;
            int broadcast = network + blockSize - 1;

            System.out.println("=========================================");
            System.out.println("Subnet " + (i + 1));
            System.out.println("=========================================");
            System.out.println("Network Address   : " + o1 + "." + o2 + "." + o3 + "." + network);
            System.out.println("First Host        : " + o1 + "." + o2 + "." + o3 + "." + firstHost);
            System.out.println("Last Host         : " + o1 + "." + o2 + "." + o3 + "." + lastHost);
            System.out.println("Broadcast Address : " + o1 + "." + o2 + "." + o3 + "." + broadcast);

            System.out.println("\nUsable IP Addresses:");
            for (int j = firstHost; j <= lastHost; j++) {
                System.out.println("  " + o1 + "." + o2 + "." + o3 + "." + j);
            }
            System.out.println();
        }

        sc.close();
    }
}

