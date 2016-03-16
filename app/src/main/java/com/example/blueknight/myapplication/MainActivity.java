package com.example.blueknight.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RadioGroup mRadgrpType;
    private RadioButton mRadBiglot, mRadPower;
    private TextView mTxtWanted, mTxtState, mTxtResult;
    private Button mBtnGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mRadgrpType = (RadioGroup)findViewById(R.id.mRadgrpType);
        mRadBiglot = (RadioButton)findViewById(R.id.mRadBiglot);
        mRadPower = (RadioButton)findViewById(R.id.mRadPower);
        mTxtWanted = (TextView)findViewById(R.id.mTxtWanted);
        mTxtState = (TextView)findViewById(R.id.mTxtState);
        mTxtResult = (TextView)findViewById(R.id.mTxtResult);
        mBtnGenerate = (Button)findViewById(R.id.mBtnGenerate);

        setSupportActionBar(toolbar);

        mRadgrpType.setOnCheckedChangeListener(radgrpTypeOnCheckedChange);
        mBtnGenerate.setOnClickListener(mBtnGenerateClick);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }

    private RadioGroup.OnCheckedChangeListener radgrpTypeOnCheckedChange = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId){
                if(checkedId == R.id.mRadBiglot){
                    mTxtState.setText("您選擇了"+getString(R.string.rad_Biglot));
                }
                else{
                    mTxtState.setText("您選擇了"+getString(R.string.rad_PowerLot));
                }
            }
    };

    private View.OnClickListener mBtnGenerateClick =new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if(mTxtWanted.getText().toString().length()!=0) {
                int numberOfPair = Integer.parseInt(mTxtWanted.getText().toString());
                String strToDisplay = new String ();

				//大樂透
                if ((mRadgrpType.getCheckedRadioButtonId() == R.id.mRadBiglot)) {
                    for (int i=0; i<numberOfPair; i++) {
                        if(i+1<10)
                            strToDisplay += "第0" + Integer.toString(i+1) + "組: " + bigLottery() + "\n";
                        else
                            strToDisplay += "第" + Integer.toString(i+1) + "組: " + bigLottery() + "\n";
                    }

                }
				//威力彩
				else if (mRadgrpType.getCheckedRadioButtonId() == R.id.mRadPower) {
                    strToDisplay += "                         第一區             第二區\n";
                    for (int i=0; i<numberOfPair; i++) {
                        if(i+1<10)
                            strToDisplay += "第0" + Integer.toString(i+1) + "組: " + powerLottery() + "      " + oneToEight() + "\n";
                        else
                            strToDisplay += "第" + Integer.toString(i+1) + "組: " + powerLottery() + "      " + oneToEight() + "\n";
                    }
                }
                mTxtResult.setText(strToDisplay);
            }
        }

        public String bigLottery(){
            Random randObj = new Random();
            String rtnStr = new String();
            int [] arr = new int[6];

            for(int i=0, temp; i<6; i++){
                temp = 1+randObj.nextInt(48);
                arr[i] = temp;
				
				//檢查重複
                for(int j=0; j<i; j++){
                    if(arr[j] == temp){
                        i--;
                        break;
                    }
                }
            }

           Arrays.sort(arr);

            for(int i=0; i<6; i++){
                if(arr[i]<10)
                    rtnStr += "0" + Integer.toString(arr[i]) + " ";
                else
                    rtnStr += Integer.toString(arr[i]) + " ";
            }

            return rtnStr;
        }

        public String powerLottery(){
            Random randObj = new Random();
            String rtnStr = new String();
            int [] arr = new int[6];

            for(int i=0, temp; i<6; i++){
                temp = 1+randObj.nextInt(37);
                arr[i] = temp;
                for(int j=0; j<i; j++){
                    if(arr[j] == temp){
                        i--;
                        break;
                    }
                }
            }

            Arrays.sort(arr);

            for(int i=0; i<6; i++){
                if(arr[i]<10)
                    rtnStr += "0" + Integer.toString(arr[i]) + " ";
                else
                    rtnStr += Integer.toString(arr[i]) + " ";
            }

            return rtnStr;
        }

        public String oneToEight(){
            Random randObj = new Random();
            return Integer.toString(1+randObj.nextInt(7));
        }
    };
}
