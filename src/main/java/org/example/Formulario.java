package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Formulario {
    StringBuilder errores;

    public Formulario(){
        errores = new StringBuilder();
        generarFormulario();
    }

    public void generarFormulario(){

        JFrame ventana = new JFrame("Formulario de contacto");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbd = new GridBagConstraints();
        panel.setBackground(Color.white);

        JLabel nombre = new JLabel("Nombre:", SwingConstants.CENTER);
        gbd.gridx = 0;
        gbd.gridy = 0;
        gbd.insets = new Insets(6, 6, 6, 6);
        panel.add(nombre, gbd);

        JTextField ventanaNom = new JTextField(15);
        gbd.gridx = 1;
        gbd.gridy = 0;
        gbd.insets = new Insets(6, 6, 6, 6);
        gbd.fill = GridBagConstraints.HORIZONTAL;
        panel.add(ventanaNom, gbd);

        JLabel email = new JLabel("Email:", SwingConstants.CENTER);
        gbd.gridx = 0;
        gbd.gridy = 1;
        gbd.insets = new Insets(6, 6, 6, 6);
        panel.add(email, gbd);

        JTextField ventanaMail = new JTextField(15);
        gbd.gridx = 1;
        gbd.gridy = 1;
        gbd.insets = new Insets(6, 6, 6, 6);
        gbd.fill = GridBagConstraints.HORIZONTAL;
        panel.add(ventanaMail, gbd);

        JLabel contraseña = new JLabel("Contraseña:", SwingConstants.CENTER);
        gbd.gridx = 0;
        gbd.gridy = 2;
        gbd.insets = new Insets(6, 6, 6, 6);
        panel.add(contraseña, gbd);

        JPasswordField ventanaContraseña= new JPasswordField(15);
        gbd.gridx = 1;
        gbd.gridy = 2;
        gbd.insets = new Insets(6, 6, 6, 6);
        panel.add(ventanaContraseña, gbd);

        JButton botonPress = new JButton("ENVIAR");
        botonPress.setBackground(Color.gray);
        botonPress.setForeground(Color.white);
        botonPress.setFocusPainted(false);
        gbd.gridx = 1;
        gbd.gridy = 3;
        gbd.insets = new Insets(20, 6, 6, 6);
        panel.add(botonPress, gbd);

        JLabel errorMsg = new JLabel("");
        errorMsg.setPreferredSize(new Dimension(300, 60));
        errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
        gbd.gridx = 0;
        gbd.gridy = 4;
        gbd.insets = new Insets(20, 6, 6, 6);
        gbd.gridwidth = 4;
        gbd.anchor = GridBagConstraints.CENTER;
        panel.add(errorMsg, gbd);

        botonPress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = ventanaNom.getText();
                String mail = ventanaMail.getText();
                char[] passwordArray = ventanaContraseña.getPassword();
                String pass = new String(passwordArray);
                errores = new StringBuilder();
                if (validarFormulario(nombre, mail, pass)){
                    errores.append("Datos enviados con éxito");
                    ventanaNom.setText("");
                    ventanaMail.setText("");
                    ventanaContraseña.setText("");

                } else {
                    errores.append("Corrija los errores antes de enviar").append(System.lineSeparator());
                }
                errorMsg.setText("<html>" + errores.toString().replaceAll(System.lineSeparator(), "<br>") + "</html>");
            }
        });

        ventana.add(panel);
        ventana.setVisible(true);

    }

    public Boolean validarFormulario(String nom, String mail, String pass){
        boolean esNombreValido = validarNombre(nom);
        boolean esEmailValido = validarMail(mail);
        boolean esContraseñaValida = validarPass(pass);

        return esNombreValido && esEmailValido && esContraseñaValida;
    }

    public Boolean validarNombre(String n){
       if (n.isEmpty()){
           errores.append("El campo nombre no puede estar vacío").append(System.lineSeparator());
           return false;
       }
       return true;
    }

    public Boolean validarMail(String mail) {
        if (mail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            return true;
        }
        errores.append("El mail no es correcto").append(System.lineSeparator());
        return false;
    }

    public Boolean validarPass(String pass){
        if (pass.length()<8){
           errores.append("La contraseña debe tener al menos 8 caracteres").append(System.lineSeparator());
           return false;
        }
        return true;

    }

}
