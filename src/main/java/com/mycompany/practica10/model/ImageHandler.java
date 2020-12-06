/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica10.model;

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
    private static String absolutePath;
    private static List<String> files;
    private static int BUFFER_SIZE =100;
    public static Dimension openImage(File fichero){
        try{
            BufferedImage image= ImageIO.read(fichero);
      
            Lienzo.setImage(image);
            return new Dimension(image.getWidth(),image.getHeight());
        }catch(Exception e){
             return null;
        }
    }

    public static void compressImage(String absolutePath, List<String> files) throws FileNotFoundException {
        ImageHandler.absolutePath=absolutePath;
        ImageHandler.files=files;
        
        System.out.println("asdf");
        for (String file : files) {
            System.out.println(file);
        }
        try{
            // Objeto para referenciar a los archivos que queremos comprimir
            BufferedInputStream origin = null;
            // Objeto para referenciar el archivo zip de salida
            System.out.println("ap");
            System.out.println(absolutePath);
            
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
    
    }

    private static List<String> getFileStringList(File[] filess) {
        List<String> files = new ArrayList<String>();
        for (int i = 0; i < filess.length; i++) {
            files.add(filess.toString());
            System.out.println(filess.toString());
        }
        return files;
    }

   
    protected Void doInBackground() throws Exception {
        
        return null;
    }
}