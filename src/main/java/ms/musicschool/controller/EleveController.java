package ms.musicschool.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.ListDataModel;
import ms.musicschool.model.Eleve;
import ms.musicschool.view.EleveEJB;

@ManagedBean
@SessionScoped
public class EleveController {
    @EJB
    private EleveEJB eleveEJB;
    private Eleve eleve = new Eleve();
    private ListDataModel eleveList;
    private HtmlDataTable dataTable;
    
    private void updateBookList() {
        eleveList = new ListDataModel(eleveEJB.findAll());
    }
    
    public String doCreate() {
        eleve = eleveEJB.create(eleve);
        updateBookList();
        return "listEleves.xhtml";
    }
    
    public String doDelete() {
        List<Eleve> eleves = (List<Eleve>)eleveList.getWrappedData();
        eleveEJB.delete(eleves);
        updateBookList();        
        return "index.html";
    }
    
    public Eleve getEleve(){
        return eleve;
    }
    
    public void setGame(Eleve eleve){
        this.eleve = eleve;
    }
    
    public ListDataModel getEleveList() {
        return eleveList;
    }

    public void setGameList(ListDataModel eleveList) {
        this.eleveList = eleveList;
    }
    
    public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }
}
