/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author mahmo
 */
public class ActivationFunctions {
    
    
    // Sigmoid
    public  double sigmoid(double value) {
        return 1 / (1 + Math.exp(-value));  
    }
    
    // Tanh
    public  double tanh(double value) {
        return (2 / (1 + Math.exp(-2*value))) - 1;
    }
    
    // ReLU
    public  double reLU(double value) {
        if(value < 0) return 0;
        else return value;
        
    }
    
    public List<Double> softmax(List<Double> input) {
    List<Double> result = new ArrayList<>();
    double sum = 0.0;

    // Calculate the exponential of each element in the input vector
    for (Double value : input) {
        result.add(Math.exp(value));
        sum += Math.exp(value);
    }

    // Normalize the values by dividing each by the sum
    for (int i = 0; i < result.size(); i++) {
        result.set(i, result.get(i) / sum);
    }

    
    Double max = Collections.max(result);
            
    int indexOfMax = result.indexOf(max);
    
        
    
    result.set(indexOfMax, 1.0);
    for(int i =0; i < result.size(); i++){
        if(result.get(i)!= 1.0) 
            result.set(i, 0.0);
    }
    
    
    
    
    
    
    return result;
}
    
    public  double activateFunc(String AF, double value){
        if(AF.equalsIgnoreCase("sigmoid"))
            return sigmoid(value);
        else if(AF.equalsIgnoreCase("tanh"))
            return tanh(value);
        else if(AF.equalsIgnoreCase("relu"))
            return reLU(value);
        
        return 0;
    
    }
    
}
