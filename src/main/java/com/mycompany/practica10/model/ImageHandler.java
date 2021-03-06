/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica10.model;

import com.mycompany.practica10.view.LoadingFrame;
import java.awt.Dimension;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import javax.swing.SwingWorker;


public class ImageHandler extends SwingWorker<Void, Integer> {
    private  String absolutePath;
    private  List<String> files;
    private  int BUFFER_SIZE =100;
    private LoadingFrame lf;
    
    public ImageHandler(String absolutePath, List<String> files, LoadingFrame lf){
        this.absolutePath=absolutePath;
        this.files=files;
        this.lf=lf;
    }

    public void compressImage(String absolutePath, List<String> files) throws FileNotFoundException, Exception {
        /*
        this.absolutePath=absolutePath;
        this.files=files;
       //TODO 
       doInBackground();
     
        try{
            // Objeto para referenciar a los archivos que queremos comprimir
            BufferedInputStream origin = null;
            // Objeto para referenciar el archivo zip de salida
            FileOutputStream dest = new FileOutputStream(absolutePath+".zip");
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
            // Buffer de transferencia para almacenar datos a comprimir
            byte[] data = new byte[BUFFER_SIZE];
            Iterator i = files.iterator();
            while(i.hasNext()) {
                String filename = (String)i.next();
                FileInputStream fi = new FileInputStream(filename);
                origin = new BufferedInputStream(fi, BUFFER_SIZE);
                ZipEntry entry = new ZipEntry( filename );
                out.putNextEntry( entry );
                // Leemos datos desde el archivo origen y se envían al archivo destino
                int count;
                while((count = origin.read(data, 0, BUFFER_SIZE)) != -1)  {
                    out.write(data, 0, count);
                }
                // Cerramos el archivo origen, ya enviado a comprimir
                origin.close();
            }
            // Cerramos el archivo zip
            out.close();
        }catch( Exception e ) {
            e.printStackTrace();
        }
    */
    }
 
    protected Void doInBackground() throws Exception {
        try{
            // Objeto para referenciar a los archivos que queremos comprimir
            BufferedInputStream origin = null;
            // Objeto para referenciar el archivo zip de salida
            FileOutputStream dest = new FileOutputStream(absolutePath+".zip");
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
            // Buffer de transferencia para almacenar datos a comprimir
            byte[] data = new byte[BUFFER_SIZE];
            Iterator i = files.iterator();
            int a=0;
            int leng= files.size();
            int each= 100/files.size();
            
            lf.setProgressBarValue(0);
            while(i.hasNext()) {
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt(); // restore interrupted status
                }
                a+=each;
                lf.setProgressBarValue(a);
                String filename = (String)i.next();
                FileInputStream fi = new FileInputStream(filename);
                origin = new BufferedInputStream(fi, BUFFER_SIZE);
                ZipEntry entry = new ZipEntry( filename );
                out.putNextEntry( entry );
                // Leemos datos desde el archivo origen y se envían al archivo destino
                int count;
                while((count = origin.read(data, 0, BUFFER_SIZE)) != -1)  {
                    out.write(data, 0, count);
                }
                // Cerramos el archivo origen, ya enviado a comprimir
                origin.close();
            }
            a++;
            // Cerramos el archivo zip
            out.close();
        }catch( Exception e ) {
            e.printStackTrace();
        }
        return null;
      
    }
    @Override
    public void done(){
        lf.dispose();
       
    }
}