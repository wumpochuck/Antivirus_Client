package ru.mtuci.antivirus.utils;

import ru.mtuci.antivirus.controllers.MainWindowController;

import java.io.*;

public class PipeHandler {

    private static final String PIPE_PATH = "\\\\.\\pipe\\AntivirusServicePipe";

    public static String sendData(String data) {
        String response = "";
        try (RandomAccessFile pipe = new RandomAccessFile(PIPE_PATH, "rw")) {
            // Write data to the named pipe
            pipe.writeBytes(data);
            pipe.writeByte('\n');

            response = pipe.readLine();
        } catch (IOException e) {
            System.out.println("PipeHandler: sendData: Error pipe connection: " + e.getMessage());
            if(e.getMessage().contains("Идет закрытие канала")){
                System.out.println("Вы закрыли программу через IDE, или изменили код!\nЗакройте программу через трей-меню, затем запустите снова");
            }
            else {
                e.printStackTrace();
            }
        }

        return response;
    }

    public static String sendRegistrationData(String login, String password, String email) {
        String data = "register:" + login + ":" + password + ":" + email;
        return sendData(data);
    }

    public static String sendLoginData(String login, String password) {
        String data = "login:" + login + ":" + password;
        return sendData(data);
    }

    public static String sendActivationData(String activationCode, String deviceName, String macAddress){
        String data = "license_activation:" + activationCode + ":" + deviceName + ":" + macAddress;
        return sendData(data);
    }

    public static String getLicenseInfo(String macAddress){
        String data = "license_info"+ ":" + macAddress;
        return sendData(data);
    }

    public static String updateLicense(String login, String password, String licenseCode, String macAddress){
        String data = "license_update:" + login + ":" + password + ":" + licenseCode + ":" + macAddress;
        return sendData(data);
    }

//    public static String updateJWT(){
//        return sendData("jwtupdate:");
//    }

    public static String sendUpdateUserData(String newLogin, String newPassword, String newEmail, String password){
        String data = "update_user:" + password + ":" + newLogin + ":" + newPassword + ":" + newEmail;
        return sendData(data);
    }

    public static String checkAuthorization(){
        String response = sendData("jwt_check:");
        System.out.println("PipeHandler: checkAuthorization: response: " + response);
        if (response != null && !response.equals("false") && !response.isEmpty()) {
            return response;
        }
        return "false";
    }

    public static void clearAuthorization(){
        String response = sendData("logout:");
        System.out.println("PipeHandler: clearAuthorization: response: " + response);

    }
}