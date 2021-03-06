package com.weinyc.sa.app.engine.controller;

import com.weinyc.sa.app.engine.servicer.UserServicer;
import com.weinyc.sa.app.model.User;
import com.weinyc.sa.common.util.JSONUtils;
import com.weinyc.sa.core.annotation.ServicerType;
import com.weinyc.sa.core.engine.AbstractController;
import com.weinyc.sa.core.engine.AbstractServicer;
import com.weinyc.sa.core.servlet.CoreServlet;
import net.sf.json.JSONObject;
public class LoginController extends AbstractController{

    
    @ServicerType(value="com.weinyc.sa.app.engine.servicer.UserServicer", spring="")
    private UserServicer servicer;
    
    @Override
    public String getFORM_NAME () {
        return "LoginForm";
    }
 
    @Override
    public String getJSP_TOGO_PERIX () {
        return "Login";
    }
    
    
    @Override
    public AbstractServicer getServicer () {
        return servicer;
    }

    public void setServicer (UserServicer servicer) {
        this.servicer = servicer;
    }
    
    @Override
    public void submitAction(){
        
    }
   
    public Object getColumnsAction(){
        return this.getColumns(); 
    }
    
    
    public String getColumns(){
        return JSONUtils.toString(User.COLUMNS, 36);
    }
    
    
    public Object loginAction(){
        JSONObject json = new JSONObject();
        JSONObject pa = this.getJSONObject();
        User user = this.servicer.login(pa.getString("user_name"), pa.getString("password"));
        this.updateAuthorizer(user);
        json.put("user", user.toJson());
        return json;
    }
   
}
