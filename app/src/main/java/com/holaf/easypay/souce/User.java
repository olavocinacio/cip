package com.holaf.easypay.souce;

import java.io.File;
import java.io.Serializable;

public class User implements Serializable {


    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public static interface CONFIG {
        int MIN_LEN_PASSWORD = 6;
    }

    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
    private String cpf;
    private String number;

    public String getName() {
        return name;
    }

    public User(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void Save(String path,String pass) throws FileAlreadyExistSException {
        String s = new String(path+'/'+SaveLogin.crip(email)+SaveLogin.crip(pass)+".ho");
        if (new File(s).exists())
            throw new FileAlreadyExistSException("usuario ja cadastrado");
        SaveClass.save(this,s);
    }

    public static class FileAlreadyExistSException extends Exception {
        public FileAlreadyExistSException(String usuario_ja_cadastrado) {
            super(usuario_ja_cadastrado);
        }
    }
}
