package com.example.alan_.proyectomoviles;


        import android.content.Context;
        import android.graphics.Color;
        import android.icu.text.SimpleDateFormat;
        import android.os.Bundle;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.github.sundeepk.compactcalendarview.CompactCalendarView;
        import com.github.sundeepk.compactcalendarview.domain.Event;

        import java.util.ArrayList;
        import java.util.Date;
        import java.util.Locale;

public class calendariop extends AppCompatActivity {

    private static final  String TAG = "1";
    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- YYYY", Locale.getDefault());
    /*
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
            // Set first day of week to Monday, defaults to Monday so calling setFirstDayOfWeek is not necessary
            // Use constants provided by Java Calendar class
            compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);

            // Add event 1 on Sun, 07 Jun 2015 18:20:51 GMT
            Event ev1 = new Event(Color.GREEN, 1433701251000L, "Some extra data that I want to store.");
            compactCalendar.addEvent(ev1);

            // Added event 2 GMT: Sun, 07 Jun 2015 19:10:51 GMT
            Event ev2 = new Event(Color.GREEN, 1433704251000L);
            compactCalendar.addEvent(ev2);

            // Query for events on Sun, 07 Jun 2015 GMT.
            // Time is not relevant when querying for events, since events are returned by day.
            // So you can pass in any arbitary DateTime and you will receive all events for that day.
            List<Event> events = compactCalendar.getEvents(1433701251000L); // can also take a Date object

            // events has size 2 with the 2 events inserted previously
            Log.d(TAG, "Events: " + events);

            // define a listener to receive callbacks when certain events happen.
            compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
                @Override
                public void onDayClick(Date dateClicked) {
                    List<Event> events = compactCalendarView.getEvents(dateClicked);
                    Log.d(TAG, "Day was clicked: " + dateClicked + " with events " + events);
                }

                @Override
                public void onMonthScroll(Date firstDayOfNewMonth) {
                    Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
                }
            });
        }
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendariop);
        final ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(false);
        actionbar.setTitle(null);
        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        final Event ev1 = new Event(Color.BLACK, 1491627601000L,"Dia especial");
        compactCalendar.addEvent(ev1);
        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();

                if (dateClicked.toString().contains("Tue Apr 04") && dateClicked.toString().contains("2017")) {

                    Toast.makeText(context, "Felis dia atodos", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "no hay tarea asignada "+ev1.getData()+dateClicked, Toast.LENGTH_SHORT).show();
                    final Event ev2 = new Event(Color.BLACK, dateClicked.getTime(),"Dia especial");
                    compactCalendar.addEvent(ev2);

                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionbar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });
        ArrayList<String> list = new ArrayList<String>();
        list.add("");
        CaldroidAdapter adapter = new CaldroidAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.post);
        lView.setAdapter(adapter);
    }

}

