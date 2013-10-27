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
    private int BONUS = 50;
    private int MAX_PERMIT_ADD = 1000;
    
    Qlearn(boolean load){        
        this._saveFile = "SaveActions.txt";
        _prevState = new State();
        _currentState = new State();
        
         _actions = new Actions(_currentState.FEATCHURES);
         if(load)
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
         if(state[0] == 10 ){
             ac[0] = limitAdd(-1, ac[0]);             
         }   
         // better on the ground
         if(state[1] == 5 ){
             ac[1] = limitAdd(-1, ac[1]);             
         }   
         // do not lose your power
         if(state[2] <  prevState[2]){
             ac[2] = limitAdd(-1, ac[2]);             
         }   
         if(state[4] == 2 ){
             ac[4] = limitAdd(-1, ac[4]);             
         }       
         if(state[5] == 2 ){
             ac[5] = limitAdd(-1, ac[5]);             
         }       
         // move right
         if(state[6] == 10){
             ac[6] = limitAdd(-1, ac[6]); 
         }
         // reward
         // better if can jump
         if(state[0] == 1 ){
             ac[0] = limitAdd(1, ac[0]);             
         }   
         // better if on the ground
         if(state[1] == 1 ){
             ac[1] = limitAdd(1, ac[1]);            
         }   
         // do not loose your power
         if(state[2] > prevState[2] ){
             ac[2] = limitAdd(1, ac[2]);             
         }   
         // it is good to kill enemies
         if(state[3] > prevState[3]){
             ac[3] = limitAdd(1, ac[3]);             
         }   
        
         if(state[4] == 1 ){
            // ac[4] += 1;             
         }       
         if(state[5] == 1 ){
             ac[5] = limitAdd(1, ac[5]);;             
         }      
         
          // move right
         if(state[6] > 10){
             ac[6] = limitAdd(1, ac[6]);
         }
              
    }
    
    public void bonusAction(){
        int[] ac = _actions.getAction(_prevActionId);
        for(int i = 0; i < _currentState.FEATCHURES; i++){
            ac[i] = limitAdd(BONUS, ac[i]);
        }
        
        System.out.println(" BONUS : La derniere action est " + _actions.getActionName(_prevActionId));
    }
    
    public void manusAction(){
        int[] ac = _actions.getAction(_prevActionId);
        for(int i = 0; i < _currentState.FEATCHURES; i++){
            ac[i] = limitAdd(-BONUS*2, ac[i]);
        }
        
        System.out.println(" MANUS : La derniere action est " + _actions.getActionName(_prevActionId));
    }
    
    private int limitAdd(int addVal,int currentVal){
        
        int temp = currentVal+addVal;
        
        if( temp > MAX_PERMIT_ADD)
            return MAX_PERMIT_ADD;
        
        if( temp < -MAX_PERMIT_ADD)
            return -MAX_PERMIT_ADD;
        
        return temp;
    }
    
}
