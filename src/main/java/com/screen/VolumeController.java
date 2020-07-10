package com.screen;

import javax.naming.OperationNotSupportedException;

public class VolumeController {
    static{
        try{
            Runtime.getRuntime().addShutdownHook(new Thread(){
                @Override
                public void run() {
                    try{
                        finalize();
                    }catch (Throwable e){
                        e.printStackTrace();
                    }
                }
            });
            System.load("D:\\IJWorkSpace\\ScreenCapture\\src\\com\\screen\\Volume\\msvcr120d.dll");
            System.load("D:\\IJWorkSpace\\ScreenCapture\\src\\com\\screen\\Volume\\VolumeControlDLL.dll");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static VolumeController uniqueInstance = null;
    private VolumeController() throws OperationNotSupportedException{
        init();
    }

    public static VolumeController getInstance(){
        if(uniqueInstance == null){
            try{
                uniqueInstance = new VolumeController();
            }catch (OperationNotSupportedException e){
                e.printStackTrace();
                return null;
            }
        }
        return uniqueInstance;
    }

    private native void init() throws OperationNotSupportedException;

    public native void setMasterVolume(int num) throws OperationNotSupportedException;

    public native void getMasterVolume() throws OperationNotSupportedException;

    public native void setMute(boolean bMute) throws OperationNotSupportedException;

    public native boolean getMute() throws OperationNotSupportedException;

    private native void finished();

    public native static void release();

    @Override
    protected void finalize(){
        finished();
        try{
            super.finalize();
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
