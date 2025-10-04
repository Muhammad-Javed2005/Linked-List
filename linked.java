import java.util.ArrayList;
import java.util.Scanner;

public class linked {
    // Simple Node class
    static class Node {
        int data;   // value
        int next;   // index of next smallest element (-1 if none)

        Node(int data) {
            this.data = data;
            this.next = -1; // initially no link
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Node> list = new ArrayList<>();

        // Input first list
        System.out.println("Enter numbers (type 'n' to stop):");
        while (true) {
            String inp = sc.nextLine();
            if (inp.equalsIgnoreCase("n")) break;
            list.add(new Node(Integer.parseInt(inp)));
        }

        // Build references and display
        int smallest = buildLinks(list);
        display(list);
        printSorted(list, smallest);

        // Option to add more numbers
        System.out.println("\nDo you want to add more numbers? (y/n)");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Enter numbers (type 'n' to stop):");
            while (true) {
                String inp = sc.nextLine();
                if (inp.equalsIgnoreCase("n")) break;
                list.add(new Node(Integer.parseInt(inp)));
            }
            smallest = buildLinks(list);
            display(list);
            printSorted(list, smallest);
        } else {
            System.out.println("Thank you!");
        }

        sc.close();
    }

    // Show all data with their 'next' links
    static void display(ArrayList<Node> list) {
        System.out.println("\nArray with references:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print("Data: " + list.get(i).data + ", Next: " + list.get(i).next + "   ");
        }
        System.out.println();
    }

    // 🔹 Print sorted numbers by following links
    static void printSorted(ArrayList<Node> list, int smallest) {
        System.out.println("\nSorted Order:");
        int current = smallest;
        while (current != -1) {
            System.out.print(list.get(current).data + " ");
            current = list.get(current).next;
        }
        System.out.println();
    }

    // 🔹 Build "next" links so we can traverse numbers in sorted order
    static int buildLinks(ArrayList<Node> list) {
        if (list.isEmpty()) return -1;

        // Find smallest element index
        int smallest = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).data < list.get(smallest).data) {
                smallest = i;
            }
        }

        // Mark visited elements
        boolean[] used = new boolean[list.size()];
        used[smallest] = true;
        int prev = smallest;

        // Link the rest in sorted order
        for (int step = 1; step < list.size(); step++) {
            int next = -1, min = Integer.MAX_VALUE;
            for (int j = 0; j < list.size(); j++) {
                if (!used[j] && list.get(j).data < min) {
                    min = list.get(j).data;
                    next = j;
                }
            }

            
            list.get(prev).next = next;
            if (next != -1) used[next] = true;
            prev = next;
        }

        return smallest;
    }
}

