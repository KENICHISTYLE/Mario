/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mario_solver;

import ch.idsia.mario.engine.sprites.Mario;
import ch.idsia.mario.environments.Environment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nassim
 */
public class Actions {
    
    protected boolean Action[] = new boolean[Environment.numberOfButtons];
    
    private int[][] _actions;
    public int ACTION_NUMBER = 13;
    private int _features;
    
    public int STOP               = 0;
    public int RIGHT              = 1;
    public int LEFT               = 2;
    public int DOWN               = 3;
    public int JUMP               = 4;
    public int JUMP_SPEED         = 5;
    public int JUMP_RIGHT         = 6;
    public int JUMP_RIGHT_SPEED   = 7;
    public int JUMP_LEFT          = 8;
    public int JUMP_LEFT_SPEED    = 9;
    public int RIGHT_SPEED        = 10;
    public int LEFT_SPEED         = 11;
    public int FIRE_BALL          = 13;
            
            
    Actions(int features){  
        _features = features;
        _actions = new int[ACTION_NUMBER][features];
        
        for(int i = 0; i < ACTION_NUMBER; i++){
            for(int j = 0; j< _features; j++){
                _actions[i][j] = 1;
            }
        }       
        
      //  for(int j = 0; j< _features; j++){
       //     _actions[RIGHT][j] = 2;
       // } 
    }
    
    Actions(int[][] actions){
        _actions = actions.clone();
    }
    
    public void setAction(int actionId, int[] content){
        _actions[actionId] = content.clone();
    }
    
    public int[] getAction(int actionId){
        return _actions[actionId];
    }
    
    /**
     * 
     * @param i actions id 
     * @return the string containing the name of the action 
     */
    public String getActionName(int i){
        
        switch(i){
            case 0: return "stop";
            case 1: return "right";
            case 2: return "left";
            case 3: return "down";
            case 4: return "jump";
            case 5: return "jump speed";
            case 6: return "jump right";
            case 7: return "jump right speed";
            case 8: return "jump left";
            case 9: return "jump left speed";
            case 10: return "right speed";
            case 11: return "left speed";    
            case 12: return "fire ball";
                
            default : return " There is no such action !";
        }
        
    }
    
    /**
     * 
     * @param i action id 
     * @return the action array that contains the selected action
     */
    public boolean[] doActionById(int i){
        
        switch(i){
            case 0: return this.stop();
            case 1: return this.Right();
            case 2: return this.left();
            case 3: return this.down();
            case 4: return this.jump();
            case 5: return this.jumpSpeed();
            case 6: return this.jumpRight();
            case 7: return this.jumpRightSpeed();
            case 8: return this.jumpLeft();
            case 9: return this.jumpLeftSpeed();
            case 10: return this.rightSpeed();
            case 11: return this.leftSpeed();    
            case 12: return this.fireball();
                
            default : return Action;
        }
        
    }
    
    //
     public boolean[] rightSpeed()
    {
        Action[Mario.KEY_RIGHT] = true;
        Action[Mario.KEY_SPEED] = true;
        Action[Mario.KEY_LEFT]  = false;
        Action[Mario.KEY_DOWN]  = false;
        Action[Mario.KEY_JUMP]  = false;
        
        return Action;
    }
    
    //
    public boolean[] Right()
    {
        Action[Mario.KEY_RIGHT] = true;
        Action[Mario.KEY_SPEED] = false;
        Action[Mario.KEY_LEFT] = false;
        Action[Mario.KEY_DOWN] = false;
        Action[Mario.KEY_JUMP]  = false;
        
        return Action;
    }
    
    //
    public boolean[] left()
    {
      
            Action[Mario.KEY_RIGHT] = false;
            Action[Mario.KEY_SPEED] = false;
            Action[Mario.KEY_LEFT]  = true;
            Action[Mario.KEY_DOWN]  = false;
            Action[Mario.KEY_JUMP]  = false;
        
        return Action;
    }
    
     //
    public boolean[] leftSpeed()
    {
      
            Action[Mario.KEY_RIGHT] = false;
            Action[Mario.KEY_SPEED] = true;
            Action[Mario.KEY_LEFT]  = true;
            Action[Mario.KEY_DOWN]  = false;
            Action[Mario.KEY_JUMP]  = false;
        
        return Action;
    }
    
    //
    public boolean[] fireball()
    {
            Action[Mario.KEY_RIGHT] = false;
            Action[Mario.KEY_SPEED] = true;
            Action[Mario.KEY_LEFT]  = false;
            Action[Mario.KEY_DOWN]  = false;
            Action[Mario.KEY_JUMP]  = false;
        
        return Action;
    }
    
    //
    public boolean[] jump()
    {
            Action[Mario.KEY_RIGHT] = false;
            Action[Mario.KEY_SPEED] = false;
            Action[Mario.KEY_LEFT]  = false;
            Action[Mario.KEY_DOWN]  = false;
            Action[Mario.KEY_JUMP]  = true;
        
        return Action;
    }
     
     public boolean[] jumpSpeed()
    {
            Action[Mario.KEY_RIGHT] = false;
            Action[Mario.KEY_SPEED] = true;
            Action[Mario.KEY_LEFT]  = false;
            Action[Mario.KEY_DOWN]  = false;
            Action[Mario.KEY_JUMP]  = true;
        
        return Action;
    }
       
     //
     public boolean[] down()
     {
            Action[Mario.KEY_RIGHT] = false;
            Action[Mario.KEY_SPEED] = false;
            Action[Mario.KEY_LEFT]  = false;
            Action[Mario.KEY_DOWN]  = true;
            Action[Mario.KEY_JUMP]  = false;
        
        return Action;
     }
     
     //
     public boolean[] stop()
     {    	    
            Action[Mario.KEY_RIGHT] = false;
            Action[Mario.KEY_SPEED] = false;
            Action[Mario.KEY_LEFT]  = false;
            Action[Mario.KEY_DOWN]  = false;
            Action[Mario.KEY_JUMP]  = false;
        
        return Action;
     }
    
    
     //
    public boolean[] jumpRight()
    {
            Action[Mario.KEY_RIGHT] = true;
            Action[Mario.KEY_SPEED] = false;
            Action[Mario.KEY_LEFT]  = false;
            Action[Mario.KEY_DOWN]  = false;
            Action[Mario.KEY_JUMP]  = true;
        
        return Action;
    }
     
    //
    public boolean[] jumpRightSpeed()
    {
            Action[Mario.KEY_RIGHT] = true;
            Action[Mario.KEY_SPEED] = true;
            Action[Mario.KEY_LEFT]  = false;
            Action[Mario.KEY_DOWN]  = false;
            Action[Mario.KEY_JUMP]  = true;
        
        return Action;
    }
    
    
      //
    public boolean[] jumpLeft()
    {
            Action[Mario.KEY_RIGHT] = false;
            Action[Mario.KEY_SPEED] = false;
            Action[Mario.KEY_LEFT]  = true;
            Action[Mario.KEY_DOWN]  = false;
            Action[Mario.KEY_JUMP]  = true;
        
        return Action;
    }
     
    //
    public boolean[] jumpLeftSpeed()
    {
            Action[Mario.KEY_RIGHT] = false;
            Action[Mario.KEY_SPEED] = true;
            Action[Mario.KEY_LEFT]  = true;
            Action[Mario.KEY_DOWN]  = false;
            Action[Mario.KEY_JUMP]  = true;
        
        return Action;
    }
    
  public void saveLearningActions(String fileName){
      
        FileWriter fw;
        try {
            fw = new FileWriter (fileName);
        
            BufferedWriter bw = new BufferedWriter (fw);
            PrintWriter out = new PrintWriter (bw);
            int i = 0 ;
            for(int[] ac :_actions){
                for(int f : ac){                    
                    out.write(f+"\t");
                }
                out.write(getActionName(i)+"\n");
                i++;
            }    
            
            out.close();
      
      } catch (IOException ex) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  public boolean loadLearningActions(String fileName){
      
      File f = new File(fileName);
      try (Scanner scanner =  new Scanner(f)){
        
        int i = 0;
          
        while (scanner.hasNextLine() && i < ACTION_NUMBER){         
          String line = scanner.nextLine();
          String parts[] = line.split("\t");
          System.out.println(parts[0]);
          for(int j = 0; j< _features; j++){                    
              try{
                  int temp = Integer.parseInt(parts[j]);
                  _actions[i][j] = temp;
              } catch(NumberFormatException e){
                  System.out.println(" Parse error");
                  return false;
              }                  
          } 
          i++;
        } 
        
        scanner.close();
        return true;
        
     }  catch (FileNotFoundException ex) {
            System.out.println(" Save file not found");
            return false;
        }
  }
    
}
