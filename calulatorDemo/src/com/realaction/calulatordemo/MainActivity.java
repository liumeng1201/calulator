package com.realaction.calulatordemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextView text_history;
	private TextView text_result;
	private Button btn_0;
	private Button btn_1;
	private Button btn_2;
	private Button btn_3;
	private Button btn_4;
	private Button btn_5;
	private Button btn_6;
	private Button btn_7;
	private Button btn_8;
	private Button btn_9;
	private Button btn_c;
	private Button btn_result;
	private Button btn_del;
	private Button btn_plus;
	private Button btn_minus;
	private Button btn_multiply;
	private Button btn_divide;
	private Button btn_dot;
	private StringBuilder sb = new StringBuilder();
	private OnClickListener l = new OnClickListener() {
		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.btn_0:
				setResult('0');
				break;
			case R.id.btn_1:
				setResult('1');
				break;
			case R.id.btn_2:
				setResult('2');
				break;
			case R.id.btn_3:
				setResult('3');
				break;
			case R.id.btn_4:
				setResult('4');
				break;
			case R.id.btn_5:
				setResult('5');
				break;
			case R.id.btn_6:
				setResult('6');
				break;
			case R.id.btn_7:
				setResult('7');
				break;
			case R.id.btn_8:
				setResult('8');
				break;
			case R.id.btn_9:
				setResult('9');
				break;
			case R.id.btn_c:
				sb.delete(0, sb.length());
				sb.append(0);
				text_history.setText("");
				text_result.setText(sb);
				break;
			case R.id.btn_result:
				expRight();
				text_history.setText(sb);
				setResult('=');
				break;
			case R.id.btn_del:
				if (sb.length() > 1) {
					sb.delete(sb.length() - 1, sb.length());
				} else {
					sb.delete(0, sb.length());
					sb.append(0);
				}
				text_result.setText(sb);
				break;
			case R.id.btn_plus:
				setResult('+');
				break;
			case R.id.btn_minus:
				setResult('-');
				break;
			case R.id.btn_multiply:
				setResult('*');
				break;
			case R.id.btn_divide:
				setResult('/');
				break;
			case R.id.btn_dot:
				setResult('.');
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		text_history = (TextView) findViewById(R.id.text_history);
		text_result = (TextView) findViewById(R.id.text_result);
		btn_0 = (Button) findViewById(R.id.btn_0);
		btn_1 = (Button) findViewById(R.id.btn_1);
		btn_2 = (Button) findViewById(R.id.btn_2);
		btn_3 = (Button) findViewById(R.id.btn_3);
		btn_4 = (Button) findViewById(R.id.btn_4);
		btn_5 = (Button) findViewById(R.id.btn_5);
		btn_6 = (Button) findViewById(R.id.btn_6);
		btn_7 = (Button) findViewById(R.id.btn_7);
		btn_8 = (Button) findViewById(R.id.btn_8);
		btn_9 = (Button) findViewById(R.id.btn_9);
		btn_c = (Button) findViewById(R.id.btn_c);
		btn_result = (Button) findViewById(R.id.btn_result);
		btn_del = (Button) findViewById(R.id.btn_del);
		btn_plus = (Button) findViewById(R.id.btn_plus);
		btn_minus = (Button) findViewById(R.id.btn_minus);
		btn_multiply = (Button) findViewById(R.id.btn_multiply);
		btn_divide = (Button) findViewById(R.id.btn_divide);
		btn_dot = (Button) findViewById(R.id.btn_dot);

		btn_0.setOnClickListener(l);
		btn_1.setOnClickListener(l);
		btn_2.setOnClickListener(l);
		btn_3.setOnClickListener(l);
		btn_4.setOnClickListener(l);
		btn_5.setOnClickListener(l);
		btn_6.setOnClickListener(l);
		btn_7.setOnClickListener(l);
		btn_8.setOnClickListener(l);
		btn_9.setOnClickListener(l);
		btn_c.setOnClickListener(l);
		btn_result.setOnClickListener(l);
		btn_del.setOnClickListener(l);
		btn_plus.setOnClickListener(l);
		btn_minus.setOnClickListener(l);
		btn_multiply.setOnClickListener(l);
		btn_divide.setOnClickListener(l);
		btn_dot.setOnClickListener(l);
	}

	private void setResult(char c) {
		if (c == '=') {
			expRight();
			if (sb.charAt(sb.length() - 2) == '/'
					&& sb.charAt(sb.length() - 1) != 0) {
				Toast.makeText(MainActivity.this, "除数不能为0", Toast.LENGTH_SHORT)
						.show();
				sb.delete(sb.length() - 2, sb.length());
				text_history.setText(sb.toString());
			} else {
				String result;
				if (sb.lastIndexOf("+") > 0 || sb.lastIndexOf("-") > 0
						|| sb.lastIndexOf("*") > 0 || sb.lastIndexOf("/") > 0) {
					result = Utils.sizeyunsuan(sb.toString());
				} else {
					result = sb.toString();
				}
				sb.delete(0, sb.length());
				sb.append(result);
			}
		} else {
			if (sb.length() == 1 && sb.toString().equalsIgnoreCase("0")) {
				sb.delete(0, sb.length());
			}
			sb.append(c);
		}
		text_result.setText(sb);
	}

	private void expRight() {
		if (sb.charAt(sb.length() - 1) == '+'
				|| sb.charAt(sb.length() - 1) == '-'
				|| sb.charAt(sb.length() - 1) == '*'
				|| sb.charAt(sb.length() - 1) == '/') {
			sb.delete(sb.length() - 1, sb.length());
			expRight();
		} else {
			return;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
