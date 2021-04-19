/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsimulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author sa370
 */
class FileManager
{   
    /*Retrieving the program's start up directory depending on the type of operating system (Windows or Linux)*/
    public static String CurrentDir()
    {
            String dir = System.getProperty("user.dir");
            String OS = System.getProperty("os.name");
            if(OS.toLowerCase().contains("windows"))
            {
                    dir = dir + "\\";
            }else{
                    dir = dir + "/";
            }
            return dir;
    }
    
    public static File SavedFileExists(String FileName)
    {
            File SFile = new File(CurrentDir()+FileName);
            if(!SFile.exists())
            {
                    return null;
            }else{
                    return SFile;
            }
    }

    /*Check if a file exists in the program's start up folder with the filename. if it does return the file*/
    public static File FileExists(String FileName)
    {
            File SFile = new File(CurrentDir()+FileName);
            if(!SFile.exists())
            {
                    return SFile;
            }else{
                    return null;
            }
    }

    public static void Save(GameSession session)
    {
        if (save(session))
        {
            Panel.Msgbox("Saved successfully");
        }
    }

    private static boolean save(GameSession session)
    {
        Boolean Success = false;
        do
        {
                String SFileName = Panel.Input("Save", "Enter filename:");
                if(SFileName != null)
                {
                        Success = SaveFile(SFileName,session);
                }else{
                        break;
                }
        }while(!Success);
        return Success;
    }

    private static boolean SaveFile(String SFileName, GameSession session)
    {
            File SFile = FileExists(SFileName + ".bin");
            if(SFile != null)
            {
                    Output(SFile, session);
                    return true;
            }else{
              Panel.Msgbox("   A file with this name already exists. Please enter a different file name.");
              return false;
            }
    }
    
    private static void Output(File SFile, GameSession session)
    {
        try
        {
                FileOutputStream FileOutput = new FileOutputStream(SFile);
                ObjectOutputStream OutputStream = new ObjectOutputStream(FileOutput);
                OutputStream.writeUTF(String.format(String.format("%s:%s:%s", session.getLevel(),session.user(),session.CarName())));
                OutputStream.close();
        }catch(Exception e)
        {
        }
    }
    
    public static GameSession Load(String FileName)
    {
        if(!FileName.endsWith(".bin"))
        {
            Panel.Msgbox("This file is not saved from this program");
            return null;
        }
        File SFile = new File(FileName);
        GameSession s = ReadGame(SFile);
        if(s != null)
        {
            Panel.Msgbox("Game loaded successfully!");
        }else{
            Panel.Msgbox("This file is not saved from this program. Please select a different file to load.");
        }
        return s;
    }

    private static GameSession ReadGame(File SFile)
    {
        GameSession _session = null;
        try
        {
            FileInputStream FileInput = new FileInputStream(SFile);
            ObjectInputStream InputStream = new ObjectInputStream(FileInput);
            String input = InputStream.readUTF();
            _session = LoadGame(input);
            if(_session != null)
            {
                return _session;
            }
            InputStream.close();
            FileInput.close();
        }catch(Exception e){
        }
        return null;
    }
    
    private static boolean isNumber(String txt)
    {
        try {
            int i = Integer.valueOf(txt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private static GameSession LoadGame(String input)
    {
        String[] items = input.split(":");
        if(isNumber(items[0]))
        {
             return new GameSession(Integer.valueOf(items[0]), items[1], items[2]);   
        }else
        {
            return null;
        }
    }

}
