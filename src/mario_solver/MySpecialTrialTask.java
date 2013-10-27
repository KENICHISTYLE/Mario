/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mario_solver;

import ch.idsia.ai.agents.Agent;
import ch.idsia.ai.tasks.Task;
import ch.idsia.mario.engine.sprites.Mario;
import ch.idsia.tools.EvaluationInfo;
import ch.idsia.tools.EvaluationOptions;
import ch.idsia.tools.Evaluator;
import java.util.List;

/**
 *
 * @author Nassim
 */
public class MySpecialTrialTask implements Task {
    
    private EvaluationOptions options;
    private List<EvaluationInfo> _results;
    
    public MySpecialTrialTask(EvaluationOptions evaluationOptions) {
        setOptions(evaluationOptions);
    }

    public double[] evaluate(Agent controller) {
        double distanceTravelled = 0;
//        controller.reset();
        options.setAgent(controller);
        Evaluator evaluator = new Evaluator(options);
        _results = evaluator.evaluate();
        for (EvaluationInfo result : _results) {
            //if (result.marioStatus == Mario.STATUS_WIN )
            //    Easy.save(options.getAgent(), options.getAgent().getName() + ".xml");
            distanceTravelled += result.computeDistancePassed();
        }
        distanceTravelled = distanceTravelled / _results.size();
        return new double[]{distanceTravelled};
    }

    public boolean isMarioDeadOrDidHeWin(){          
        
        boolean answer = false; //dead
        
        for (EvaluationInfo result : _results) {
            if (result.marioStatus == Mario.STATUS_WIN )
                 return true;
            if (result.marioStatus == Mario.STATUS_DEAD )
                 return false;
        }
        
        return answer;
    }
    
    public void setOptions(EvaluationOptions options) {
        this.options = options;
    }

    public EvaluationOptions getOptions() {
        return options;
    }

    
}
