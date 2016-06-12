package com.csclab.hc.socketsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextUtils;

import java.io.OutputStream;
import java.net.Socket;
import android.util.Log;

public class MainActivity extends Activity implements android.view.View.OnClickListener{
    /** Init Variable for IP page **/
    EditText inputIP;
    Button ipSend;
    String ipAdd = "";
    String oper = "";
    EditText inputNumTxt1;
    EditText inputNumTxt2;
    TextView textResult;
    Button Return;
    Button btnAdd;
    Button btnSub;
    Button btnMult;
    Button btnDiv;
    Button btnSqu;
    Button btnMod;
    String strToSend;




    /** Init Variable for Page 1 **/
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ip_page);
        inputIP = (EditText)findViewById(R.id.edIP);
        ipSend = (Button)findViewById(R.id.ipButton);

        ipSend.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Func() for setup page 1 **/
                ipAdd = inputIP.getText().toString();
                jumpToCalLayout();
            }
        });
    }

    /** Function for page 1 setup */


    public void jumpToCalLayout() {
        //TODO: Change layout to activity_main
        // HINT: setContentView()
        setContentView(R.layout.cal_main);
        //TODO: Find and bind all elements(4 buttons 2 EditTexts)
        // inputNumTxt1, inputNumTxt2
        // btnAdd, btnSub, btnMult, btnDiv
        inputNumTxt1 = (EditText) findViewById(R.id.etNum1);
        inputNumTxt2 = (EditText) findViewById(R.id.etNum2);


        btnAdd = (Button)this.findViewById(R.id.btnAdd);
        btnSub = (Button)this.findViewById(R.id.btnSub);
        btnMult = (Button)this.findViewById(R.id.btnMult);
        btnDiv = (Button)this.findViewById(R.id.btnDiv);
        btnSqu = (Button)this.findViewById(R.id.btnSqu);
        btnMod = (Button)this.findViewById(R.id.btnMod);



        //TODO: Set 4 buttons' listener
        // HINT: myButton.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnSqu.setOnClickListener(this);
        btnMod.setOnClickListener(this);


    }


    /** Function for onclick() implement */
    @Override
    public void onClick(View v) {
        //Log.d("Client","Client Send");

        float num1 = 0; // Store input num 1
        float num2 = 0; // Store input num 2
        float result = 0; // Store result after calculating

        // check if the fields are empty
        if (TextUtils.isEmpty(inputNumTxt1.getText().toString())
                || TextUtils.isEmpty(inputNumTxt2.getText().toString())) {
            return;
        }

        // read EditText and fill variables with numbers
        num1 = Float.parseFloat(inputNumTxt1.getText().toString());
        num2 = Float.parseFloat(inputNumTxt2.getText().toString());

        // check if the fields are empty
        // jumpToResultLayout(result+"0");



        switch (v.getId()) {
            case R.id.btnAdd:
                oper = "+";
                result=num1+num2;

                break;
            case R.id.btnSub:
                oper = "-";
                result=num1-num2;

                break;
            case R.id.btnMult:
                oper = "*";
                result=num1*num2;

                break;
            case R.id.btnDiv:
                oper = "/";
                result=num1/num2;
                System.out.print(result);

                break;

            case R.id.btnSqu:
                oper = "^";
                int i;
                result=1;
                for(i=0;i<(int)num2;i++)
                {
                    result=result*num1;
                }
                System.out.print(result);

                break;

            case R.id.btnMod:
                oper = "mod";
                result=num1%num2;
                System.out.print(result);

                break;
            default:
                break;
        }
        Thread t = new thread();
        t.start();
        strToSend="The result from app is "+String.valueOf(num1)+oper+String.valueOf(num2)+"="+result+"0";
        jumpToResultLayout(String.valueOf(num1)+oper+String.valueOf(num2)+"="+result+"0");
    }

    public void jumpToResultLayout(String resultStr) {
        setContentView(R.layout.resultpage);

        //TODO: Bind return_button and textResult form result view
        // HINT: findViewById()
        Return = (Button) this.findViewById(R.id.button);
        textResult = (TextView) findViewById(R.id.editText);


        // HINT: Remember to change type

        if (textResult != null) {
            textResult.setText(resultStr);
            //TODO: Set the result text
        }

        if (Return != null) {
            //TODO: prepare button listener for return button
            // HINT:
            Return.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    jumpToCalLayout();
                    // Something to do..
                }
            });
        }
    }

    class thread extends Thread{
        public void run(){
            try{
                System.out.println("Client: Waiting to connect...");
                int serverPort = 2000;

                // Create socket connect server
                Socket socket = new Socket(ipAdd, serverPort);
                System.out.println("Connected!");

                // Create stream communicate with server
                OutputStream out = socket.getOutputStream();
               // String strToSend = "Hi I'm client";

                byte[] sendStrByte = new byte[1024];
                System.arraycopy(strToSend.getBytes(), 0, sendStrByte, 0, strToSend.length());
                out.write(sendStrByte);

            }catch (Exception e){
                System.out.println("Error" + e.getMessage());
            }
        }
    }
}
