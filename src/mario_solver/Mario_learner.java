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
public class Mario_learner implements ch.idsia.ai.agents.Agent {

    private Qlearn learninAlgo;
    protected String Name = "StudentMario";
    private boolean first = true;
    
    public void reset() {
        learninAlgo = new Qlearn();
        first = true;
    }

    @Override
    public boolean[] getAction(Environment observation) {
        
       if(first){
           learninAlgo.init(observation);
           first = false;  
           return learninAlgo.initAction();
       }
              
       boolean[] ac = learninAlgo.learn(observation);
       
       return ac; 
    }

    public void save(){
        learninAlgo.saveLearning();
    }
    
    @Override
    public AGENT_TYPE getType() {
        return AGENT_TYPE.AI;
    }
 
    @Override
    public String getName() {        
        return Name;   
    }

   
    @Override
    public void setName(String Name) { 
        this.Name = Name;    
    }
    
}
