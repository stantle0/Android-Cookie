package br.com.cookie;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private TextView tex;
	private Button bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tex = (TextView) findViewById(R.id.tex);
		bt = (Button) findViewById(R.id.bt);
	}

	public void processamento (View view) {
		Num num = new Num(tex, bt);
		num.execute(3);
	}
}
