package com.holaf.easypay.souce;

import android.app.Activity;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class SaveClass {
    public interface OnLoadListener {
        void onLoad(Object o);
    }

    public static void save(final Serializable o, final String nameFile) {
        new Thread() {
            @Override
            public void run() {
                try {
                    FileOutputStream fout = new FileOutputStream(nameFile);
                    ObjectOutputStream oos = new ObjectOutputStream(fout);
                    oos.writeObject(o);
                    oos.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }.start();
    }
    public  static  Object load(String nameFile){
        Object o = null;
        try {
            File f = new File(nameFile);
            if (f.exists()) {
                FileInputStream fout = new FileInputStream(f);
                ObjectInputStream oos = new ObjectInputStream(fout);
                o = oos.readObject();
                oos.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return o;
    }
    public static void load(final Activity activity, final String nameFile, final OnLoadListener onLoadListener) {
        new Thread() {
            @Override
            public void run() {
                Object o = null;
                try {
                    File f = new File(nameFile);
                    if (f.exists()) {
                        FileInputStream fout = new FileInputStream(f);
                        ObjectInputStream oos = new ObjectInputStream(fout);
                        o = oos.readObject();
                        oos.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                final Object finalO = o;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onLoadListener.onLoad(finalO);
                    }
                });
            }

        }.start();
    }

    public static void deleteFile(final String name) {
        new Thread() {
            @Override
            public void run() {
                File file = new File(name);
                if (file.exists()) {
                    file.delete();
                }
            }
        }.start();

    }

}
