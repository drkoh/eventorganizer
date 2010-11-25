package com.cs446teameo.UI;

import com.cs446teameo.Main.R;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

public class MonthlyEventAdder extends Dialog implements OnClickListener {

	Button okButton, cancelButton;
	SeekBar daySeek;
	TextView dayText;
	public static int day;
	public static boolean daySet = false;

	public MonthlyEventAdder(Context context) 
	{
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.monthlyeventadder);
		okButton = (Button) findViewById(R.monthlyeventadder.okButton);
		okButton.setOnClickListener(this);
		cancelButton = (Button) findViewById(R.monthlyeventadder.cancelButton);
		cancelButton.setOnClickListener(this);
		daySeek = (SeekBar) findViewById(R.monthlyeventadder.daySeek);
		dayText = (TextView) findViewById(R.monthlyeventadder.dayText);
		// Clear Button
		daySeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
			
			public void onProgressChanged(SeekBar volume, int progress, boolean fromUser) {
				    // TODO Auto-generated method stub
				dayText.setText(" " + String.valueOf(progress + 1));
		    }
		
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void onClick(View v) 
	{
		if (v == okButton)
		{
			dismiss();
			daySet = true;
			day = daySeek.getProgress() + 1;
		}
		if (v == cancelButton)
		{
			dismiss();
			daySet = false;
			day = -1;
		}
	}
}
