/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash;

import views.login;

/**
 *
 * @author fajarsubeki
 */
public class Screenscreen {

    public static void main(String[] args){
        splash Splash = new splash();
        Splash.setVisible(true);
        login Login = new login();
        
        try{
            for(int i = 0; i <= 100; i++){
                Thread.sleep(40);
                Splash.loadinnum.setText(Integer.toString(i)+"%");
                Splash.loadingbar.setValue(i);
                if(i==100){
                    Splash.setVisible(false);
                    Login.setVisible(true);
                }
            }
        }catch (Exception e){
            
        }
    }
    
}
