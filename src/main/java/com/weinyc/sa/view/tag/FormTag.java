/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.weinyc.sa.view.tag;

import static com.weinyc.sa.common.constants.Constants.*;

import com.weinyc.sa.core.engine.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
/**
 *
 * @author Ronghai Wei <ronghai.wei@outlook.com>
 */
public class FormTag  extends AbstractTag{

    public FormTag() {
        super();
    }
    
    
    private AbstractController controller;

    public AbstractController getController() {
        return controller;
    }

    public void setController(AbstractController controller) {
        this.controller = controller;
    }

    public AbstractController getWorker() {
        return controller;
    }

    public void setWorker(AbstractController worker) {
        this.controller = worker;
    }
 
 
    @Override
    public int doEndTag() throws JspException {
        int returnValue = super.doEndTag();
        this.println("<div style='display:none;'> <input name='__nothing' id='__nothing' value='__nothing' /> </div>");
        this.println("</form>", "</div>"); 
        return returnValue;
    }

    @Override
    public int doStartTag() throws JspException {
        super.doStartTag();
        HttpServletRequest request = this.getRequest();
        
        StringBuilder sb = new StringBuilder();
        sb.append("<div id='form' > ").append("\n");
        String action = this.getContextPath()+"/"+CORE_SERVLET+"/";
        if(controller.getNavigationBean() != null){
            action = controller.getNavigationBean().getUrl(getContextPath());
        }
        sb.append("<form class='formnomargin' name='").append(controller.getFORM_NAME())
                .append("'  id='").append(controller.getFORM_NAME())
                .append("'  action='").append(action).append("' method='post'>")
                .append("\n");
        //sb.append("     <input type='hidden' name='" + REQUEST_WORKER + "' id='" + REQUEST_WORKER + "' value='").append(worker.getClass().getName()).append("' />").append("\n");
        //sb.append("     <input type='hidden' name='" + SRC_JSP + "' id='" + SRC_JSP + "' value='").append(worker.getJspGoto()).append("' />").append("\n");
        sb.append("     <input type='hidden' name='"+BTN_OPTION+"' id='"+BTN_OPTION+"'  value='' /> ").append("\n");
        if(controller != null){
            sb.append("     <input type='hidden' name='" + NAV_TIERS + "' id='" + NAV_TIERS + "' value='").append(controller.getNavTier()).append("' />").append("\n");
        }

        this.println(sb); 
        return EVAL_BODY_INCLUDE;
    } 
    
    @Override
    public void release() { 
        this.controller = null;
        super.release();
    }
 
}
