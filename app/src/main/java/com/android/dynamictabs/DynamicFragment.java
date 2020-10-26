package com.android.dynamictabs;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.RadioButton;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import Util.Util;


public class DynamicFragment extends Fragment implements View.OnClickListener, TextWatcher {
    LinearLayout layout_signup, layout_tab;
    TextInputLayout txt_user_input, txt_pwd_input, et_input_count, txt_inputaddress;
    Context mContext;
    int tabCount;
    String USERNAME = "userName";
    String PASSWORD = "Password";

    DynamicUiComponents uiComponent;
    int position;
    LinearLayout ll_main;

    public static DynamicFragment newInstance() {
        return new DynamicFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firstlayout, container, false);

        mContext = getActivity();
        getExtras();
        ll_main = view.findViewById(R.id.linearlayout);
        loadUiComponents(view);

        return view;
    }

    private void getExtras() {
        try {
            uiComponent = new DynamicUiComponents(DynamicFragment.this, getActivity());
            Bundle bundle = getArguments();
            if (bundle != null) {

                if (bundle.containsKey("position")) {
                    position = bundle.getInt("position");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void loadUiComponents(View view) {
        switch (position) {
            case 0:
                addingComponents(view);
                break;
            case 1:
                ll_main.addView(uiComponent.addingTextInputLayout());
                ll_main.setFocusableInTouchMode(true);
                ll_main.setFocusable(true);
                break;
            case 2:
                ll_main.addView(uiComponent.toggleGroupButton());
                ll_main.addView(uiComponent.getButton());

                break;

            case 3:
                ll_main.addView(uiComponent.materialCardView());
                break;


            default:
                ll_main.addView(uiComponent.addingTextInputLayout());
                ll_main.setFocusableInTouchMode(true);
                ll_main.setFocusable(true);
                ll_main.addView(uiComponent.toggleGroupButton());
                break;
        }
    }


    private void addingComponents(View view) {

        ll_main.addView(uiComponent.addingRadioButton());

        LinearLayout ll_layout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 0, 10, 0);
        ll_layout.setLayoutParams(layoutParams);
        ll_layout.setOrientation(LinearLayout.VERTICAL);
        ll_layout.setId(R.id.layout);


        layout_tab = uiComponent.addingTabLayout();
        ll_layout.addView(layout_tab);

        layout_signup = uiComponent.addingSignupPage();
        ll_layout.addView(layout_signup);

        ll_main.addView(ll_layout);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /*
     * Button Click Event
     */
    @Override
    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.btn_signup:
                    String userName = txt_user_input.getEditText().getText().toString();
                    String password = txt_pwd_input.getEditText().getText().toString();
                    if (userName != null && userName.length() == 0) {
                        if (!txt_user_input.isErrorEnabled()) {
                            txt_user_input.setErrorEnabled(true);
                        }
                        txt_user_input.setError("Enter UserName");
                    } else if (password != null && password.length() == 0) {
                        if (!txt_pwd_input.isErrorEnabled()) {
                            txt_pwd_input.setErrorEnabled(true);
                        }
                        txt_pwd_input.setError("Enter Password");
                    } else if (userName != null && userName.length() > 0 && password != null && password.length() > 0) {

                        Util.saveValueInPrefrences(mContext, USERNAME, userName);
                        Util.saveValueInPrefrences(mContext, PASSWORD, password);
                        if (tabCount != 1) {
                            ((MainActivity) mContext).nextFragment(1);
                        }
                    }

                    break;
                case R.id.btn_update:
                    String count = et_input_count.getEditText().getText().toString();
                    int tabCount = Integer.parseInt(count);
                    if (tabCount > 1) {
                        ((MainActivity) mContext).refreshTabs(tabCount);
                    }
                    break;

                case R.id.btn_time:
                    Snackbar snackbar = Snackbar
                            .make(ll_main, "Hi i am SnackBar", Snackbar.LENGTH_LONG);

                    snackbar.show();


                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (txt_pwd_input.getEditText().hasFocus()) {
            if (txt_pwd_input.isErrorEnabled()) {
                txt_pwd_input.setErrorEnabled(false);
            }
        } else if (et_input_count.getEditText().hasFocus()) {
            if (et_input_count.isErrorEnabled()) {
                et_input_count.setErrorEnabled(false);
            }
        } else if (txt_user_input.getEditText().hasFocus()) {
            if (txt_user_input.isErrorEnabled()) {
                txt_user_input.setErrorEnabled(false);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


}
