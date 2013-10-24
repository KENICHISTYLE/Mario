/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mario_solver;

import ch.idsia.mario.environments.Environment;

/**
 *
 * @author Nassim
 */
public class State {
    
    private int FEATCHURES = 8;
    private int[] _state;
    private float[] _marioPos;
    
    private Actions _actions;
    
    State(){        
        _state = new int[FEATCHURES];
        _actions = new Actions(FEATCHURES);
    }
    
    State(Environment obs){
        _state = new int[FEATCHURES];
        createState(obs);
    }
    
    public int[] createState(Environment obs){
        
       byte[][] compObs = obs.getCompleteObservation();
       
        _state[0] = compObs[11][10];
        _state[1] = compObs[10][10];
        _state[2] = compObs[10][11];
        _state[3] = compObs[10][12];
        _state[4] = compObs[11][12];
        _state[5] = compObs[12][12];
        _state[6] = compObs[12][11];
        _state[7] = compObs[12][12];
        
        _marioPos = obs.getMarioFloatPos();
        
        _actions = new Actions(FEATCHURES);
        
        return _state;
    }
    
    
}
