package Core.db.Service;

import Core.Controller.StompHandler.Shout;
import Utils.fileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class pythoneditService{
    private SimpMessagingTemplate messaging;

    @Autowired
    public pythoneditService(SimpMessagingTemplate messaging) {
        this.messaging = messaging;
    }

    final Map<String, String> userpythongoonMap = new HashMap<String, String>();
    final Map<String, Process> userProcessMap=new HashMap<String, Process>();

    private final static Logger logger=LoggerFactory.getLogger(pythoneditService.class);

    public void runpythonfile(final String username, String pythonfilecontent) {
        final String filepath = "D:\\ideaWorkSpace\\daimafenxiang\\src\\main\\Resource\\pythonfile\\" + username + "editpython.py";
//        final String filepath="/tmp/pythonlinshiFile/"+username+"editpython.py";//服务器需修改
        fileUtils.saveStringToFile(pythonfilecontent, filepath, false);
        new Thread(new Runnable() {
            public void run() {
                Process proc=null;
                synchronized (userProcessMap){
                    if(userProcessMap.get(username)!=null){
                        sendEndRun(username);
                        return;
                    }
                    try {
                        proc = Runtime.getRuntime().exec("python  " + filepath);
                        userProcessMap.put(username,proc);
                    } catch (IOException e) {
                        sendEndRun(username);
                        e.printStackTrace();
                    }
                }
                try {
                    getMessageToMp(proc.getInputStream(), new StringBuilder(username), new StringBuilder("input"));
                    getMessageToMp(proc.getErrorStream(), new StringBuilder(username), new StringBuilder("error"));
                    proc.waitFor();
                    synchronized (userProcessMap){
                        userProcessMap.remove(username);
                    }
                    sendEndRun(username);
                } catch (InterruptedException e) {
                    sendEndRun(username);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void getMessageToMp(final InputStream input, final StringBuilder username, final StringBuilder messagetype) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                Reader reader = new InputStreamReader(input);
                BufferedReader bf = new BufferedReader(reader);
                String line = null;
                try {
                    while ((line = bf.readLine()) != null) {
                        synchronized (userpythongoonMap) {
                            String oldmessage=userpythongoonMap.get(username.toString());
                            if(oldmessage==null){
                                oldmessage="";
                            }
                            userpythongoonMap.put(username.toString(), oldmessage + "\n"+line);//将写出数据放入Map中去
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public boolean stopRunning(String username){
        synchronized (userProcessMap){
            Process proc=userProcessMap.get(username);
            if(proc==null){
                return false;
            }
            proc.destroy();
            userProcessMap.remove(username);
            return true;
        }
    }

    private void sendEndRun(String username){
        Shout message = new Shout();
        message.setMessage("@Complete@");
        messaging.convertAndSendToUser(username, "/queue/pythonoutinfo", message);
    }

    public void sendMessageToUser() {
        synchronized (userpythongoonMap){
            for (String key : userpythongoonMap.keySet()) {
                Shout message = new Shout();
                message.setMessage(userpythongoonMap.get(key));
                messaging.convertAndSendToUser(key, "/queue/pythonoutinfo", message);
                userpythongoonMap.remove(key);
            }
        }
    }
}
