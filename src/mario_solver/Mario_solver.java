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
        Mario_agent controller = new Mario_agent();
        AgentsPool.addAgent(controller);
        // loading the game
        EvaluationOptions options = new CmdLineOptions(new String[0]);
        options.setAgent(controller);
        Task task = new ProgressTask(options);
        options.setMaxFPS(false);
        options.setVisualization(true);
        options.setNumberOfTrials(2);
        options.setMatlabFileName("");
        options.setLevelRandSeed((int) (Math.random () * Integer.MAX_VALUE));
        options.setLevelDifficulty(4);
        task.setOptions(options);

        System.out.println ("Score: " + task.evaluate (controller)[0]);
    }
}
