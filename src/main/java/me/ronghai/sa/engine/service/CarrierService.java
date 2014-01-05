/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.ronghai.sa.engine.service;

import java.util.Collection;
import java.util.List;
import me.ronghai.sa.model.Carrier;

/**
 *
 * @author ronghai
 */
public interface CarrierService {
    public Carrier update(Carrier entity);
    public Carrier find(Object id);
    public void remove(Carrier c);
    public List<Carrier> find();
    public void remove(Long ... ids);
    public void remove(Collection<Long> ids);
    public Carrier save(Carrier c);
}
