import java.util.ArrayList;
import java.util.Scanner;

public class add {
    static class Node {
        int data;
        int index;

        public Node(int d, int i) {
            data = d;
            index = i;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int smallest_index = 0;
        ArrayList<Node> linked_list = new ArrayList<>();
        System.out.println("Enter the values or type 'n' to stop input");

        while (true) {
            String inp = input.nextLine();
            if (inp.equalsIgnoreCase("n")) {
                break; 
            } else {
                int val = Integer.parseInt(inp);
                linked_list.add(new Node(val, -1));
            }
        }

        // get correct smallest_index and corrected linked list
        smallest_index = refrences(linked_list, smallest_index);

        display(linked_list, linked_list.size());
        sorted_arr(linked_list, linked_list.size(), smallest_index);

        System.out.println("Do You Want to Add any number to list? y/n");
        String add = input.nextLine();

        if (add.equalsIgnoreCase("y")) {
        System.out.println("Enter the values or type 'n' to stop input");
        while (true) {
            String inp = input.nextLine();
            if (inp.equalsIgnoreCase("n")) {
                break; 
            } else {
                int val = Integer.parseInt(inp);
                linked_list.add(new Node(val, -1));
            }
        }
            smallest_index = refrences(linked_list, smallest_index);
            display(linked_list, linked_list.size());
             sorted_arr(linked_list, linked_list.size(), smallest_index);
        } 
        else {
            System.out.println("Thank you !");
        }
        input.close();
    }

    public static void display(ArrayList<Node> linked_list, int size) {
        System.out.println("Given Array and refrenced indexes are:");
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print("Data: " + linked_list.get(i).data + ", Index: " + linked_list.get(i).index +"   ");
        }
        System.out.println();
    }

    public static void sorted_arr(ArrayList<Node> linked_list, int size, int smallest_index) {
        System.out.println("Sorted Array:");
        int current = smallest_index;

        while (current != -1) {
            System.out.println(linked_list.get(current).data);
            current = linked_list.get(current).index; 
        }
    }

    public static int refrences(ArrayList<Node> linked_list,int smallest_index){
        for (int i = 1; i < linked_list.size(); i++) {
            if (linked_list.get(i).data < linked_list.get(smallest_index).data) {
                smallest_index = i;
            }
        }

        int second_smal = smallest_index;
        boolean[] Arr = new boolean[linked_list.size()];
        Arr[smallest_index] = true;

        for (int i = 1; i < linked_list.size(); i++) {
            int min = 1000000;
            int next_index = -1;
            for (int j = 0; j < linked_list.size(); j++) {
                if (!Arr[j]) {
                    if (linked_list.get(j).data < min) {
                        min = linked_list.get(j).data;
                        next_index = j;
                    }
                }
            }
            linked_list.get(second_smal).index = next_index;
            second_smal = next_index;
            Arr[second_smal] = true;
        }
        return smallest_index;
   }
}

