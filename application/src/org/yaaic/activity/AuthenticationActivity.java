/*
Yaaic - Yet Another Android IRC Client

Copyright 2009-2011 Sebastian Kaspari

This file is part of Yaaic.

Yaaic is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Yaaic is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Yaaic.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.yaaic.activity;

import org.yaaic.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Authentication activity for entering nickserv / sasl usernames and password
 * for a given server.
 *
 * @author Sebastian Kaspari <sebastian@yaaic.org>
 */
public class AuthenticationActivity extends Activity implements OnCheckedChangeListener, OnClickListener
{
    private CheckBox nickservCheckbox;
    private TextView nickservPasswordLabel;
    private EditText nickservPasswordEditText;

    private CheckBox saslCheckbox;
    private TextView saslUsernameLabel;
    private EditText saslUsernameEditText;
    private TextView saslPasswordLabel;
    private EditText saslPasswordEditText;

    /**
     * On create
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.authentication);

        nickservCheckbox = (CheckBox) findViewById(R.id.nickserv_checkbox);
        nickservPasswordLabel = (TextView) findViewById(R.id.nickserv_label_password);
        nickservPasswordEditText = (EditText) findViewById(R.id.nickserv_password);

        saslCheckbox = (CheckBox) findViewById(R.id.sasl_checkbox);
        saslUsernameLabel = (TextView) findViewById(R.id.sasl_label_username);
        saslUsernameEditText = (EditText) findViewById(R.id.sasl_username);
        saslPasswordLabel = (TextView) findViewById(R.id.sasl_label_password);
        saslPasswordEditText = (EditText) findViewById(R.id.sasl_password);

        nickservCheckbox.setOnCheckedChangeListener(this);
        saslCheckbox.setOnCheckedChangeListener(this);

        ((Button) findViewById(R.id.ok)).setOnClickListener(this);
        ((Button) findViewById(R.id.cancel)).setOnClickListener(this);
    }

    /**
     * On checkbox changed
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        switch (buttonView.getId()) {
            case R.id.nickserv_checkbox:
                nickservPasswordLabel.setEnabled(isChecked);
                nickservPasswordEditText.setEnabled(isChecked);

                if (!isChecked) {
                    nickservPasswordEditText.setText("");
                }

                break;

            case R.id.sasl_checkbox:
                saslUsernameLabel.setEnabled(isChecked);
                saslUsernameEditText.setEnabled(isChecked);
                saslPasswordLabel.setEnabled(isChecked);
                saslPasswordEditText.setEnabled(isChecked);

                if (!isChecked) {
                    saslUsernameEditText.setText("");
                    saslPasswordEditText.setText("");
                }

                break;
        }
    }

    /**
     * On click on button
     */
    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.ok:
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                break;

            case R.id.cancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
