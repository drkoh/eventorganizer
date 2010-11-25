package com.cs446teameo.UI;

import com.cs446teameo.Main.R;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class WeeklyEventAdder extends Dialog implements OnClickListener {

	Button okButton, cancelButton;
	CheckBox mondayCheckbox, sundayCheckbox, tuesdayCheckbox, wednesdayCheckbox, 
							thursdayCheckbox, fridayCheckbox, saturdayCheckbox;
	public static boolean daySet = false;
	public static boolean [] days = days = new boolean[7];

	public WeeklyEventAdder(Context context) 
	{
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.weeklyeventadder);
		okButton = (Button) findViewById(R.weeklyeventadder.okButton);
		okButton.setOnClickListener(this);
		cancelButton = (Button) findViewById(R.weeklyeventadder.cancelButton);
		cancelButton.setOnClickListener(this);
		sundayCheckbox = (CheckBox)findViewById(R.weeklyeventadder.sundayCheckbox);
		mondayCheckbox = (CheckBox)findViewById(R.weeklyeventadder.mondayCheckbox);
		tuesdayCheckbox = (CheckBox)findViewById(R.weeklyeventadder.tuesdayCheckbox);
		wednesdayCheckbox = (CheckBox)findViewById(R.weeklyeventadder.wednesdayCheckbox);
		thursdayCheckbox = (CheckBox)findViewById(R.weeklyeventadder.thursdayCheckbox);
		fridayCheckbox = (CheckBox)findViewById(R.weeklyeventadder.fridayCheckbox);
		saturdayCheckbox = (CheckBox)findViewById(R.weeklyeventadder.saturdayCheckbox);
	}

	@Override
	public void onClick(View v) 
	{
		if (v == okButton)
		{
			dismiss();
			daySet = true;
			days[0] = sundayCheckbox.isChecked();
			days[1] = mondayCheckbox.isChecked();
			days[2] = tuesdayCheckbox.isChecked();
			days[3] = wednesdayCheckbox.isChecked();
			days[4] = thursdayCheckbox.isChecked();
			days[5] = fridayCheckbox.isChecked();
			days[6] = saturdayCheckbox.isChecked();
		}
		if (v == cancelButton)
		{
			dismiss();
			daySet = false;
			days = null;
		}
	}
}
