
package com.weinyc.sa.app.model;

import static com.weinyc.sa.common.util.JSONUtils.expectOne;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.weinyc.sa.core.viewer.bean.W2UIColumnBean;
import com.weinyc.sa.common.constants.Constants;
import com.weinyc.sa.core.model.AbstractModel;
/**
 *
 * @author ronghai
 */
@Entity(name="categories")
public class Category extends AbstractModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "code",  nullable=true)
    private String code;
    
    @Column(name = "level")
    private Integer level = 1;
    
    @Column(name = "parent_id")
    private Long parentId = -1L;
    
    private transient Category parent;
    private transient List<Category> children;
    

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }


    @Column(name = "disabled")
    private Integer disabled;

    @Column(name = "add_time", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date addTime;

    @Column(name = "update_time", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Column(name = "note", nullable=true)
    private String note;

    public Category() {
    }

    public Category(Long id) {
        this.id = id;
    }

    
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    
    @Override
    public boolean isDisabled() {
        return disabled != null && disabled == DISABLED_YES;
    }

    /**
     *
     * @param disabled
     */
    @Override
    public void setDisabled(boolean disabled) {
        this.disabled = disabled ? DISABLED_YES : DISABLED_NO;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getName()+"[ id=" + id + " ]";
    }
    
    
    private transient  boolean changed;

    @Override
    public boolean isChanged() {
        return changed;
    }
    @Override
    public void setChanged(boolean changed) {
        this.changed = changed;
    }
    
    
    public static final JSONArray COLUMNS;
    static{
        COLUMNS = new JSONArray();
        COLUMNS.add(new W2UIColumnBean("recid", "ID", "20%", true ,"int").toJson());
        COLUMNS.add(new W2UIColumnBean("name", "Name", "20%", true, "text" , JSONObject.fromObject("{ type: 'text'  }")).toJson());
        COLUMNS.add(new W2UIColumnBean("code", "Code", "20%", true, "text" , JSONObject.fromObject("{ type: 'text'  }")).toJson());
        W2UIColumnBean col = new W2UIColumnBean("parentId", "Parent", "20%", Constants.SAJS_PREFIX+".render_parent", true, null,  JSONObject.fromObject("{ type: 'select', items:'"+Constants.SAJS_PREFIX+".categories()' , renderDrop:'"+Constants.SAJS_PREFIX+".renderDrop' }"));
        col.setSearchable(false);
        COLUMNS.add(col.toJson());
        COLUMNS.add(new W2UIColumnBean("note", "Note", "120px", true, "text", JSONObject.fromObject("{ type: 'text'  }")).toJson());
    }
    
    @Override
    public Object toJson(){
        JSONObject map = new JSONObject();
        map.put("recid", this.getRecid());
        map.put("id", this.id);
        map.put("name", this.name);
        map.put("code", this.code);
        map.put("level", this.level);
        map.put("parentId", this.parentId);
        map.put("note", this.note);
       // map.put("children", this.children);
        return map;
    }
   
    public static  Category fromJson(JSONObject json){               
        expectOne(json, "recid", "name", "code", "level", "parentId", "note");
        expectOne(json, "id"); 
        if(json.has("recid") && !json.has("id")){
            json.put("id", json.get("recid"));
        }
        return Category.fromJson(json, Category.class);
    }
    
    private static ModelMeta<Category> modelMeta;
    @SuppressWarnings("unchecked")
    @Override
    public   ModelMeta<Category> modelMeta(){
        return _getModelMeta();
    }
    public static   ModelMeta<Category> _getModelMeta(){
        if(modelMeta == null){
            modelMeta = new ModelMeta<>(Category.class);
        }
        return modelMeta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public void addChild(Category cat) {
        if(this.children == null){
            this.children = new ArrayList<>();
        }
        this.children.add(cat);
    }
    
    public boolean isLevelOne(){
        return this.level == 1;
    }
}
