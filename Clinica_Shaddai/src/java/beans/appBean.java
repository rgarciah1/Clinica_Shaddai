/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import util.MyUtil;

/**
 *
 * @author Usuario
 */
public class appBean {

    /**
     * Creates a new instance of appBean
     */
    public appBean() {
    }
    
    public String getBaseUrl(){
        return MyUtil.baseurl();
    }
    
    public String getBasepath(){
        return MyUtil.basepath();
    }
}