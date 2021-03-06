/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.weinyc.sa.core.db;

import com.weinyc.sa.core.viewer.bean.NavigationBean;
import com.weinyc.sa.app.model.Navigation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author ronghai
 */
public class NavigationUtil {

    private static NavigationBean covert(Navigation na){
        NavigationBean bean = new NavigationBean();
        bean.setWorker(na.getWorker());
        bean.setLabel(na.getLabel());
        bean.setOrder(na.getOrder());
        bean.setId(na.getId().intValue());
        bean.setI18n(na.getI18n());
        bean.setNavTier(new int[]{ na.getTier_1().intValue(), (int) na.getTier_2().intValue(), (int) na.getTier_3().intValue(), (int) na.getTier_4().intValue()});
        return bean;
    }
    
    public static List<NavigationBean> convert(List<Navigation> navs) {        
        LinkedHashMap<String, NavigationBean> navBeans = new LinkedHashMap<>();
        
        List<NavigationBean> all = new ArrayList<>();
        for(Navigation na: navs){
            NavigationBean bean = covert(na);
            navBeans.put(bean.getNavTier("_"), bean);
            all.add(bean);
        }
        HashSet<String> removeKeys = new HashSet<>();
        for(NavigationBean bean : all){
            String parantKey = bean.getParentNavTier("_");
            NavigationBean navBean = navBeans.get(parantKey);
            if(navBean != null){
                navBean.addChild(bean);
                removeKeys.add(bean.getNavTier("_"));
            }
        }
        for(String key : removeKeys){
             navBeans.remove(key);
        } 
        List<NavigationBean> navBeanList = new ArrayList<>(navBeans.values());
        for (Iterator<NavigationBean> iterator = navBeanList.iterator(); iterator.hasNext();) {
            NavigationBean next = iterator.next();
            if(next.getChildren() == null || next.getChildren().isEmpty()){
                if(StringUtils.isEmpty(next.getWorker())){
                    iterator.remove();
                    continue;
                }
            }else{
               // sort(next.getChildren());
            }
        }
        sort(navBeanList);
        return navBeanList;
    }
    
    public final static Comparator<NavigationBean> comparator = new Comparator<NavigationBean>(){
        @Override
        public int compare(NavigationBean o1, NavigationBean o2) {
            if(o1.getOrder() != o2.getOrder()){
                return o1.getOrder() - o2.getOrder();
            }else{
                return o1.getId() - o2.getId();
            }
        }
        
    };
    
    public static void sort(List<NavigationBean> navBeans){
        if(navBeans == null) return;
        Collections.sort(navBeans, comparator);
        for(NavigationBean bean : navBeans){
            if(bean.getChildren() != null){
              Collections.sort(bean.getChildren(), comparator);
            }
        }
    } 
}
