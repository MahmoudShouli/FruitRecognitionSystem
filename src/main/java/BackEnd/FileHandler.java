/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class FileHandler {
    
    private static final String filePath = "../resources/TrainingSet.txt";

    public static List<List<Integer>> readMatrix() throws IOException {
        // Initialize a list to store lists of integers
        List<List<Integer>> dataLists = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line
            br.readLine();

            // Read the remaining lines
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line by "|", convert each element to integer, and store in a list
                List<Integer> row = Arrays.stream(line.split("\\|"))
                                           .map(Integer::parseInt)
                                           .toList();

                // Append the list to the main list
                dataLists.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

       
       
        
        return dataLists;
    }
    
    public static List<List<Integer>> getInputLists(List<List<Integer>> dataLists) {
        List<List<Integer>> inputList = new ArrayList<>();

        for (List<Integer> dataList : dataLists) {
            // Get the first two elements and add them to the input list
            inputList.add(dataList.subList(0, 2));
        }

        return inputList;
    }
    
        public static List<List<Integer>> getOutputLists(List<List<Integer>> dataLists) {
        List<List<Integer>> outputList = new ArrayList<>();

        for (List<Integer> dataList : dataLists) {
            // Get the last three elements and add them to the output list
            outputList.add(dataList.subList(2, dataList.size()));
        }

        return outputList;
    }
    
    
     public static List<List<Double>> convertIntToDouble(List<List<Integer>> listOfListsOfIntegers) {
        List<List<Double>> listOfListsOfDoubles = new ArrayList<>();

        for (List<Integer> integers : listOfListsOfIntegers) {
            List<Double> doubles = new ArrayList<>();

            for (Integer integer : integers) {
                doubles.add((double) integer); // Convert each integer to double
            }

            listOfListsOfDoubles.add(doubles);
        }

        return listOfListsOfDoubles;
    }
    
   
   
    

}


    
