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
    
    public int FEATCHURES = 7;
    public int[] _state;
    private float _PrevMarioPos = 0;
    private float[] _marioPos;
    
        
    State(){        
        _state = new int[FEATCHURES];         
    }
    
    State(Environment obs){
        _state = new int[FEATCHURES];
        updateState(obs);
    }
    
    
    
    public int[] updateState(Environment obs){
        
       byte[][] eObs = obs.getEnemiesObservation();
       
        _state[0] = obs.mayMarioJump()?1:10;
        _state[1] = obs.isMarioOnGround()?1:5;
        _state[2] = obs.getMarioMode();
        _state[3] = obs.getKillsTotal();
        _state[4] = (eObs[11][10] == 0)?1:2;
        _state[5] = (eObs[11][12] == 0)?1:2;       
        
        _marioPos = obs.getMarioFloatPos();      
        
        if(_marioPos[0] > _PrevMarioPos)
            _state[6] = 12;
        else
            _state[6] = 10;
        
        _PrevMarioPos = _marioPos[0];
        return _state;
    }
    
    public int[] getState(){
        return _state;
    }    
    
    
}
