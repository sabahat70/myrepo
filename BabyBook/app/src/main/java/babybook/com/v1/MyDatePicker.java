package babybook.com.v1;

/**
 * Created by sabahatahmed on 2/2/16.
 */
import android.app.DatePickerDialog;
import java.util.Calendar;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.os.Bundle;
import android.widget.TextView;


public class MyDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private int year,month,day;
    private StringBuilder date;
    private TextView tv;


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        date = setCurrentDate();

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {


        date = buildStringBuilderString(selectedMonth,selectedDay,selectedYear);

        tv = (TextView)getActivity().findViewById(R.id.tvDate);
        tv.setText(date.toString());


    }

    public StringBuilder setCurrentDate(){

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        return buildStringBuilderString(month,day,year);
    }

    public StringBuilder buildStringBuilderString(int month, int day, int year){
        return new StringBuilder().append(month + 1)
                .append("-").append(day).append("-").append(year)
                .append(" ");

    }
}