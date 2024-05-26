/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd;

/**
 *
 * @author mahmo
 */
public class Connection {
     
    
    private double weight;
    
    private final Neuron from;
    
    private final Neuron to;
    
    private double deltaWeight;

    public Connection(Neuron from, Neuron to) {
        this.from = from;
        this.from.addOutgoingConnection(this);
        this.to = to;
        this.to.addIncomingConnection(this);
        
    }
    
    
    // kind of works like the constructor above.
    public static Connection connect(Neuron from, Neuron to){
        return new Connection(from,to); 
    }
    

    public double getWeight() {
        return weight;
    }

    public double getDeltaWeight() {
        return deltaWeight;
    }
    
    

    public Neuron getFrom() {
        return from;
    }

    public Neuron getTo() {
        return to;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDeltaWeight(double deltaWeight) {
        this.deltaWeight = deltaWeight;
    }
    
    
    
    
    
}
