/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mario_solver;

import ch.idsia.mario.engine.sprites.Mario;
import ch.idsia.mario.environments.Environment;

/**
 *
 * @author Nassim
 */
public class Mario_agent implements ch.idsia.ai.agents.Agent  {

    protected boolean Action[] = new boolean[Environment.numberOfButtons];
    protected String Name = "MarioAgent";
    protected boolean PrintOnce = true;
    @Override
    public void reset()
    {
        Action = new boolean[Environment.numberOfButtons];
        Action[Mario.KEY_RIGHT] = true;
        Action[Mario.KEY_SPEED] = true;
    }

    /**
     * print the given matrix on the terminal
     * @param observation a matrix of bytes
     */
    private void showMatrix(byte [][] observation){
        for(byte[] v : observation){
            for(byte e: v){
                System.out.print(" "+e+" ");
            }
            System.out.println();
        }
    }
    
    /**
     * print a line separator on the terminal
     */
    private void Separator(){
        System.out.println("-----------------------------------------------------------------------------");
    }
    
    /**
     * did we see some enemies or obstacles in the map
     * @param obs total observation of the map
     * @return  true if obstacle or enemy on the map
     */
    private boolean ThereIsSomething(byte[][] obs){
       
        for(byte[] v : obs){
            for(byte e: v){
               if(e != 0)
                   return true;
            }            
        }
        return false;
    }
    
    /**
     * analyse the observation matrix
     * @param observation the complete observation of the environment
     */
    private void scanObservation(Environment observation){
        byte[][] CompleteObs = observation.getCompleteObservation();
        byte[][] EnemiesObs = observation.getEnemiesObservation();
        float[] Mpos = observation.getMarioFloatPos();
        
        if(ThereIsSomething(CompleteObs)){
            Separator();
            for(int i = 0; i < Mpos.length; ++i)
                System.out.print(Mpos[i]+" | ");
            System.out.println();
            Separator();
            showMatrix(CompleteObs);
            Separator();
            showMatrix(EnemiesObs);
            Separator();
            PrintOnce = false;
        }
    }
    @Override
    public boolean[] getAction(Environment observation)
    {
        //Action[Mario.KEY_SPEED] = Action[Mario.KEY_JUMP] =  observation.mayMarioJump() || !observation.isMarioOnGround();
        Action[Mario.KEY_RIGHT] = true;
        Action[Mario.KEY_SPEED] = false;
        
        //show helper info
        /*if(PrintOnce){
            scanObservation(observation); 
            if(observation.isMarioOnGround()){
                Action[Mario.KEY_JUMP] = !Action[Mario.KEY_JUMP];
                Action[Mario.KEY_SPEED] = ! Action[Mario.KEY_SPEED];
            }        
        }else{
             Action[Mario.KEY_JUMP] = false;
             Action[Mario.KEY_SPEED] = false;
             Action[Mario.KEY_RIGHT] = false;
        }
        */
        
        if(observation.isMarioOnGround()){
            Action[Mario.KEY_JUMP] = !Action[Mario.KEY_JUMP];
            Action[Mario.KEY_SPEED] = ! Action[Mario.KEY_SPEED];
        }            
             
        
        return Action;
    }

    @Override
    public AGENT_TYPE getType() {
        return AGENT_TYPE.AI;
    }

    @Override
    public String getName() {        return Name;    }

    @Override
    public void setName(String Name) { this.Name = Name;    }
}
