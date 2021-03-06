package com.weinyc.sa.app.engine.servicer;

import com.weinyc.sa.core.engine.AbstractServicer;
import com.weinyc.sa.core.viewer.bean.NavigationBean;
import com.weinyc.sa.common.util.JSONUtils;
import com.weinyc.sa.app.dao.impl.CurrencyDAOImpl;
import com.weinyc.sa.app.model.Currency;
import com.weinyc.sa.core.dao.AbstractModelDAO;
import com.weinyc.sa.core.model.AbstractModel;

import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

public class CurrencyServicer extends AbstractServicer  {

    @Autowired
    private com.weinyc.sa.app.dao.impl.CurrencyDAOImpl currencyDAO;

    public CurrencyDAOImpl getCurrencyDAO() {
        return currencyDAO;
    }

    public void setCurrencyDAO(CurrencyDAOImpl currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    private List<Currency> currencies;

    public List<Currency> getCurrencies() {
        return currencies;
    }
    
    @Override
    public AbstractModelDAO<?> getDAO(){
        return this.currencyDAO;
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
        this.currencies = this.find();
    }

    @Override
    public void destory() {

    }

   
    public Currency update(Currency entity) {
        Currency c = currencyDAO.update(entity);
        this.refresh();
        return c;
    }

 
    public Currency find(Object id) {
        return currencyDAO.find(id);
    }

    public List<Currency> find() {
        return currencyDAO.find(" WHERE disabled = 0 ");
    }


    public void remove(Currency c) {
        this.currencyDAO.remove(c, false);
        this.refresh();
    }


    public void remove(Long... ids) {
        this.currencyDAO.remove(false, Arrays.asList(ids));
        this.refresh();
    }

    @Override
    public JSONArray getJSONArray(JSONObject json){
        return JSONUtils.toJSONArray(this.currencies, json);
    }
    
    @Override
    public List<? extends AbstractModel> beans(){
        return this.currencies;
    }
    
    @Override
    public boolean remove(Collection<Long> ids) {
        if(ids == null || ids.isEmpty() ) return false;
        if( 0 ==  this.currencyDAO.remove(false, new ArrayList<>(ids))){
            return false;
        }
        this.refresh();
        return true;
    }

    public Currency save(Currency c) {
        this.currencyDAO.persistent(c);
        this.refresh();
        return c;
    }
    
    @Override
    public boolean saveOrUpdate(JSONArray jsonArray) {
        if(jsonArray == null || jsonArray.isEmpty() ) return false;
        @SuppressWarnings("unchecked")
        Iterator<JSONObject> it = jsonArray.iterator();
        while(it.hasNext()){
            JSONObject newJsonObj = it.next();
            Currency currency = Currency.fromJson(newJsonObj);
            Long id  = currency.getId();
            if(this.currencyDAO.exsit(id)){
                currency .setUpdateTime(new Date());
            }else{
                currency.setId(null);
                currency.setAddTime(new Date());
                currency .setUpdateTime(new Date());
            }
            this.saveOrUpdate(currency);
        };
        this.refresh();
        return true;
    }

    private Currency saveOrUpdate(Currency currency) {
        if(currency.getId() == null){
           return this.save (currency);
        }else{
           return this.update(currency);
        }
    }

}
