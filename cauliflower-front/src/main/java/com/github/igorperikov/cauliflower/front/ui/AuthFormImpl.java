package com.github.igorperikov.cauliflower.front.ui;

import com.github.igorperikov.cauliflower.front.ui.generated.AuthForm;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;

public class AuthFormImpl extends AuthForm {
    public Button getLoginButton() {
        return loginButton;
    }

    public Button getRegisterButton() {
        return registerButton;
    }

    public TextField getLoginInput() {
        return loginInput;
    }

    public TextField getPasswordInput() {
        return passwordInput;
    }
}
