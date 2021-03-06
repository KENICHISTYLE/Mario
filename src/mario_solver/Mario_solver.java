/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mario_solver;

import ch.idsia.ai.agents.Agent;
import ch.idsia.ai.agents.AgentsPool;
import ch.idsia.ai.agents.human.HumanKeyboardAgent;
import ch.idsia.ai.tasks.ProgressTask;
import ch.idsia.ai.tasks.Task;
import ch.idsia.mario.engine.sprites.Mario;
import ch.idsia.tools.CmdLineOptions;
import ch.idsia.tools.EvaluationOptions;

/**
 *
 * @author Nassim
 */
public class Mario_solver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Agent controller = new HumanKeyboardAgent();
        //if (args.length > 0) {
        //    controller = AgentsPool.load (args[0]);
        //    AgentsPool.addAgent(controller);
        //}
        
        //creating the ai agent
        //Mario_agent controller = new Mario_agent();
        Mario_learner controller = new Mario_learner();
        AgentsPool.addAgent(controller);
        // loading the game
        EvaluationOptions options = new CmdLineOptions(new String[0]);
        options.setAgent(controller);
        //Task task = new ProgressTask(options);
        MySpecialTrialTask task = new MySpecialTrialTask(options);
        options.setMaxFPS(true);
        options.setVisualization(true);
        options.setNumberOfTrials(0);
        options.setExitProgramWhenFinished(true);
        //options.setMarioInvulnerable(true);
        options.setLevelRandSeed((int) (Math.random () * Integer.MAX_VALUE));
        options.setLevelDifficulty(0);
        task.setOptions(options);
               
        System.out.println ("Score: " + task.evaluate (controller)[0]);
        System.out.println(" Gonna save ");
        
        controller.save(task.isMarioDeadOrDidHeWin());
        System.out.println(" Saved ");
        System.exit(0);
    }
}
