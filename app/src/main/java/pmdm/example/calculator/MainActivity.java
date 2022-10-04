package pmdm.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTV;
    TextView solutionTv;

    MaterialButton buttonC;
    MaterialButton buttonBrackOpen;
    MaterialButton buttonBrackClose;
    MaterialButton buttonDivide;
    MaterialButton button7;
    MaterialButton button8;
    MaterialButton button9;
    MaterialButton buttonMultiply;
    MaterialButton button4;
    MaterialButton button5;
    MaterialButton button6;
    MaterialButton buttonPlus;
    MaterialButton button1;
    MaterialButton button2;
    MaterialButton button3;
    MaterialButton buttonMinus;
    MaterialButton buttonAC;
    MaterialButton button0;
    MaterialButton buttonDot;
    MaterialButton buttonEquals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTV = findViewById(R.id.result_tv);
        solutionTv =findViewById(R.id.solution_tv);

        assignId(buttonC,R.id.button_c);
        assignId(buttonBrackOpen,R.id.button_open_bracket);
        assignId(buttonBrackClose,R.id.button_close_bracket);
        assignId(buttonDivide,R.id.button_divide);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(buttonPlus,R.id.button_sum);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(buttonMinus,R.id.button_minus);
        assignId(buttonAC,R.id.button_AC);
        assignId(button0,R.id.button_0);
        assignId(buttonDot,R.id.button_dot);
        assignId(buttonEquals,R.id.button_result);




    }

    //Method for
    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText= button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("AC")){
            solutionTv.setText("");
            resultTV.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solutionTv.setText(resultTV.getText());
            return;
        }
        if (buttonText.equals("C")){            
             if (solutionTv.length()==1 || resultTV.length()==1){
                solutionTv.setText("");
                resultTV.setText("0");
                return;
            }else{
                dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
            }
            
        }else{
            dataToCalculate+= buttonText;
        }


        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Error")){
            resultTV.setText(finalResult);
        }

    }

    String getResult (String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return  finalResult;
        }catch (Exception e){
            return "Error";
        }
    }
}
