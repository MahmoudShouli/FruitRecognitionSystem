/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author mahmo
 */



public class MainFunctions {
    
    
    
    private static NeuralNetwork neuralNetwork;
    
    
    
    public static Double Train(double learningRate, int maxEpochs, int hiddenCount, double MSE, String hiddenAF, List<List<Integer>> inputMatrix, List<List<Double>> desiredOutputMatrix) throws IOException {
        
        // Step 1: Initilization
        
        neuralNetwork = new NeuralNetwork(2, hiddenCount, 3);
        
        
        Double counter = 0.0;
        int epochs = 0;
        Double mse = 0.0;
         
         
        Double se1,se2,se3;
        se1 = se2 = se3 = 0.0;
         
        Double mse1,mse2,mse3;
        mse1 = mse2 = mse3 = 0.0;
         
         
         
         
        List<Double> actualOutputs = new ArrayList<>();
        List<Double> outputErrors = new ArrayList<>();
        List<Double> outputSquaredErrors = new ArrayList<>();
         
        while(maxEpochs != epochs && mse != MSE){ 
            for(int i=0; i < inputMatrix.size(); i++){ //inputMatrix has the table of data entries.
         
            // Step 2: Activation
        
                actualOutputs = neuralNetwork.calculateOutputs(inputMatrix.get(i), hiddenAF);
                
                
                
                // If last epoch, compute accuracy
                
                if(epochs + 1 == maxEpochs){
                   
                    if(actualOutputs.equals(desiredOutputMatrix.get(i)))
                        counter += 1;
                    
                }
                
                  
            
                outputErrors = neuralNetwork.calculateOutputErrors(desiredOutputMatrix.get(i), actualOutputs);
                // might print them 
                
                outputSquaredErrors = neuralNetwork.calculateSquaredError(desiredOutputMatrix.get(i), actualOutputs);
                
                se1 += outputSquaredErrors.get(0);
                se2 += outputSquaredErrors.get(1);
                se3 += outputSquaredErrors.get(2);
                
            // Step 3: Weight Training    
             
                neuralNetwork.calculateErrorGradient(outputErrors,actualOutputs, hiddenAF);
            
                neuralNetwork.calculateWeightsCorrections(learningRate);
            
                neuralNetwork.updateWeights();
                
            
            
            // Step 4: Repeat  (go to next iteraton)  
            
            
            }
            
            mse1 = se1/inputMatrix.size();
            mse2 = se2/inputMatrix.size();
            mse3 = se3/inputMatrix.size();
            
            mse = (mse1 + mse2 + mse3)/3; 
            
            
            
            epochs++; 
        
        }
        
        
        Double accuracy = counter / inputMatrix.size();
        
        return accuracy;
        
        
    }
    
    
    
    
    public static String Test(int color, int sweetness,String hiddenAF){
        
            List<Integer> inputs = new ArrayList<>();
            inputs.add(color); inputs.add(sweetness);
            
            List<Double> actualOutputs = neuralNetwork.calculateOutputs(inputs, hiddenAF);
            
        
            Double max = Collections.max(actualOutputs);
            
            
            int fruit = actualOutputs.indexOf(max);
            
            
            
            switch (fruit){
                
                case 0: return "Apple";
                case 1: return "Orange";
                case 2: return "Banana";
                default: return null;
                
            } 
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    

}
