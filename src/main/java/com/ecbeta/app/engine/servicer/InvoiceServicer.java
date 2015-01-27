package com.ecbeta.app.engine.servicer;

import com.ecbeta.common.core.AbstractServicer;
import com.ecbeta.common.core.viewer.bean.NavigationBean;
import com.ecbeta.common.util.JSONUtils;

import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import me.ronghai.sa.dao.impl.InvoiceDAOImpl;
import me.ronghai.sa.model.AbstractModel;
import me.ronghai.sa.model.Invoice;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

public class InvoiceServicer extends AbstractServicer  {

    @Autowired
    private me.ronghai.sa.dao.impl.InvoiceDAOImpl invoiceDAO;

    public InvoiceDAOImpl getInvoiceDAO() {
        return invoiceDAO;
    }

    public void setInvoiceDAO(InvoiceDAOImpl invoiceDAO) {
        this.invoiceDAO = invoiceDAO;
    }

    private List<Invoice> invoices;

    public List<Invoice> getInvoices() {
        return invoices;
    }

    /**
     *
     */
    private static final long serialVersionUID = 4874672762001288584L;

    @Override
    public void init(NavigationBean navBean) {
        this.refresh();
    }

    private void refresh() {
        this.invoices = this.find();
    }

    @Override
    public void destory() {

    }

   
    public Invoice update(Invoice entity) {
        Invoice c = invoiceDAO.update(entity);
        this.refresh();
        return c;
    }

 
    public Invoice find(Object id) {
        return invoiceDAO.find(id);
    }

    public List<Invoice> find() {
        return invoiceDAO.find(" WHERE disabled = false ");
    }


    public void remove(Invoice c) {
        this.invoiceDAO.remove(c, false);
        this.refresh();
    }


    public void remove(Long... ids) {
        this.invoiceDAO.remove(false, Arrays.asList(ids));
        this.refresh();
    }
    
    @Override
    public boolean remove(Collection<Long> ids) {
        if(ids == null || ids.isEmpty()) return false;
        if( 0 == this.invoiceDAO.remove(false, new ArrayList<>(ids))){
            return false;
        }
        this.refresh();
        return true;
    }

    public Invoice save(Invoice c) {
        this.invoiceDAO.persistent(c);
        this.refresh();
        return c;
    }
    
    @Override
    public JSONArray getJSONArray(JSONObject json){
        return JSONUtils.toJSONArray(this.invoices, json);
    }
    
    @Override
    public List<? extends AbstractModel> beans(){
        return this.invoices;
    }
    
    @Override
    public boolean saveOrUpdate(JSONArray jsonArray) {
        if(jsonArray == null || jsonArray.isEmpty() ) return false;
        @SuppressWarnings("unchecked")
        Iterator<JSONObject> it = jsonArray.iterator();
        while(it.hasNext()){
            JSONObject newJsonObj = it.next();
            Invoice invoice = Invoice.fromJson(newJsonObj);
            Long id  = invoice.getId();
            if(this.invoiceDAO.exsit(id)){
                invoice .setUpdateTime(new Date());
            }else{
                invoice.setId(null);
                invoice.setAddTime(new Date());
                invoice .setUpdateTime(new Date());
                
            }
            this.saveOrUpdate(invoice);
        }
        this.refresh();
        return true;
    }

    private Invoice saveOrUpdate(Invoice invoice) {
        if(invoice.getId() == null){
           return this.save (invoice);
        }else{
           return this.update(invoice);
        }
    }

}
