package com.intruder.indoorgeojson.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Zip {


    /**
     * Unzip
     */
    public static void unZip(String zipFile, String outputFolder){

        byte[] buffer = new byte[1024];

        try{
            File folder = new File(outputFolder);
            if(!folder.exists()){
                folder.mkdir();
            }
            ZipInputStream zis =
                    new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry ze = zis.getNextEntry();

            while(ze!=null){

                String fileName = ze.getName();
                String osVersion = System.getProperty("os.name");
                if (osVersion.contains("Windows")) {
                    fileName = fileName;
                } else {
                    fileName =fileName.replaceAll("\\\\", "\\/");
                }

                File newFile = new File(outputFolder + File.separator + fileName);
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
