/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author GfellerM01
 */
public class BeanErro implements Serializable{
    
    public BeanErro() {

    }

    public String getMsg() {
        return host;
    }

    public String getHost() {
        return host;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setHost(String host) {
        this.host = host;
    }


    private String msg;
    private String host;
}
