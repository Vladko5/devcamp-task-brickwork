package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Reads the Area parameters(first lane input)
        int[] areaDimensions = Arrays.stream(scanner.nextLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();


        if (isValid(areaDimensions)){
            int[][] nums = new int[areaDimensions[0]][areaDimensions[1]];

            createFirstLayer(nums, scanner);

            createSecondLayer(nums, areaDimensions);

            printSecondLayer(nums, areaDimensions);
        }

        // If solution doesn't exist
        else {
            System.out.println(-1);
        }
    }

    // Creates the first layer
    public static void createFirstLayer(int[][] nums, Scanner scanner){
        for (int row = 0; row < nums.length; row++) {
            for (int col = 0; col < nums[row].length; col++) {
                nums[row][col] = Integer.parseInt(scanner.next());
            }
        }
    }

    // Creates the second layer
    public static void createSecondLayer(int[][] nums, int[] areaDimensions){
        for (int row = 0; row < nums.length - 1; row++) {
            for (int col = 0; col < nums[row].length - 1; col++) {
                if (areaDimensions[0] > 2){
                    if (row == 1){
                        continue;
                    }
                }else {
                    if (col == 3){
                        continue;
                    }
                }
                if (nums[row][col] == nums[row][col + 1] || nums[row][col] == nums[row + 1][col]
                        || nums[row + 1][col] == nums[row + 1][col + 1]){
                    int num = nums[row][col + 1];
                    nums[row][col + 1] = nums[row + 1][col];
                    nums[row + 1][col] = num;
                }
            }
        }
    }

    // Prints second layer
    public static void printSecondLayer(int[][] nums, int[] areaDimension){
        for (int[] num : nums) {
            for (int col = 0; col < num.length; col++) {
                if (col + 1 == areaDimension[1]) {
                    System.out.print(num[col] + " ");
                    System.out.println();
                    continue;
                }
                if (num[col] == num[col + 1]) {
                    System.out.print(num[col] + " ");
                } else {
                    System.out.print(num[col] + "|");
                }
            }
        }
    }

    // Checks if solution exists
    public static boolean  isValid(int[] areaDimensions){
        return areaDimensions[0] % 2 == 0 && areaDimensions[1] % 2 == 0
                && areaDimensions[0] < 100 && areaDimensions[1] < 100;
    }
}
