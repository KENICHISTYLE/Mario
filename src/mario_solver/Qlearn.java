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
public class Qlearn {
    
    private Actions _actions;
    private State _prevState,_currentState;
    private int _prevActionId;
    private final String _saveFile;
    private float _learningRate = 0.1f;
    
    Qlearn(){        
        this._saveFile = "SaveActions.txt";
        _prevState = new State();
        _currentState = new State();
        
         _actions = new Actions(_currentState.FEATCHURES);
         _actions.loadLearningActions(_saveFile);
         
        _prevActionId = 0;
    }
    
    Qlearn(Environment obs){
        this._saveFile = "SaveActions.txt";
        _prevState = new State(obs);
        _currentState = new State(obs);
        
         _actions = new Actions(_currentState.FEATCHURES);
         _actions.loadLearningActions(_saveFile);
         
        _prevActionId = 0;
    }
    
    public void init(Environment obs){
        _prevState.updateState(obs);
        _currentState.updateState(obs);
    }
    
    public boolean[] initAction(){
        return _actions.doActionById(_actions.RIGHT);
    }
    
    public boolean[] learn(Environment obs){
        
        _currentState.updateState(obs);
        
        int actionId = (getBestAction(_actions)).ActionId;
        boolean[] returnAction = _actions.doActionById(actionId);
        
        rewardAction(_prevState, _actions, _prevActionId);
        
        System.out.println(_actions.getActionName(actionId));
        _prevActionId = actionId;
        _prevState = _currentState;
        
        return returnAction;
    }
    
    public void saveLearning(){
        _actions.saveLearningActions(_saveFile);
    }
    
    public LearningInfo getBestAction(Actions actions){
        
        int score = 0;
        int actionId = 0;
        int[] state = _currentState._state;
        
        //for each action
        for(int i = 0; i< actions.ACTION_NUMBER; i++){
            
            int actionScore = 0;
            int[] curentAction = actions.getAction(i);
            int[] tempState;
            
            // scalar product
            for(int j = 0; j <_currentState.FEATCHURES; j++){
                actionScore += state[j]*curentAction[j];
            }
            
            //choose action
            if(actionScore >= score){
                score = actionScore;
                actionId = i;
            }
        }
        
        System.out.print(score);
        
        
        
        return new LearningInfo(score,actionId);
    }
    
    public void rewardAction(State prev,Actions actions, int actionId){
        
         int[] prevState = prev.getState();
         int[] ac = actions.getAction(actionId);
         int[] state = _currentState._state;
         // punish
         // better if he can jump
         if(state[0] == 2 ){
             ac[0] -= 1;             
         }   
         // better on the ground
         if(state[1] == 2 ){
             ac[1] -= 1;             
         }   
         // do not lose your power
         if(state[2] <  prevState[2]){
             ac[2] -= 1;             
         }   
             
         // move right
         if(state[6] == 10){
             ac[6] -=1; 
         }
         // reward
         // better if can jump
         if(state[0] == 1 ){
             ac[0] += 11;             
         }   
         // better if on the ground
         if(state[1] == 1 ){
             ac[1] += 1;             
         }   
         // do not loose your power
         if(state[2] > prevState[2] ){
             ac[2] += 1;             
         }   
         // it is good to kill enemies
         if(state[3] > prevState[3]){
             ac[1] += 1;             
         }   
        
          // move right
         if(state[6] > 10){
             ac[6] +=1; 
         }
              
    }
}
