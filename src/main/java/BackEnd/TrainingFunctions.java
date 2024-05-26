/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mahmo
 */
public class TrainingFunctions {
    
   
    
    // Derivative of Sigmoid
    public  double derSigmoid(double value){
        return (value * (1 - value));
    }
   
     // Derivative of Tanh
    public  double derTanh(double value){
        return (1 - Math.pow(value, 2));
    }
    
    
     // Derivative of ReLU
    public  double derReLU(double value){
        if(value < 0) return 0;
        else return 1;
    }
    
    public List<Double> derSoftmax(List<Double> softmaxedScores){
        List<Double> derivatives = new ArrayList<>();
        
        for (int i = 0; i < softmaxedScores.size(); i++) {
            derivatives.add(softmaxedScores.get(i)*(1-softmaxedScores.get(i)));
    }
        
        
        return derivatives;
        
    }
    
    
    public  double trainFunc(String TF, double value){
        if(TF.equalsIgnoreCase("sigmoid"))
            return derSigmoid(value);
        else if(TF.equalsIgnoreCase("tanh"))
            return derTanh(value);
        else if(TF.equalsIgnoreCase("relu"))
            return derReLU(value);
        
        return 0;
    
    }
}

