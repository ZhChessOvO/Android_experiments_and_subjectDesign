package bjfu.it.liyu.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Math.sqrt;

public class CalculatorActivity extends AppCompatActivity {

    TextView textView;
    private StringBuilder number;
    private String ckey="NULL";
    private double now=0;
    private double pre=0;
    private boolean ispoint=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        textView=findViewById(R.id.showwww);
        number = new StringBuilder(String.valueOf('0'));
    }
    public  void onClickCE(View view) {
        calculator("CE");
        done();
    }
    public  void onClickDiv(View view) {
        calculator("/");
    }
    public  void onClickMultiply(View view) {
        calculator("*");
    }
    public void onClickSub(View view) {
        calculator("-");
    }
    public void onClickAdd(View view) {
        calculator("+");
    }
    public void onClickGen(View view) {
        calculator("gen");
        done();
    }

    public void onClickEqual(View view) {
        done();
    }
    public void onClickC(View view) {
        calculator("C");
        done();
    }
    public void onClickNine(View view) {
        pressNum(9);
    }
    public void onClickEight(View view) {
        pressNum(8);
    }
    public void onClickSeven(View view) {
        pressNum(7);
    }
    public void onClickSix(View view) {
        pressNum(6);
    }
    public void onClickFive(View view) {
        pressNum(5);
    }
    public void onClickFour(View view) {
        pressNum(4);
    }
    public void onClickThree(View view) {
        pressNum(3);
    }
    public void onClickTwo(View view) {
        pressNum(2);
    }
    public void onClickOne(View view) {
        pressNum(1);
    }
    public void onClickZero(View view) {
        pressNum(0);
    }
    public void onClickPoint(View view) {
        pressNum(-1);
    }
    public void pressNum(int num) {
        if(num==-1&&ispoint)
        {
            //warn and do nothing
            Toast toast = Toast.makeText(CalculatorActivity.this,"不能有两个小数点",Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(num==-1)
        {
            ispoint=true;
            number.append('.');
            textView.setText(number.toString());
            return ;
        }
        else
        {
            if(number.toString().startsWith("0")&&!ispoint) number = new StringBuilder(String.valueOf(num));
            else number.append(num);
            now=Double.valueOf(number.toString());
            textView.setText(number.toString());
        }

    }
    public void calculator(String key)
    {
        ckey=key;
        pre=now;
        ispoint=false;
        number = new StringBuilder(String.valueOf('0'));
    }
    public void done()
    {
        boolean exception=false;
        switch (ckey)
        {
            case "CE":
                number = new StringBuilder(String.valueOf('0'));
                textView.setText(number.toString());
                break;
            case "+":
                now=now+pre;
                pre=0;
                number = new StringBuilder((String.valueOf(now)));
                textView.setText(number.toString());
                break;
            case "-":
                now=pre-now;
                pre=0;
                number = new StringBuilder((String.valueOf(now)));
                textView.setText(number.toString());
                break;
            case "*":
                now=now*pre;
                pre=0;
                number = new StringBuilder((String.valueOf(now)));
                textView.setText(number.toString());
                break;
            case "/":
                if(now==0) {
                    //warn
                    Toast toast = Toast.makeText(CalculatorActivity.this,"除数不能为0",Toast.LENGTH_SHORT);
                    toast.show();
                    exception=true;
                }
                else {
                    now = pre / now;
                    pre = 0;
                    number = new StringBuilder((String.valueOf(now)));
                    textView.setText(number.toString());
                }
                break;
            case "C":
                pre=0;now=0;
                number = new StringBuilder(String.valueOf('0'));
                textView.setText(number.toString());
                break;
            case "gen":
                if(now<0)
                {
                    //warn
                    Toast toast = Toast.makeText(CalculatorActivity.this,"根号下不能为负数",Toast.LENGTH_SHORT);
                    toast.show();
                    exception=true;
                }
                else {
                    now = sqrt(now);
                    number = new StringBuilder((String.valueOf(now)));
                    textView.setText(number.toString());
                }
                break;
            default: break;
        }
        if(number.toString().contains(".")) ispoint=true;
        if(!exception) ckey="NULL";
    }
}