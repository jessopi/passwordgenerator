package com.jessopi.passwordgenerator;

import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/*
        Ui class of the web application
        creates components,layouts and handles input validation
 */
@SpringUI
@Theme("tests-valo-dark")

public class PasswordgeneratorUi extends UI {

    //Components & layout
    private VerticalLayout layout;

    private AllCharacterGenerator allCharacters;
    private PronounceableGenerator pronounceable;
    private boolean allowed = false;
    private Button generateButton;
    private Button selectPassword;
    private TextField passwordContainer;

    private TextField lengthEntry;
    private CheckBox uppercase;
    private CheckBox lowercase;
    private CheckBox numbers;
    private CheckBox specialCharacters;
    private RadioButtonGroup<String> passwordFormat;

    //Constructor
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        layout = new VerticalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        allCharacters = new AllCharacterGenerator();
        pronounceable = new PronounceableGenerator();
        createHeader();
        createPasswordGenerationLayout();
        createPasswordOptions();
        createLayout();
    }
    //creates the header
    private void createHeader(){
        Label header= new Label("Password Generator");
        header.addStyleName(ValoTheme.LABEL_H1);
        layout.addComponent(header);
    }
    //creates generate password button and box where the password goes
    private void createPasswordGenerationLayout(){
        generateButton = new Button("Generate Password");
        generateButton.addStyleName(ValoTheme.BUTTON_PRIMARY);

        passwordContainer = new TextField();
        passwordContainer.setWidth("50%");
        passwordContainer.addStyleName("align-center");
        passwordContainer.setPlaceholder("Generated Password");
        passwordContainer.setReadOnly(true);

        selectPassword = new Button("Select Generated Password",VaadinIcons.CLIPBOARD);
        selectPassword.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        selectPassword.addStyleName(ValoTheme.BUTTON_TINY);

        layout.addComponents(passwordContainer,selectPassword,generateButton);
    }
    //creates checkboxes to allow user to select parameters for password.
    private void createPasswordOptions(){
        VerticalLayout options = new VerticalLayout();
        options.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        options.setWidth("52%");

        lengthEntry = new TextField();
        lengthEntry.setPlaceholder("Password Length");

        uppercase = new CheckBox("Uppercase [A-Z]");
        uppercase.setValue(true);

        lowercase = new CheckBox("Lowercase [a-z]");
        lowercase.setValue(true);

        numbers = new CheckBox("Numbers [0-9]");
        numbers.setValue(true);

        specialCharacters = new CheckBox("Special Characters [!$%@#]");
        specialCharacters.setValue(true);

        Label lengthDescription = new Label("Password Length [1-256]");
        lengthDescription.addStyleName(ValoTheme.LABEL_BOLD);

        passwordFormat = new RadioButtonGroup<>();
        passwordFormat.setItems("Allow All Character Types","Make Pronounceable");
        passwordFormat.setStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
        passwordFormat.setValue("Allow All Character Types");

        Label passwordDescription = new Label("Password Formula:");
        passwordDescription.setStyleName(ValoTheme.LABEL_BOLD);
        options.addComponents(lengthDescription,lengthEntry,passwordDescription,passwordFormat,uppercase,lowercase,numbers,specialCharacters);
        layout.addComponent(options);
    }
    //creates listeners for buttons
    private void createLayout() {
        
        //listener for generateButton to generate a new password on click.
        generateButton.addClickListener(clickEvent ->{
            //clears current password
            passwordContainer.clear();
            //if no boxes checked dont allow password generation
            if(allowed && (uppercase.getValue() || lowercase.getValue() || numbers.getValue() || specialCharacters.getValue())) {
                if(passwordFormat.getValue().equals("Allow All Character Types")){
                    passwordContainer.setValue(allCharacters.generatePassword(Integer.parseInt(lengthEntry.getValue()), uppercase.getValue(), lowercase.getValue(), numbers.getValue(), specialCharacters.getValue()));
                } else {
                    //call to generate a pronounceable password
                    passwordContainer.setValue(pronounceable.generatePassword(Integer.parseInt(lengthEntry.getValue()),uppercase.getValue(),lowercase.getValue()));
                }
            }
        });
        //selects password generated for the user to copy to clipboard
        selectPassword.addClickListener(clickEvent -> passwordContainer.selectAll());

        //If pronounceable word is selected sets numbers and special chracters to false
        passwordFormat.addValueChangeListener(e->{
            if(passwordFormat.getValue().equals("Make Pronounceable")){
                numbers.setValue(false);
                specialCharacters.setValue(false);
            }
        });

        //Validation for password length checking range and if input is an integer.
        lengthEntry.addValueChangeListener(e->{
           try{
                int passwordLength = Integer.parseInt(lengthEntry.getValue());
                if(passwordLength < 1 ||  passwordLength > 256){
                    throw new IllegalArgumentException("Not valid int");
                }
            } catch (NumberFormatException ex){
                allowed = false;
                lengthEntry.setComponentError(new UserError("Length needs to be an Integer"));

               return;
            } catch (IllegalArgumentException ex1){
               allowed = false;
               lengthEntry.setComponentError(new UserError("Length needs to be between 1 - 256"));
               return;
           }
            lengthEntry.setComponentError(null);

            allowed = true;
        });
        specialCharacters.addValueChangeListener(e->{
            if(specialCharacters.getValue()){
                passwordFormat.setValue("Allow All Character Types");
            }
        });
        numbers.addValueChangeListener(e->{
            if(numbers.getValue()){
                passwordFormat.setValue("Allow All Character Types");
            }
        });

        setContent(layout);
    }
}
