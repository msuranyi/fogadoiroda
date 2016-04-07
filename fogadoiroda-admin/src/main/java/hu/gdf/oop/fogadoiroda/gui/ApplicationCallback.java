package hu.gdf.oop.fogadoiroda.gui;

public interface ApplicationCallback {

    void showNotification(String message);    
    
    void showWarning(String message);
    
    void startProgressBar();
    
    void stopProgressBar();
    
}