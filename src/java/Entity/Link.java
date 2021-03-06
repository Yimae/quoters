/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Kva
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "links")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Link.findAll", query = "SELECT l FROM Link l"),
    @NamedQuery(name = "Link.findById", query = "SELECT l FROM Link l WHERE l.id = :id"),
    @NamedQuery(name = "Link.findByLink", query = "SELECT l FROM Link l WHERE l.link = :link")
})

public class Link implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "link")
    private String link;
    /*
        @Basic(optional = false)
        @NotNull
        @Column(name = "postId")
        private int postId;
    */
    
    public Link(){
    }
    
    public Link(Integer id){
        this.id = id;
    }
    
    /*public Link(Integer id, integer postId){
        this.Id = id;
        this.postId = postId;
    }*/
    
    public Integer getId(){
        return id;
    }
    
    public void steId(Integer Id){
        this.id = id;
    }
    
    public String getLink(){
        return link;
    }
    
    public void setLink(String link){
        this.link = link;
    }
    
    /*public int getPostId(){
        return postId;
    }
    
    public void setPostId(int PostId){
        this.postId = postId;
    }
    */
    
    @Override
    public int hashCode(){
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object){
        if(!(object instanceof Link)){
            return false;
        }
        Link other = (Link) object;
        if((this.id == null && other.id != null)|| (this.id != null && !this.id.equals(other.id))){
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return "Entity.Link[ id="+id+"]";
    }
    
    @ManyToOne
    @JoinColumn(name = "postId")
    private Quote quote;
    public Quote getQuote(){
        return quote;
    }
    
    public void setQuote(Quote quote){
        this.quote = quote;
    }
}
