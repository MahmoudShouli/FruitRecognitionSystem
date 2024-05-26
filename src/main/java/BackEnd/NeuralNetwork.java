/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * @author mahmo
 */
public class NeuralNetwork {
    
    private static final Random random = new Random();
    
    private final List<Neuron> inputLayer = new ArrayList<>();
    private final List<Neuron> hiddenLayer = new ArrayList<>();
    private final List<Neuron> outputLayer = new ArrayList<>();
    
    private TrainingFunctions tf = new TrainingFunctions();
    private ActivationFunctions af = new ActivationFunctions();
   
    
    public NeuralNetwork(int inputCount, int hiddenCount, int outputCount){
        
        // creating the Input Layer
        for(int i =1; i <= inputCount; i++){
            inputLayer.add(new Neuron("input" + i,0));
        }
        
        
        
        double minValue = -2.4/inputCount;
        double maxValue = 2.4/inputCount;
        
        
        // creating the Hidden Layer
        for(int i =1; i <= hiddenCount; i++){
            Neuron hidden = new Neuron("hidden" + i,minValue + (maxValue - minValue) * random.nextDouble());
            // connect each input neuron to each hidden neuron
            for (Neuron input : inputLayer){
                Connection connection = Connection.connect(input,hidden);
                connection.setWeight(minValue + (maxValue - minValue) * random.nextDouble()); // intiliaize random weights
            }
            
            hiddenLayer.add(hidden);
        }
        
        minValue = -2.4/hiddenCount;
        maxValue = 2.4/hiddenCount;
        
        
        
        // creating the Output Layer
        for(int i =1; i <= outputCount; i++){
            Neuron output = new Neuron("output" + i,minValue + (maxValue - minValue) * random.nextDouble());
            // connect each hidden neuron to each output neuron
            for (Neuron hidden : hiddenLayer){
                Connection connection = Connection.connect(hidden,output);
                connection.setWeight(minValue + (maxValue - minValue) * random.nextDouble()); // intiliaize random weights
            }
            
            outputLayer.add(output);
        }
        
    }
    
    
    
    
    // this function returns the Actual Outputs.
    public  List<Double> calculateOutputs(List<Integer> inputValues,String hiddenAF) {

        // assigning the input data to the input layer
        for(int i=0; i < inputValues.size(); i++){
            inputLayer.get(i).setOutputValue(inputValues.get(i));
        }

        // computing hidden layer values
        for(Neuron hiddenNeuron: hiddenLayer ){
            hiddenNeuron.calculateOutputValueForHiddenNeurons(hiddenAF); // this is Yj(p)
        }


        // computing output layer values
        List<Double> resultValues = new ArrayList<>();
        for(Neuron outputNeuron: outputLayer){
            outputNeuron.calculateOutputValueForOutputNeurons();// this is Yk(p)
            resultValues.add(outputNeuron.getOutputValue());
        }
        
        
        return af.softmax(resultValues); // contains softmaxed output scores.

        
    }   
    
    
   
    
    
    // this function is to calculate the errors for the output neurons (error = desired - actual)
    public List<Double> calculateOutputErrors(List<Double> desiredOutputs, List<Double> actualOutputs){
        List<Double> outputErrors = new ArrayList<>();
        
        for(int i=0; i < desiredOutputs.size(); i++){
            
            outputErrors.add(desiredOutputs.get(i) - actualOutputs.get(i));
            
          
        }
        
        return outputErrors;
        
        
    }
    
    
    
    public List<Double> calculateSquaredError(List<Double> desiredOutputs, List<Double> actualOutputs){
        List<Double> outputSquaredErrors = new ArrayList<>();
        
        for(int i=0; i < desiredOutputs.size(); i++){
            
            outputSquaredErrors.add( Math.pow(desiredOutputs.get(i) - actualOutputs.get(i),2));
            
          
        }
        
        return outputSquaredErrors;
        
        
    }

  
    
    
    // this function calculates the error gradient for the output neurons then for the hidden neurons
    public void calculateErrorGradient(List<Double> outputErrors,List<Double> softmaxedScores, String hiddenAF ){
        
        // Error Gradient = TrainingFunction(outputvalue) * outputError
        
        List<Double> derivatives = tf.derSoftmax(softmaxedScores);
        
        int i=0;
        for(Neuron outputNeuron : outputLayer) {
            outputNeuron.setErrorGradient(derivatives.get(i) * outputErrors.get(i));
            i++;
        }
        
        
        
        // Error Gradient = TraningFunction(outputvalue) * Σ(wieght*errorgradient)
        int total;
        for(Neuron hiddenNeuron: hiddenLayer ) {
            total =0;
            for(Connection connection: hiddenNeuron.getOutgoingConnections()){
                total += connection.getWeight() * connection.getTo().getErrorGradient();
            }
           hiddenNeuron.setErrorGradient(tf.trainFunc(hiddenAF, hiddenNeuron.getOutputValue()) * total);
                 
        }
        
        
        
    }
    
    // this function calculates the weights & biases corrections for the output-hidden weights group then hidden-input weights group
    public void calculateWeightsCorrections(double learningRate){
        
        // ΔW = α * outputValue * gradientError
        for(Neuron outputNeuron : outputLayer) {
            for(Connection connection : outputNeuron.getIncomingConnections()){
                connection.setDeltaWeight(learningRate * connection.getFrom().getOutputValue() * outputNeuron.getErrorGradient());
                outputNeuron.setDeltaBias(learningRate * outputNeuron.getThreshold() * outputNeuron.getErrorGradient());
            }
        }
        
        
        for(Neuron hiddenNeuron : hiddenLayer) {
            for(Connection connection : hiddenNeuron.getIncomingConnections()){
                connection.setDeltaWeight(learningRate * connection.getFrom().getOutputValue() * hiddenNeuron.getErrorGradient());
                hiddenNeuron.setDeltaBias(learningRate * hiddenNeuron.getThreshold() * hiddenNeuron.getErrorGradient());
            }
        }
        
    }
    
    // this function updates the weights and biases
    public void updateWeights(){
        
        for(Neuron outputNeuron : outputLayer) {
            for(Connection connection : outputNeuron.getIncomingConnections()){
                connection.setWeight(connection.getWeight() + connection.getDeltaWeight());
                outputNeuron.setBias( outputNeuron.getBias() + outputNeuron.getDeltaBias());
            }
        }
        
        for(Neuron hiddenNeuron : hiddenLayer) {
            for(Connection connection : hiddenNeuron.getIncomingConnections()){
                connection.setWeight(connection.getWeight() + connection.getDeltaWeight());
                hiddenNeuron.setBias( hiddenNeuron.getBias() + hiddenNeuron.getDeltaBias());
            }
        }
        
        
        
    }
    
    
   
    
    
    
    
}
