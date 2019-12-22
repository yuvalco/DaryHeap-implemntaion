package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//       gets data from user about d array value
        System.out.println("please enter d value");
        Scanner obj = new Scanner(System.in);
        int dValue = obj.nextInt();

        DaryHeap heap = new DaryHeap(dValue);

        Scanner menu = new Scanner(System.in);
        showMenu();

        int value = 0;
        while ((value = menu.nextInt()) != -1) {
            switch (value) {
                case 1:
                    int max = heap.extractMax();
                    System.out.println(max + " Extracted from the heap");
                    showMenu();
                    break;
                case 2:
                    System.out.println("Enter number to be inserted to the heap.");
                    heap.insert(menu.nextInt());
                    System.out.println("Inserted into the heap");
                    showMenu();
                    break;
                case 3:
                    System.out.println("Enter key number, and than value");
                    heap.increaseKey(menu.nextInt(),menu.nextInt());
                    showMenu();
                    break;
                case 4:
                    heap.print();
                    showMenu();
                    break;
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n~~Menu~~");
        System.out.println("Select one of the option:");
        System.out.println("1 - Extract max");
        System.out.println("2 - Insert");
        System.out.println("3 - Increase key");
        System.out.println("4 - Print heap");
    }
}
