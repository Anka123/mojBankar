package hr.foi.air.osobnibankar;

import hr.foi.air.osobnibankar.adapters.IzvjestajiAdapter;
import hr.foi.air.osobnibankar.db.Transakcija;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.Spinner;

import com.activeandroid.query.Select;

public class IzvjestajiActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.izvjestaji);
		final Context c = this;
		final ExpandableListView lista = (ExpandableListView) findViewById(R.id.izvjestaj);

		final Spinner spin = (Spinner) findViewById(R.id.izbornik);
		spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String kategorija_odabir = spin.getSelectedItem().toString();
				List<Transakcija> trans = new Select().all()
						.from(Transakcija.class)
						.where("kategorija == ?", kategorija_odabir).execute();

				IzvjestajiAdapter izvjestaj_adapter = new IzvjestajiAdapter(c,
						trans);
				lista.setAdapter(izvjestaj_adapter);
				lista.setOnGroupClickListener(new OnGroupClickListener() {

					@Override
					public boolean onGroupClick(ExpandableListView parent,
							View v, int groupPosition, long id) {

						return false;
					}
				});

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

	}
}
