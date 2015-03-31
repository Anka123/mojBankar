package hr.foi.air.osobnibankar;

import hr.foi.air.osobnibankar.database.Prihod;
import hr.foi.air.osobnibankar.database.Rashod;

import java.util.Date;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class PiRActivity extends Activity {
	Context c = this;
	Dialog dialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.piractivity);
		final Context context = this;

		Button btnDodajPiR = (Button) findViewById(R.id.btnDodajPiR);
		btnDodajPiR.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog = new Dialog(context);
				dialog.setContentView(R.layout.dodajpir);
				dialog.setTitle(R.string.noviUnos);
				dialog.show();

				RadioGroup rgPiR = (RadioGroup) dialog
						.findViewById(R.id.radioGroup2);
				rgPiR.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					int izbor;

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						final int chkId = checkedId;
						Button btnSpremi = (Button) dialog
								.findViewById(R.id.btnSpremi);
						btnSpremi.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {

								switch (chkId) {
								case R.id.rbPrihod:
									izbor = 0;
									unos(izbor);
									Toast.makeText(getApplicationContext(),
											"prihod", Toast.LENGTH_SHORT)
											.show();
									dialog.dismiss();
									break;

								case R.id.rbRashod:
									izbor = 1;
									unos(izbor);
									dialog.dismiss();
									break;
								}

							}
						});
					}
				});
			}
		});
	}

	public void unos(int izbor) {
		int iz = izbor;
		EditText etNaziv = (EditText) dialog.findViewById(R.id.etNaziv);
		String naziv = etNaziv.getText().toString();
		EditText etOpis = (EditText) dialog.findViewById(R.id.etOpis);
		String opis = etOpis.getText().toString();
		EditText etIznos = (EditText) dialog.findViewById(R.id.etIznos);
		Double iznos = 0.0;
		iznos = Double.valueOf(etIznos.getText().toString());
		Date datum = new Date(System.currentTimeMillis());
		String danasnjiDatum = datum.toString();

		if (iz == 0) {
			Prihod prihod = new Prihod(naziv, opis, iznos, danasnjiDatum);
			prihod.save();
<<<<<<< HEAD
			Toast.makeText(getApplicationContext(), "Prihod spremljen",
=======
			Toast.makeText(getApplicationContext(), "prihod spremljen",
>>>>>>> origin/master
					Toast.LENGTH_SHORT).show();

		} else if (iz == 1) {
			Rashod rashod = new Rashod(naziv, opis, iznos, danasnjiDatum);
<<<<<<< HEAD
			Toast.makeText(getApplicationContext(), "Rashod spremljen",
=======
			Toast.makeText(getApplicationContext(), "rashod spremljen",
>>>>>>> origin/master
					Toast.LENGTH_SHORT).show();
			rashod.save();
		}

	}

}
