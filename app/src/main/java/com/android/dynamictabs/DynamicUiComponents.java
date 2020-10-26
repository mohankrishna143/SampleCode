package com.android.dynamictabs;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class DynamicUiComponents {

    DynamicFragment ctx;
    Activity activity;

    public DynamicUiComponents(DynamicFragment cotx, Activity acty) {
        ctx = cotx;
        activity = acty;

    }


    /*
     * Creating Group Radio buttons
     */
    public LinearLayout addingRadioButton() {
        LinearLayout layout = new LinearLayout(activity);
        layout.setId(R.id.radio_layout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.setMargins(0, 50, 0, 0);
        layout.setLayoutParams(layoutParams);
        layout.setGravity(Gravity.CENTER);

        RadioGroup radio = new RadioGroup(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        radio.setOrientation(LinearLayout.HORIZONTAL);
        radio.setLayoutParams(params);
        for (int i = 0; i < 2; i++) {
            RadioButton radioButton = new RadioButton(activity);
            String txt;
            if (i == 1) {
                radioButton.setId(R.id.radio_button_2);
                txt = "Login";
            } else {
                radioButton.setId(R.id.radio_button_1);
                radioButton.setChecked(true);
                txt = "Update Tab Count";
            }
            radioButton.setText(txt);
            radio.addView(radioButton);
        }
        radio.setId(R.id.radioGroup);
        layout.addView(radio);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radio_button_2) {
                    ctx.layout_signup.setVisibility(View.VISIBLE);
                    ctx.layout_tab.setVisibility(View.GONE);
                } else {
                    ctx.layout_signup.setVisibility(View.GONE);
                    ctx.layout_tab.setVisibility(View.VISIBLE);
                    //text = "female";
                }
            }
        });
        return layout;
    }


    /*
     * Tab cout view with button
     */
    public LinearLayout addingTabLayout() {
        LinearLayout ll_layout = setLayoutParams();
        ll_layout.setOrientation(LinearLayout.VERTICAL);
        ll_layout.setId(R.id.tab_layout);


        TextView tv = new TextView(activity);
        LinearLayout.LayoutParams ll_params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll_params.gravity = Gravity.CENTER;
        tv.setLayoutParams(ll_params);
        tv.setTypeface(Typeface.DEFAULT_BOLD);
        tv.setText("Tab Count :-");
        tv.setTextColor(ctx.getResources().getColor(R.color.black));

        LinearLayout ll_tab = setLayoutParams();
        ll_tab.setOrientation(LinearLayout.HORIZONTAL);
        ll_tab.setFocusable(true);
        ll_tab.setFocusableInTouchMode(true);
        ll_tab.addView(tv);
        ctx.et_input_count = addingTextInputLayout(false);
        ctx.et_input_count.getEditText().addTextChangedListener(ctx);
        ll_tab.addView(ctx.et_input_count);
        ll_layout.addView(ll_tab);

        MaterialButton btn_update = new MaterialButton(activity);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.gravity = Gravity.CENTER;
        btn_update.setLayoutParams(param);
        btn_update.setText("Update");
        btn_update.setId(R.id.btn_update);
        btn_update.setOnClickListener(ctx);

        ll_layout.addView(btn_update);
        return ll_layout;
    }

    /*
     * Creating Signup page wih resrict tag
     */
    public LinearLayout addingSignupPage() {
        LinearLayout ll_layout = setLayoutParams();
        LinearLayout.LayoutParams ll_params = (LinearLayout.LayoutParams) ll_layout.getLayoutParams();
        ll_params.setMargins(15, 0, 15, 0);
        ll_layout.setOrientation(LinearLayout.VERTICAL);
        ll_layout.setId(R.id.layout_signup);
        ll_layout.setVisibility(View.GONE);
        for (int i = 0; i < 3; i++) {

            if (i == 0) {
                ctx.txt_user_input = addingTextInputLayout(true);
                ctx.txt_user_input.setHint(ctx.getResources().getString(R.string.hint_test));
                ctx.txt_user_input.setId(R.id.txt_user_input);
                ctx.txt_user_input.getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
                ctx.txt_user_input.getEditText().setId(R.id.et_username);
                ctx.txt_user_input.getEditText().addTextChangedListener(ctx);
                ll_layout.addView(ctx.txt_user_input);
            } else if (i == 1) {
                ctx.txt_pwd_input = addingTextInputLayout(true);
                ctx.txt_pwd_input.setHint(ctx.getResources().getString(R.string.hint_pwd));
                ctx.txt_pwd_input.setId(R.id.txt_user_input);
                ctx.txt_pwd_input.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);
                ctx.txt_pwd_input.getEditText().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                ctx.txt_pwd_input.getEditText().setId(R.id.et_pwd);
                ctx.txt_pwd_input.getEditText().addTextChangedListener(ctx);
                ll_layout.addView(ctx.txt_pwd_input);
            } else if (i == 2) {
                ctx.txt_inputaddress = addingTextInputLayout(true);
                ctx.txt_inputaddress.setHint(ctx.getResources().getString(R.string.hint_address));
                ctx.txt_inputaddress.setId(R.id.txt_user_input);
                ctx.txt_inputaddress.getEditText().setId(R.id.et_address);
                ctx.txt_inputaddress.getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
                ll_layout.addView(ctx.txt_inputaddress);
            }

        }
        MaterialButton btn_signup = new MaterialButton(activity);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.gravity = Gravity.CENTER;
        param.setMargins(0, 20, 0, 0);
        btn_signup.setLayoutParams(param);
        btn_signup.setText("Next");
        btn_signup.setId(R.id.btn_signup);
        btn_signup.setOnClickListener(ctx);
        ll_layout.addView(btn_signup);
        return ll_layout;
        // ll_layout.addView(ll_tab);
    }

    /*
     * Creating layout params
     */
    private LinearLayout setLayoutParams() {
        LinearLayout ll_layout = new LinearLayout(activity);
        LinearLayout.LayoutParams ll_params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll_params.setMargins(0, 20, 0, 0);
        ll_layout.setLayoutParams(ll_params);

        return ll_layout;
    }


    /*
     * Creating inputtextview for updating tab count
     */
    public TextInputLayout addingTextInputLayout(boolean isMarginTop) {
        LinearLayout.LayoutParams ll_params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (isMarginTop) {
            ll_params.setMargins(20, 20, 20, 0);
        } else {
            ll_params.setMargins(20, 0, 0, 0);
        }
        ll_params.gravity = Gravity.CENTER;
        TextInputLayout emailTextInputLayout = new TextInputLayout(activity, null, R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        emailTextInputLayout.setId(R.id.et_input_count);
        emailTextInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        emailTextInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        emailTextInputLayout.setHint("Enter Tab Count");
        emailTextInputLayout.setErrorEnabled(true);

        TextInputEditText edtEmail = new TextInputEditText(emailTextInputLayout.getContext());
        emailTextInputLayout.addView(edtEmail);
        edtEmail.setInputType(InputType.TYPE_CLASS_NUMBER);
        emailTextInputLayout.setLayoutParams(ll_params);
        emailTextInputLayout.setId(R.id.et_count);

        return emailTextInputLayout;
    }


    /*
     * Creating DropDdown Textview
     */

    public TextInputLayout addingTextInputLayout() {

        List<String> categories = new ArrayList<String>();
        categories.add("Accept ");
        categories.add("Beta ");
        categories.add("Cupcake");
        categories.add("Donut");
        categories.add("Eclair");
        categories.add("Froyo");
        categories.add("Gingerbread");
        categories.add("HoneyComb");
        categories.add("Ice cream Sandwich");
        categories.add("Jellybean");
        categories.add("Kitkat");
        categories.add("Lolipop");
        categories.add("Marshmallow");
        categories.add("Nougat");
        categories.add("Oreo");
        categories.add("Pie");

        LinearLayout.LayoutParams ll_params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll_params.setMargins(20, 50, 20, 0);
        ll_params.gravity = Gravity.CENTER;

        TextInputLayout emailTextInputLayout = new TextInputLayout(activity, null, R.style.Widget_MaterialComponents_TextInputLayout_FilledBox_ExposedDropdownMenu);
        emailTextInputLayout.setId(R.id.et_input_count);
        emailTextInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        emailTextInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        emailTextInputLayout.setHint("Drop down Suggestions");
        emailTextInputLayout.setLayoutParams(ll_params);
        AutoCompleteTextView textView = new AutoCompleteTextView(activity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);
        // layoutParams.setMargins(30, 30, 30, 30);
        emailTextInputLayout.addView(textView);
        emailTextInputLayout.setEndIconMode(TextInputLayout.END_ICON_DROPDOWN_MENU);

        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textView.setAdapter(dataAdapter);
        return emailTextInputLayout;
    }


    /*
     * Creating ToggleButton
     */
    public MaterialButtonToggleGroup toggleGroupButton() {
        LinearLayout.LayoutParams ll_params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll_params.setMargins(30, 30, 0, 0);
        ll_params.gravity = Gravity.CENTER;

        MaterialButtonToggleGroup toggleGroup = new MaterialButtonToggleGroup(activity);
        toggleGroup.setSingleSelection(true);
        String txt = null;
        for (int i = 0; i < 3; i++) {
            MaterialButton button = new MaterialButton(activity, null, R.attr.materialButtonOutlinedStyle);
            if (i == 0) {
                txt = "Hi";
            } else if (i == 1) {
                txt = "Hello";
            } else if (i == 2) {
                txt = "Button 1";
            }
            button.setText(txt);
            toggleGroup.addView(button);
        }
        toggleGroup.setLayoutParams(ll_params);
        return toggleGroup;
    }


    /*
     * Creating a button
     */
    public MaterialButton getButton() {

        MaterialButton btn_time = new MaterialButton(activity);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.gravity = Gravity.CENTER;
        param.setMargins(0, 20, 0, 0);
        btn_time.setLayoutParams(param);
        btn_time.setText("Snackbar");
        btn_time.setId(R.id.btn_time);
        btn_time.setOnClickListener(ctx);
        return btn_time;
    }

    /*
     * Creating Material design card view
     */
    public NestedScrollView materialCardView() {
        NestedScrollView nestedScrollView = new NestedScrollView(activity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(30, 30, 30, 30);
        nestedScrollView.setLayoutParams(layoutParams);
        nestedScrollView.setBackgroundColor(Color.WHITE);

        LinearLayout linearLayout = new LinearLayout(activity);
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(linearParams);


        for (int i = 0; i < 3; i++) {
            MaterialCardView cardView = new MaterialCardView(activity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);
            params.setMargins(20, 20, 20, 20);
            cardView.setLayoutParams(params);
            cardView.setBackgroundColor(ctx.getResources().getColor(R.color.colorPrimary));
            LinearLayout linear = new LinearLayout(activity);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400);
            linear.setOrientation(LinearLayout.VERTICAL);
            linear.setLayoutParams(param);
            ImageView image = new ImageView(activity);
            image.setBackgroundResource(R.mipmap.ic_launcher);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            linear.addView(image);
            LinearLayout linear_1 = new LinearLayout(activity);
            linear_1.setLayoutParams(layoutParams);

            TextView tv = new TextView(activity);
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(layout);
            tv.setTextSize(20.0f);
            tv.setText("header 1");

            linear_1.addView(tv);
            linear.addView(linear_1);
            cardView.addView(linear);
            linearLayout.addView(cardView);

        }
        nestedScrollView.addView(linearLayout);

        return nestedScrollView;
    }
}
