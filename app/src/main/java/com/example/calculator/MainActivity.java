package com.example.calculator;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView resultText,solutionText;
    MaterialButton materialButton_divide,Button_c,openBracket,close_bracket,Button_7,Button_8,Button_9,Button_6,Button_5,Button_4,Button_3,Button_2,Button_1,Button_0,materialButton_dot,
    materialButton_mul,materialButton_minus,materialButton_equal,materialButton_ac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText = findViewById(R.id.result_Text);
        solutionText = findViewById(R.id.solution_Text);

        assignId(materialButton_divide,R.id.materialButton_divide);
        assignId(Button_c,R.id.Button_c);
        assignId(openBracket,R.id.openBracket);
        assignId(close_bracket,R.id.close_bracket);
        assignId(Button_7,R.id.Button_7);
        assignId(Button_8,R.id.Button_8);
        assignId(Button_9,R.id.Button_9);
        assignId(Button_6,R.id.Button_6);
        assignId(Button_5,R.id.Button_5);
        assignId(Button_4,R.id.Button_4);
        assignId(Button_3,R.id.Button_3);
        assignId(Button_2,R.id.Button_2);
        assignId(Button_1,R.id.Button_1);
        assignId(Button_0,R.id.Button_0);
        assignId(materialButton_dot,R.id.materialButton_dot);
        assignId(materialButton_mul,R.id.materialButton_mul);
        assignId(materialButton_minus,R.id.materialButton_minus);
        assignId(materialButton_equal,R.id.materialButton_equal);
        assignId(materialButton_ac,R.id.materialButton_ac);
    }
    void assignId(MaterialButton btn,int id){
        btn =findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
          MaterialButton button = (MaterialButton) view;
          String buttonText = button.getText().toString();
          String dataToCalculate = solutionText.getText().toString();

          if (buttonText.equals("AC")){
              solutionText.setText("");
              resultText.setText("0");
              return;
          }
          if (buttonText.equals("=")){
              solutionText.setText(resultText.getText());
              return;
          }
        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
            return;
        }else {
            dataToCalculate = dataToCalculate+buttonText;
        }

        solutionText.setText(dataToCalculate);
        String finalResult  =getResult(dataToCalculate);
        if(!finalResult.equals("Err")){
            resultText.setText(finalResult);
        }
    }
    String getResult(String data) {

       try{

           Context context = Context.enter();
           context.setOptimizationLevel(-1);
           Scriptable scriptable = context.initStandardObjects();
           String finalResult  = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
           if(finalResult.endsWith(".0")){
               finalResult = finalResult.replace(".0","");
           }

           return finalResult;
       }catch (Exception e){
           return "Err";
       }
    }
}