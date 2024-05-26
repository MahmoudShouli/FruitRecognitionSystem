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
public class Neuron {
    
    private final String name; // input1, input2 or hidden1 hidden2 and so on
   
    private double bias; 
    
    private int threshold;
    
    private double deltaBias; // for correction
    
    private double outputValue;
    
    private double errorGradient;
    
    private ActivationFunctions af = new ActivationFunctions();
    
    // list of Connections for each Neuron
    private List<Connection> incomingConnections = new ArrayList<>();
    private List<Connection> outgoingConnections = new ArrayList<>();
    
    
    
    public Neuron(String name, double bias) {
        this.name = name;
        this.bias = bias;
        this.threshold = -1;
    }
    
    
    public void calculateOutputValueForHiddenNeurons(String AF) {
        double total = -1 * bias;
        for (Connection connection: incomingConnections) {
            total = total + connection.getWeight() * connection.getFrom().getOutputValue(); // total = X
        }
        
        setOutputValue(af.activateFunc(AF,total)); // Ya = F(X)
    }
    
    
    public void calculateOutputValueForOutputNeurons() {
        double total = -1 * bias;
        for (Connection connection: incomingConnections) {
            total = total + connection.getWeight() * connection.getFrom().getOutputValue(); // total = X
        }
        
        setOutputValue(total);
    }
    
    
    
    
    
    

    public String getName() {
        return name;
    }

    public double getBias() {
        return bias;
    }

    public double getOutputValue() {
        return outputValue;
    }

    public int getThreshold() {
        return threshold;
    }

    public double getDeltaBias() {
        return deltaBias;
    }

    public void setDeltaBias(double deltaBias) {
        this.deltaBias = deltaBias;
    }
    
    

    public void setOutputValue(double outputValue) {
        this.outputValue = outputValue;
    }

    public void setErrorGradient(double errorGradient) {
        this.errorGradient = errorGradient;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }
    
    

    public List<Connection> getIncomingConnections() {
        return incomingConnections;
    }

    public List<Connection> getOutgoingConnections() {
        return outgoingConnections;
    }

    public double getErrorGradient() {
        return errorGradient;
    }
    
    
    public void addIncomingConnection(Connection connection){
        incomingConnections.add(connection);
        
    }
    
    public void addOutgoingConnection(Connection connection){
        outgoingConnections.add(connection);
        
    }
    
    
    
}
